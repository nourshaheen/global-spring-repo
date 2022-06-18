package com.global.book.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching
@EnableAspectJAutoProxy()
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class WebConfig implements WebMvcConfigurer {
	
	
//	@Autowired
//    public void WebConfig(DataSource dataSource) {
//        Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
//    }

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		
		return new RestTemplate();
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:bundle/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Override
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	
	
//	 @Bean
//	  public LocaleResolver localeResolver() {
//	      SessionLocaleResolver slr = new SessionLocaleResolver();
//	      slr.setDefaultLocale(Locale.US);
//	      slr.setLocaleAttributeName("session.current.locale");
//	      slr.setTimeZoneAttributeName("session.current.timezone");
//	      return slr;
//	  }

	/**
	 * will switch to a new locale based on the value of the language parameter
	 *  appended to an HTTP request URL. (url?language=en)
	 * @return
	 */
//	@Bean
//	public LocaleChangeInterceptor localeChangeInterceptor() {
//	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//	    lci.setParamName("lang"); 
//	    return lci;
//	}
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//	    registry.addInterceptor(localeChangeInterceptor());
//	}
	
	
	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
	    return RedisCacheConfiguration.defaultCacheConfig()
	      .entryTtl(Duration.ofMinutes(60))
	      .disableCachingNullValues()
	      .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}
	
	
}
