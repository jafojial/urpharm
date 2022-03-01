/**
 * 
 */
package com.urpharm.sbrws.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
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

	private String firstname;
	
	private String lastname;
	
	private String email;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	
	private Integer child = 0;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	
}
