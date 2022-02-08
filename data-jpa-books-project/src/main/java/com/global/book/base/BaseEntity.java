package com.global.book.base;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity <ID> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private ID id;
	
	private String statusCode;
	
	private boolean isDeleted;
	
	@CreatedBy
	private String createdBy;
	
	@CreatedDate
	private Date createdDate;
	
	@LastModifiedBy
    private String lastModifiedBy;
	
	@LastModifiedDate
	private Date lastModifiedDate;

	

}
