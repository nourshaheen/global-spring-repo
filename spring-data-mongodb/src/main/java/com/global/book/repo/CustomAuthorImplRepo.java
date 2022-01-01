package com.global.book.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.global.book.entity.Author;
import com.mongodb.client.result.UpdateResult;

@Component
public class CustomAuthorImplRepo implements CustomAuthorRepo {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public void updateEmail(String email, String name , String phone) {
		
		Query query = new Query(Criteria.where("email").is(email));
        Update update = new Update();
        update.set("name", name);
        update.set("phone", phone);
        
        UpdateResult result = mongoTemplate.updateFirst(query, update, Author.class);
        
        if(result == null)
            System.out.println("No documents updated");
        else
            System.out.println(result.getModifiedCount() + " document(s) updated..");

    }
	

}
