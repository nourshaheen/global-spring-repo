package com.global.product.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.global.product.entity.PostDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostWebClientService {
	
	private static String BASE_POST_URL = "https://jsonplaceholder.typicode.com";
	
	WebClient client = WebClient.create(BASE_POST_URL);

	 public Mono<PostDto> get(long id) {
         return client
                 .get()
                 .uri("/posts/" + id)
//                 .headers(headers -> headers.setBasicAuth("user", "userpwd"))
                 .retrieve()
                 .bodyToMono(PostDto.class);
     }
 
     public Flux<PostDto> getAll() {
         return client.get()
                 .uri("/posts")
//                 .headers(headers -> headers.setBasicAuth("user", "userpwd"))
                 .retrieve()
                 .bodyToFlux(PostDto.class);
     }
 
     public Flux<PostDto> findByName(String name) {
         return client.get()
                 .uri(uriBuilder -> uriBuilder.path("/posts")
                 .queryParam("name", name)
                 .build())
                 .headers(headers -> headers.setBasicAuth("user", "userpwd"))
                 .retrieve()
                 .bodyToFlux(PostDto.class);
     }
 
     public Mono<PostDto> create(PostDto s)  {
         return client.post()
                 .uri("/posts")
                 .headers(headers -> headers.setBasicAuth("admin", "adminpwd"))
                 .body(Mono.just(s), PostDto.class)
                 .retrieve()
                 .bodyToMono(PostDto.class);
     }
 
     public Mono<PostDto> update(PostDto student)  {
         return client
                 .put()
                 .uri("/posts/" + student.getId())
                 .headers(headers -> headers.setBasicAuth("admin", "adminpwd"))
                 .body(Mono.just(student), PostDto.class)
                 .retrieve()
                 .bodyToMono(PostDto.class);
     }
 
     public Mono<Void> delete(long id) {
         return client
                 .delete()
                 .uri("/posts/" + id)
                 .headers(headers -> headers.setBasicAuth("admin", "adminpwd"))
                 .retrieve()
                 .bodyToMono(Void.class);
     }
}
