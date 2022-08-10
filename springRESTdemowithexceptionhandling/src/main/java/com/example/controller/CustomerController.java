package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CustomerDTO;
import com.example.exception.CustomerException;
import com.example.service.CustomerService;

// http://localhost:9090/employees
@RestController
@RequestMapping("/xyzcompany")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private Environment environment;

	// http://localhost:9090/xyzcompany/hello

	/*
	 * @GetMapping("/hello") public String welcomeToMyRestApplication() { return
	 * "Coming Soon!!!!!"; }
	 */

	// http://localhost:9090/xyzcompany/hello2
	// List<String> list=new ArrayList<>();
	// ResponseEntity<String> str=new ResponseEntity<>();

	/*
	 * @GetMapping("/hello2") public ResponseEntity<String>
	 * welcomeToMyRestApplication2() { ResponseEntity<String> str=new
	 * ResponseEntity<>("Hello",HttpStatus.CREATED); return str; }
	 */

	// GET--->// http://localhost:9090/xyzcompany/customers/1 // @GetMapping //===>
	// reading
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") int customerId) throws CustomerException {
		//try {
			CustomerDTO customerDTO = customerService.getCustomer(customerId);
			return new ResponseEntity<>(customerDTO, HttpStatus.OK);

		//} catch (CustomerException exception) {
		//	return new ResponseEntity<>(environment.getProperty(exception.getMessage()),
		//			HttpStatus.INTERNAL_SERVER_ERROR);
	//	}
	}
	// POST--->// http://localhost:9090/xyzcompany/customers // @PostMapping
	// //=====> adding

	@PostMapping("/customers")
	public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerException {
		customerService.addCustomer(customerDTO);
		String message = environment.getProperty("Controller.INSERT_SUCCESS");
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

//  DELETE--->// http://localhost:9090/xyzcompany/customers/4     @DeleteMapping // ======> delete
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int customerId) throws CustomerException {
		customerService.deleteCustomer(customerId);
		String message = environment.getProperty("Controller.DELETE_SUCCESS");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

// PUT ----> http://localhost:9090/xyzcompany/customers/2  //	@PutMapping  // ===> update
	@PutMapping("/customers/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable("id") int customerId,
			@RequestBody CustomerDTO customerDTO) throws CustomerException {
		customerService.updateCustomer(customerId, customerDTO.getEmailId());
		String message = environment.getProperty("Controller.UPDATE_SUCCESS");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// GET----> http://localhost:9090/xyzcompany/customers
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDTO>> getCustomers() throws CustomerException {
		List<CustomerDTO> customerDTOList = customerService.findAll();
		return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
	}

}
