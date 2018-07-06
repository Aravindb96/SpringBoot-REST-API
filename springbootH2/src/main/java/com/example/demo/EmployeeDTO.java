package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class EmployeeDTO {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long emp_id;
	
	String position;
	public EmployeeDTO() {
		
	}
	public EmployeeDTO( String position) {
		super();
		
		this.position = position;
	}
	public Long getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", position=" + position + "]";
	}
	
}
