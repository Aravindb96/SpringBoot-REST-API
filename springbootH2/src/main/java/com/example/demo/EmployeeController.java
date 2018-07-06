package com.example.demo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepo e;
	
	@Autowired
	EmployeeDTOrepo e1;
	
	@ApiOperation(value="Get all Employee details")
	//Get all Employee details
	@GetMapping(value="/employees",  produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Employee> getAllEmployees(){

		return e.findAll();
	}


	@ApiOperation(value="Get a particular Employee detail")
	//Get a particular Employee detail
	@GetMapping(value="/employee/{empId}", produces = { MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<Employee> getEmployee(@PathVariable(value = "empId") Long eid) {

		Optional<Employee> emp =e.findById(eid);
		return new ResponseEntity<Employee>(emp.get(),HttpStatus.OK);
	}


	
	@ApiOperation(value="Add a new Employee")
	//Add a new Employee
	@PostMapping("/employee")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee emp) {
		Employee s = e.save(emp);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(s.getEmp_id()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	
	@ApiOperation(value="Delete an Employee")
	//Delete an Employee
	@DeleteMapping(value="/employee/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteEmployee(@PathVariable(value = "id") Long eid) {

		e.deleteById(eid);
	}


	@ApiOperation(value="Update an Employee")
	//Update an Employee
	@PutMapping(value="/employee/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee emp, @PathVariable Long id) {
		Optional<Employee> o = e.findById(id);
		if (!o.isPresent())
			return ResponseEntity.notFound().build();
		emp.setEmp_id(id);
		e.save(emp);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value="Patch a particular detail of an Employee")
	//Patch a particular detail of an Employee
	@PatchMapping(value="/employee/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> PatchEmployeeDetail(@RequestBody EmployeeDTO emp, @PathVariable Long id) {
		Optional<EmployeeDTO> o = e1.findById(id);
		if (!o.isPresent())
			return ResponseEntity.notFound().build();
		emp.setEmp_id(id);
		e1.save(emp);
		return ResponseEntity.noContent().build();
	}

}
