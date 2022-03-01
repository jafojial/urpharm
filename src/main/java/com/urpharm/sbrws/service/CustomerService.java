/**
 * 
 */
package com.urpharm.sbrws.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.urpharm.sbrws.dto.CustomerDto;
import com.urpharm.sbrws.dto.CustomerResponse;
import com.urpharm.sbrws.entity.Customer;
import com.urpharm.sbrws.exception.CustomerAlreadyExistException;
import com.urpharm.sbrws.exception.CustomerInvalidParameterException;
import com.urpharm.sbrws.repository.CustomerRepository;


/**
 * @author JAFOJIAL
 *
 */
@Repository
public class CustomerService implements ICustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	

	@Override
	public CustomerDto createCustomer(CustomerDto custDto) throws Exception {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(custDto.getFirstname()))
			throw new CustomerInvalidParameterException("Firstname can not be empty or null");
		
		if(StringUtils.isBlank(custDto.getLastname()))
			throw new CustomerInvalidParameterException("Lastname can not be empty or null");
		
		if(StringUtils.isBlank(custDto.getEmail()) || !EmailValidator.getInstance().isValid(custDto.getEmail()))
			throw new CustomerInvalidParameterException("Email address can not be empty or null and must be a valid Email address");
		
		if(customerRepository.findByEmail(custDto.getEmail()).isPresent())
			throw new CustomerAlreadyExistException("A customer with this Email address already exist");
		
		// Convert DTO to entity
        Customer customer = mapToEntity(custDto);
        Customer newCustomer = customerRepository.save(customer);

        // Convert customer entity to DTO
        CustomerDto customerResponse = mapToDTO(newCustomer);
		
		return customerResponse;
	}
	
	
	@Override
	public CustomerResponse getCustomers(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
		
	    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Customer> customers = customerRepository.findAll(pageable);

        // get content for page object
        List<Customer> listOfCustomers = customers.getContent();

        List<CustomerDto> content= listOfCustomers.stream().map(customer -> mapToDTO(customer)).collect(Collectors.toList());

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomers(content);
        customerResponse.setPageNo(customers.getNumber());
        customerResponse.setPageSize(customers.getSize());
        customerResponse.setTotalElements(customers.getTotalElements());
        customerResponse.setTotalPages(customers.getTotalPages());
        customerResponse.setLast(customers.isLast());

        return customerResponse;
	}
	
	
	// convert Customer Entity into DTO
    private CustomerDto mapToDTO(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstname(customer.getFirstname());
        customerDto.setLastname(customer.getLastname());
        customerDto.setEmail(customer.getEmail());
        customerDto.setDob(customer.getDob());
        customerDto.setChild(customer.getChild());
        customerDto.setCreatedAt(customer.getCreatedAt());
        
        return customerDto;
    }

    // convert DTO to entity
    private Customer mapToEntity(CustomerDto customerDto){
    	Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setEmail(customerDto.getEmail());
        customer.setDob(customerDto.getDob());
        customer.setChild(customerDto.getChild());
//        customer.setCreatedAt(customerDto.getCreatedAt());
        
        return customer;
    }

}
