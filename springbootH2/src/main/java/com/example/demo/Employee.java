package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long emp_id;
	
	
	
	String emp_name;
	String position;
	public Employee() {
		
	}
	public Employee(Long emp_id,String emp_name, String position) {
		super();
		this.emp_id=emp_id;
		this.emp_name = emp_name;
		this.position = position;
	}
	public Long getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", position=" + position + "]";
	}
	public byte[] getJson() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
