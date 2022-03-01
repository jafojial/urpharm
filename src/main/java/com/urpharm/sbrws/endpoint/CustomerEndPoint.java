/**
 * 
 */
package com.urpharm.sbrws.endpoint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.urpharm.sbrws.dto.CustomerDto;
import com.urpharm.sbrws.dto.CustomerResponse;
import com.urpharm.sbrws.service.CustomerService;
import com.urpharm.sbrws.util.AppConstants;

/**
 * @author JAFOJIAL
 *
 */
@RestController
@RequestMapping("/urpharm-sbrws/api/v1")
public class CustomerEndPoint {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CustomerEndPoint.class);
	
	@Autowired
	private CustomerService customerService;
	
		
	@PostMapping(path="/customers/add", produces = "application/json")
	public ResponseEntity<CustomerDto> customerAddCustomer(@RequestBody CustomerDto custDto) throws Exception {

		LOGGER.debug("[CUSTOMER INFO : "+custDto);
		CustomerDto customer = null;
		
		customer = customerService.createCustomer(custDto);
		
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/customers", produces = "application/json")
    public CustomerResponse getAllCustomers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return customerService.getCustomers(pageNo, pageSize, sortBy, sortDir);
    }
	
}