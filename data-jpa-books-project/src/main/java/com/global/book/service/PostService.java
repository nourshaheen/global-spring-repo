package com.global.book.service;

import java.io.File;
import java.util.List;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.global.book.dto.PostDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final RestTemplate restTemplate;

	private static String BASE_POST_URL = "https://jsonplaceholder.typicode.com/posts";

	public PostDto getPostById(Long id) {

//		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<PostDto> result = restTemplate.getForEntity(BASE_POST_URL + "/" + id, PostDto.class);

		return result.getBody();

	}

	public List<PostDto> getAllPost() {

//		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List> result = restTemplate.getForEntity(BASE_POST_URL, List.class);

		return result.getBody();

	}

	public PostDto addPost(PostDto dto) {

//		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("accept", "application/json");
		headers.add("accept-language", "en");

		HttpEntity<PostDto> request = new HttpEntity<>(dto, headers);

		ResponseEntity<PostDto> result = restTemplate.exchange(BASE_POST_URL, HttpMethod.POST, request, PostDto.class);

//		ResponseEntity<PostDto> result =  restTemplate.postForEntity(BASE_POST_URL, request, PostDto.class);

		return result.getBody();

	}

	public void updatePost(PostDto dto) {

// 		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<PostDto> request = new HttpEntity<>(dto);

		restTemplate.put(BASE_POST_URL, request);

	}

	public void deletePostById(Long id) {

// 		RestTemplate restTemplate = new RestTemplate();

		restTemplate.delete(BASE_POST_URL + "/" + id);

	}

	public void uploadFile(String id, String pathType) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("id", id);
		map.add("pathType", pathType);
		map.add("file", getTestFile());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		String serverUrl = "http://localhost:8082/spring-rest/fileserver/singlefileupload/";

		ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, request, String.class);
	}

	public void getUrlHeaders() {
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		HttpHeaders httpHeaders = restTemplate.headForHeaders("");
//    	 assertTrue(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON));
	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		int timeout = 5000;
		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
				.setSocketTimeout(timeout).build();
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		return new HttpComponentsClientHttpRequestFactory(client);
	}

	public String getTestFile() {

		return new File("").toString();
	}

}
