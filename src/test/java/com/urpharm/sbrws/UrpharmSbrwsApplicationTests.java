package com.urpharm.sbrws;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.urpharm.sbrws.endpoint.CustomerEndPoint;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UrpharmSbrwsApplicationTests {

	@Autowired
	CustomerEndPoint customerEndPoint;
	
	
	/**
	 * Test if the application context loads correctly
	 */
	@Test
	public void contextLoads() {
		Assertions.assertThat(customerEndPoint).isNotNull();
	}
	

}
