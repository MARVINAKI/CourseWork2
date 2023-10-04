package com.example.coursework2.model;

import com.example.coursework2.DTO.EmployeeDTO;
import com.example.coursework2.constant.Department;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;


@Entity
@Table(name = "employees")
@Data
@RequiredArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "department", nullable = false, length = 50)
	@Enumerated(value = EnumType.STRING)
	private Department department;

	@Column(name = "salary", nullable = false)
	private Double salary;

	public static EmployeeDTO convertToDto(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setName(employee.getName());
		employeeDTO.setDepartment(employee.getDepartment());
		employeeDTO.setSalary(employee.getSalary());
		return employeeDTO;
	}

	public static Employee convertToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		employee.setName(employeeDTO.getName());
		employee.setDepartment(employeeDTO.getDepartment());
		employee.setSalary(employeeDTO.getSalary());
		return employee;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return name.equals(employee.name) && department.equals(employee.department) && salary.equals(employee.salary);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, department, salary);
	}
}
