package com.global.book.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.context.i18n.LocaleContextHolder;

import com.global.book.dto.AutherDto;
import com.global.book.entity.Auther;

@Mapper(imports = {LocaleContextHolder.class})
public interface AutherMapper {
	
	@Mappings({
//		@Mapping(source = "fullName" , target = "name"),
		@Mapping(target = "name" , expression = "java(LocaleContextHolder.getLocale().getLanguage() ==\"ar\" ? entity.getFullName() :  entity.getFullName())"),
		@Mapping(target = "ipAddress" , defaultValue = "192.135.125.2"),
	})
	AutherDto map (Auther entity);
	
	List<AutherDto> map (List<Auther> entity);
	
	Set<AutherDto> map (Set<Auther> entity);
	
	@Mapping(target = "fullName" , source = "name")
	Auther unMap (AutherDto dto);
	
	@Mapping(target = "fullName" , source = "name")
	@Mapping(target = "id" , ignore = true)
	@Mapping(target = "imagePath" , ignore = true)
	@Mapping(target = "bookCount" , ignore = true)
	@Mapping(target = "statusCode" , ignore = true)
	@Mapping(target = "deleted" , ignore = true)
	Auther unMap (AutherDto dto, @MappingTarget Auther t);
	
	List<Auther> unMap (List<AutherDto> dto);
	
	
	@AfterMapping
	default void mapName(Auther entity, @MappingTarget AutherDto dto) {

		if (entity.getFullName() != null) {
			String lang = LocaleContextHolder.getLocale().getLanguage();
			dto.setName(lang.equals("ar") ? entity.getFullName() : entity.getFullName());
		}
	}
	

}
