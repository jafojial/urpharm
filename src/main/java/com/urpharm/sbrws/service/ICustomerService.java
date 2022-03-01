/**
 * 
 */
package com.urpharm.sbrws.service;

import com.urpharm.sbrws.dto.CustomerDto;
import com.urpharm.sbrws.dto.CustomerResponse;

/**
 * @author JAFOJIAL
 *
 */
public interface ICustomerService {
	
	public CustomerDto createCustomer(CustomerDto custDto) throws Exception;
	
	public CustomerResponse getCustomers(Integer pageNo, Integer pageSize, String sortBy, String sortDir);
	
}
