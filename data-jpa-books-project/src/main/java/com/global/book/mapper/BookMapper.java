package com.global.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.global.book.dto.BookDto;
import com.global.book.entity.Book;

@Mapper(uses = {AutherMapper.class})
public interface BookMapper {

	@Mapping(target = "auther" , ignore = true)
	@Mapping(target = "autherName" , source = "auther.fullName")
	@Mapping(target = "autherEmail" , source = "auther.email")
	BookDto map(Book entity);

	@Mapping(source = "autherName" , target = "auther.fullName")
	@Mapping(source = "autherEmail" , target = "auther.email")
	Book unMap(BookDto dto);

}
