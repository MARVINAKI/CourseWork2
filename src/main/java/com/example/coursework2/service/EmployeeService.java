package com.example.coursework2.service;

import com.example.coursework2.DTO.AverageSalaryDTO;
import com.example.coursework2.DTO.EmployeeDTO;
import com.example.coursework2.DTO.MaxSalaryDTO;
import com.example.coursework2.constant.Department;
import com.example.coursework2.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

	boolean isExists(String name, Department department);

	void addEmployee(EmployeeDTO employee);

	Optional<Employee> findById(Integer id);

	List<EmployeeDTO> findAll();

	void deleteEmployee(Integer id);

	List<AverageSalaryDTO> getAverageSalaryByDepartment();

	List<MaxSalaryDTO> getMaxSalaryByDepartment();
}
