package com.global.book.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import com.global.book.dto.BookDto;
import com.global.book.entity.Book;
import com.global.book.mapper.qualifier.BookFavorateFlagQualifierImpl;
import com.global.book.mapper.qualifier.BookFavorateQualifier;
import com.global.book.mapper.qualifier.BookFavorateQualifier.BookFavorateFlagMethodQualifier;
import com.global.book.service.BookFavorateService;

@Mapper(uses = {AutherMapper.class, BookFavorateFlagQualifierImpl.class})
public interface BookMapper {

	@Mapping(target = "auther" , ignore = true)
	@Mapping(target = "autherName" , source = "auther.fullName")
	@Mapping(target = "autherEmail" , source = "auther.email")
	@Mapping(source = "." , target = "isFavorate" , qualifiedBy = {BookFavorateQualifier.class, BookFavorateFlagMethodQualifier.class})
	BookDto map(Book entity);

	@Mapping(source = "autherName" , target = "auther.fullName")
	@Mapping(source = "autherEmail" , target = "auther.email")
	Book unMap(BookDto dto);
	
	

}
