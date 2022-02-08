package com.global.book.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Book;
import com.global.book.entity.BookDto;
import com.global.book.repository.BookRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookService extends BaseService<Book, Long> {

	private final BookRepo bookRepo;
	
    private final static String USERS_PROC = ".INSERT_JP_USERS";
   
    private final EntityManager entityManager;
    
    private final Environment env;
	
	
    public Book addUsers(Book book) {
        String dbName = env.getProperty("spring.jpa.properties.hibernate.default_schema");
        
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery(dbName + USERS_PROC);
        
        query.registerStoredProcedureParameter("Email_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("First_Name_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Middle_Name_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Last_Name_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Gender_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Phone_Number_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Summary_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Experience_Param", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Profile_Img_Param", String.class, ParameterMode.IN);
        
        query.registerStoredProcedureParameter("Result_Param", Integer.class, ParameterMode.OUT);
        log.info("=========>> ");
//        query.setParameter("Email_Param", book.getEmail());
//        query.setParameter("First_Name_Param", book.getFirstName());
//        query.setParameter("Middle_Name_Param", book.getMiddleName());
//        query.setParameter("Last_Name_Param", book.getLastName());
//        query.setParameter("Gender_Param", book.getGender());
//        query.setParameter("Phone_Number_Param", book.getPhoneNumber());
//        query.setParameter("Summary_Param", book.getSummary());
//        query.setParameter("Experience_Param", book.getExperience());
//        query.setParameter("Profile_Img_Param", book.getProfileImg());
        
        int count = ((Number) query.getOutputParameterValue("Result_Param")).intValue();
        
//        if (count == 1) {
//            AddUserResponse usrResp = new AddUserResponse(Constant.STATUS_TRUE, Constant.SUCCESS);
//            usrResp.setData(new AddUserSPResponse("User added successfully."));
//            return usrResp;
//        } else {
//            AddUserResponse usrResp = new AddUserResponse(Constant.STATUS_TRUE, Constant.SUCCESS);
//            usrResp.setData(new AddUserSPResponse("User updated successfully."));
//            return usrResp;
//        }
        
        return null;
    }
	
	
	
	
	
	public List<Book> insertAll(List<Book> entities) {
		
		BookDto dto = BookDto.builder()
				.id(20L)
				.name("Java")
				.price(300.50)
				.build();
			
		
		return bookRepo.saveAll(entities);
	}

	public Book update(Book entity) {

		Book book = findById(entity.getId());

		book.setName(entity.getName());

		return update(book);
	}

	
	public int deleteByAutherId (Long id) {
		
		return bookRepo.deleteByAutherId(id);
	}

}
