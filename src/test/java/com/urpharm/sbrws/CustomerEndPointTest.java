/**
 * 
 */
package com.urpharm.sbrws;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urpharm.sbrws.dto.CustomerDto;
import com.urpharm.sbrws.dto.CustomerResponse;
import com.urpharm.sbrws.endpoint.CustomerEndPoint;
import com.urpharm.sbrws.service.CustomerService;

/**
 * @author JAFOJIAL
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerEndPoint.class)
public class CustomerEndPointTest {
	
	@MockBean
	CustomerService customerService;

	@Autowired
	MockMvc mockMvc;
	
	
	/**
	 * Test adding new customer endpoint
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAdd() throws Exception {
		
		CustomerDto customer = new CustomerDto("Alex", "JAZA", "alex.jaza@urpharm.com");
		CustomerDto customerDto = new CustomerDto(1L, "Alex", "JAZA", "alex.jaza@urpharm.com", null, 0, Calendar.getInstance().getTime());
		
		Mockito.when(customerService.createCustomer(customer)).thenReturn(customerDto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/urpharm-sbrws/api/v1/customers/add")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(customer))
		        .accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isCreated())
		        .andExpect(jsonPath("$.id", Matchers.is(1)))
		        .andExpect(jsonPath("$.firstname", Matchers.is("Alex")))
		        .andExpect(jsonPath("$.lastname", Matchers.is("JAZA")))
		        .andExpect(jsonPath("$.email", Matchers.is("alex.jaza@urpharm.com")));
		
	}
	
	
	
	/**
	 * Test getting paginated customer list endpoint
	 * 
	 * @throws Exception
	 */
	@Test
	public void testfindAll() throws Exception {
		
		CustomerDto customerDto = new CustomerDto(1L, "Alex", "JAZA", "alex.jaza@urpharm.com", null, 0, Calendar.getInstance().getTime());
		List<CustomerDto> customers = Arrays.asList(customerDto);
		CustomerResponse custResp = new CustomerResponse(customers, 0, 10, 1L, 1, true);
 
		Mockito.when(customerService.getCustomers(0, 10, "id", "asc")).thenReturn(custResp);
 
		mockMvc.perform(get("/urpharm-sbrws/api/v1/customers"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.customers", Matchers.hasSize(1)))
			.andExpect(jsonPath("$.customers[0].id", Matchers.is(1)))
	        .andExpect(jsonPath("$.customers[0].firstname", Matchers.is("Alex")))
	        .andExpect(jsonPath("$.customers[0].lastname", Matchers.is("JAZA")))
	        .andExpect(jsonPath("$.customers[0].email", Matchers.is("alex.jaza@urpharm.com")))
	        .andExpect(jsonPath("$.pageNo", Matchers.is(0)))
	        .andExpect(jsonPath("$.pageSize", Matchers.is(10)))
	        .andExpect(jsonPath("$.totalElements", Matchers.is(1)))
	        .andExpect(jsonPath("$.totalPages", Matchers.is(1)))
	        .andExpect(jsonPath("$.last", Matchers.is(true)));
		
	}
	
	
	/**
	 * Test getting paginated customer list endpoint with not existing data page number
	 * 
	 * @throws Exception
	 */
	@Test
	public void testfindAllWrongPage() throws Exception {
		
		Mockito.when(customerService.getCustomers(0, 10, "id", "asc")).thenReturn(new CustomerResponse(new ArrayList<>(), 1, 5, 1L, 1, true));
		 
		mockMvc.perform(get("/urpharm-sbrws/api/v1/customers", 1, 5, "id", "asc"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.customers", Matchers.hasSize(0)))
			.andExpect(jsonPath("$.pageNo", Matchers.is(1)))
	        .andExpect(jsonPath("$.pageSize", Matchers.is(5)))
	        .andExpect(jsonPath("$.totalElements", Matchers.is(1)))
	        .andExpect(jsonPath("$.totalPages", Matchers.is(1)))
	        .andExpect(jsonPath("$.last", Matchers.is(true)));
	}
	
	
	/**
	 * Convert oject to a json string
	 * 
	 * @param obj the oject to convert
	 * @return the converted objet in json format
	 */
	static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
