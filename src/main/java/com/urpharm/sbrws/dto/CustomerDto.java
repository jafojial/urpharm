/**
 * 
 */
package com.urpharm.sbrws.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Customer Dto
 * 
 * @author JAFOJIAL
 *
 * Customer Dto
 */
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class CustomerDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	@NotBlank
	private String firstname;
	
	@NotBlank
	private String lastname;
	
	@NotBlank
	@Email
	private String email;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	
	private Integer child = 0;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	
	
	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 */
	public CustomerDto(String firstname, String lastname, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param child
	 * @param dob
	 */
	public CustomerDto(String firstname, String lastname, String email, int child, Date dob) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.child = child;
		this.dob = dob;
	}
	
}
