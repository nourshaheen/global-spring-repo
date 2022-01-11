package com.global.book.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {
	
	@Autowired
	private MessageSource messageSource;

	/**
	 * to get message by key
	 * 
	 * @param key
	 * @return
	 */
	public String getMessage(String key) {
		return messageSource.getMessage(key, new Object[] {}, LocaleContextHolder.getLocale());
	}
	
	/**
	 * to get message by key with array of paramters 
	 * @param key
	 * @param params
	 * @return
	 */
	public String getMessage(String key , String [] params) {
		return messageSource.getMessage(key, params , LocaleContextHolder.getLocale());
	}

}
