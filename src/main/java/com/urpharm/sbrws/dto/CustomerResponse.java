/**
 * 
 */
package com.urpharm.sbrws.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JAFOJIAL
 *
 *Customer Response objet Dto
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerResponse {

	private List<CustomerDto> customers;
	
    private int pageNo;
    
    private int pageSize;
    
    private long totalElements;
    
    private int totalPages;
    
    private boolean last;
    
}
