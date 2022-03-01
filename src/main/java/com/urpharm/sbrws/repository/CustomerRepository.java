/**
 * 
 */
package com.urpharm.sbrws.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.urpharm.sbrws.entity.Customer;

/**
 * @author JAFOJIAL
 *
 * Customer repository interface
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
	
	public List<Customer> findByFirstnameLikeIgnoreCase(String firstname);
	
	public List<Customer> findByFirstnameContainingIgnoreCase(String firstname);
	
	public List<Customer> findByLastnameLikeIgnoreCase(String lastname);
	
	public Optional<Customer> findByFirstname(String firstname);
	
	public Optional<Customer> findByLastname(String lastname);
	
	public Optional<Customer> findByEmail(String email);
	
	public List<Customer> findByChild(int child);
	
	public List<Customer> findByActive(Boolean status);
	
}
