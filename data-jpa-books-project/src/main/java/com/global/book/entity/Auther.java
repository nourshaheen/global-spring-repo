package com.global.book.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.global.book.base.BaseEntity;
import com.global.book.validator.IpAddress;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(name = "Auther Entity")
@SQLDelete(sql = "update authers set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
@Entity
@Table(name = "authers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Auther extends BaseEntity<Long> {
		
	@NotBlank
	private String fullName;
	
//	@Pattern(regexp = "^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$")
	@IpAddress()
	private String ipAddress;
	
	@Email(message = "{validation.constraints.email.message}")
	private String email;
	
	@Formula("(select count(*) from books book where book.auther_id = id)")
	private long bookCount;
	
//	@NotEmpty
//	@JsonManagedReference
//	@OneToMany(mappedBy = "auther" , cascade = CascadeType.ALL)
//	private List<Book> books = new ArrayList<>();
	
	private String imagePath;

}
