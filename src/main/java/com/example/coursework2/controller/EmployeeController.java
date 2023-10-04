package com.example.coursework2.controller;

import com.example.coursework2.DTO.AverageSalaryDTO;
import com.example.coursework2.DTO.EmployeeDTO;
import com.example.coursework2.DTO.MaxSalaryDTO;
import com.example.coursework2.constant.Department;
import com.example.coursework2.model.Employee;
import com.example.coursework2.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Void> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		employeeService.addEmployee(employeeDTO);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{name}/{department}")
	public ResponseEntity<Boolean> checkExistenceOfEmployee(@PathVariable String name,
															@PathVariable Department department) {
		return employeeService.isExists(name, department) ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
	}

	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> findAllEmployee() {
		return ResponseEntity.ok(employeeService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable Integer id) {
		Optional<Employee> employee = employeeService.findById(id);
		return employee.map(value -> ResponseEntity.ok(Employee.convertToDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/average_salary")
	public ResponseEntity<List<AverageSalaryDTO>> findAverageSalaryByDepartment() {
		return ResponseEntity.ok(employeeService.getAverageSalaryByDepartment());
	}

	@GetMapping("/max_salary")
	public ResponseEntity<List<MaxSalaryDTO>> findMaxSalaryByDepartment() {
		return ResponseEntity.ok(employeeService.getMaxSalaryByDepartment());
	}
}
