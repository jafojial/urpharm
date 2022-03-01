/**
 * 
 */
package com.urpharm.sbrws.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JAFOJIAL
 *
 * Customer entity
 */
@Entity
@Table(name="URPHARM_CUST")
@Data @NoArgsConstructor @AllArgsConstructor
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1730982540912307462L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String firstname;
	
	@Column(nullable = false)
	private String lastname;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private int child = 0;
	
	@Column
	private Boolean active = Boolean.TRUE;
	
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dob;
	
	@Column
	private Date createdAt = new Date();
	
}
