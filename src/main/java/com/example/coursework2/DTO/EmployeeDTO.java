package com.example.coursework2.DTO;

import com.example.coursework2.constant.Department;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmployeeDTO {
	private String name;
	private Department department;
	private Double salary;
}
