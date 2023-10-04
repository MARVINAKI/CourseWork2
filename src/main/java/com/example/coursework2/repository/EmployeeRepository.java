package com.example.coursework2.repository;

import com.example.coursework2.constant.Department;
import com.example.coursework2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	/**
	 * Поиск сотрудника по имени и отделу
	 *
	 * @param name имя сотрудника
	 * @param department отдел сотрудника
	 * @return сотрудник
	 */
	Optional<Employee> getByNameAndAndDepartment(String name, Department department);

	/**
	 * Поиск отделов с сотрудниками больше 1
	 * @return список отделов
	 */
	@Query("select e.department, count(e.department) from Employee e group by e.department having count(e.department) > 1 ")
	List<Department> getDuplicateDepartment();

	/**
	 * Поиск средней зарплаты сотрудников по отделу
	 *
	 * @param department отдел
	 * @return средняя зарплата сотрудников
	 */
	@Query("select sum(e.salary) / count(e.department) from Employee e WHERE e.department = ?1")
	Double getAverageSalaryByDepartment(Department department);

	/**
	 * Поиск максимальной зарплаты сотрудника в отделе
	 *
	 * @param department отдел
	 * @return максимальная зарплата
	 */
	@Query("select max(e.salary) from Employee e where e.department = ?1")
	Double getMaxSalaryByDepartment(Department department);

}
