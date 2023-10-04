package com.example.coursework2.DTO;

import com.example.coursework2.constant.Department;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MaxSalaryDTO {

	private Department department;
	private Double maxSalary;
}
