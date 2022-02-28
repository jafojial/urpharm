/**
 * 
 */
package com.urpharm.sbrws.service;

import java.util.List;

import com.urpharm.sbrws.dto.CustomerDto;
import com.urpharm.sbrws.dto.CustomerResponse;
import com.urpharm.sbrws.entity.Customer;

/**
 * @author HOME
 *
 */
public interface ICustomerService {
	
	public CustomerDto createCustomer(CustomerDto custDto) throws Exception;
	
	public Customer saveCustomer(Customer customer);
	
	public Customer getCustomer(Long id);
	
	public List<Customer> getCustomers();
	
	public CustomerResponse getCustomers(Integer pageNo, Integer pageSize, String sortBy, String sortDir);
	
	public List<Customer> getCustomerFirstnameContains(String firstname);
	
	public List<Customer> findCustomersByFirstnameLike(String firstname);
	
	public List<Customer> findCustomersByFirstnameContaining(String firstname);
	
	public List<Customer> findCustomersByLastnameLike(String lastname);
	
	public Customer getCustomerByEmail(String email);
	
	public List<Customer> findCustomersByChild(int child);
	
	public List<Customer> findCustomersByActive(Boolean status);
	
	public void deleteCustomer(Long id);
	
	public void deleteCustomer(Customer customer);
	
	
}
