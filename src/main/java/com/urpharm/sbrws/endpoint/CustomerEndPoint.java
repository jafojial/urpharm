/**
 * 
 */
package com.urpharm.sbrws.endpoint;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.urpharm.sbrws.dto.CustomerDto;
import com.urpharm.sbrws.dto.CustomerResponse;
import com.urpharm.sbrws.entity.Customer;
import com.urpharm.sbrws.service.CustomerService;
import com.urpharm.sbrws.util.AppConstants;

/**
 * @author HOME
 *
 */
@RestController
@RequestMapping("/urpharm-sbrws/api/v1")
public class CustomerEndPoint {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CustomerEndPoint.class);
	
	@Autowired
	private CustomerService customerService;
	
	
	
	@InitBinder
    public void initBinder (WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
    }
	
	
	@PostMapping(path="/customers/add", produces = "application/json")
	public ResponseEntity<CustomerDto> customerAddCustomer(@RequestBody CustomerDto custDto) throws Exception {

		LOGGER.info("[CUSTOMER INFO : "+custDto);
		CustomerDto customer = null;
		
		customer = customerService.createCustomer(custDto);
		
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/customers", produces = "application/json")
	public List<Customer> getAllCustomers() {
	    
		return customerService.getCustomers();
	}
	
	
	/*
	 * @GetMapping(value="/customers", produces = "application/json") public
	 * ResponseEntity<List<Customer>> getAllEmployees(
	 * 
	 * @RequestParam(defaultValue = "0") Integer pageNo,
	 * 
	 * @RequestParam(defaultValue = "10") Integer pageSize,
	 * 
	 * @RequestParam(defaultValue = "id") String sortBy) { List<Customer> list =
	 * customerService.getCustomers(pageNo, pageSize, sortBy);
	 * 
	 * return new ResponseEntity<List<Customer>>(list, new HttpHeaders(),
	 * HttpStatus.OK); }
	 */
	
	
	@GetMapping(value="/customers/paginated", produces = "application/json")
    public CustomerResponse getAllCustomers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return customerService.getCustomers(pageNo, pageSize, sortBy, sortDir);
    }

	
	
	@GetMapping(value="/customers/find", produces = "application/json")
	public List<Customer> getCustomers(@RequestParam(value="name", required=false, defaultValue="") String name) {
		
		LOGGER.info("[CUSTOMER SEARCH SPECIALITY : " + name);
	    
		return customerService.findCustomersByFirstnameContaining(name);
	}
	
	
	@GetMapping(value="/customers/active", produces = "application/json")
	public ResponseEntity <List<Customer>> getValidCustomers(@RequestParam(value="status", required=false, defaultValue="") Boolean status) {
		
		LOGGER.info("[CUSTOMER SEARCH ACTIVE : "+status);
	    
		return new ResponseEntity<>(customerService.findCustomersByActive(status), HttpStatus.OK);
	}
	
}