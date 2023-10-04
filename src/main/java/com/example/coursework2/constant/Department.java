package com.example.coursework2.constant;

public enum Department {
	WORKSHOP("Цех"),
	SHOP("Магазин"),
	BOOKKEEPING("Бухгалтерия"),
	WAREHOUSE("Склад");

	private final String department;

	Department(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}
}
