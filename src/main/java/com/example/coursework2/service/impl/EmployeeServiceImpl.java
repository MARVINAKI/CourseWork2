package com.example.coursework2.service.impl;

import com.example.coursework2.DTO.AverageSalaryDTO;
import com.example.coursework2.DTO.EmployeeDTO;
import com.example.coursework2.DTO.MaxSalaryDTO;
import com.example.coursework2.constant.Department;
import com.example.coursework2.model.Employee;
import com.example.coursework2.repository.EmployeeRepository;
import com.example.coursework2.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	/**
	 * Проверка существования сотрудника
	 *
	 * @param name       имя сотрудника
	 * @param department отдел сотрудника
	 * @return <b>true/false</b>
	 */
	public boolean isExists(String name, Department department) {
		return employeeRepository.getByNameAndAndDepartment(name, department).isPresent();
	}

	/**
	 * Добавление сотрудника в БД
	 *
	 * @param employeeDTO класс DTO с основными полями сотрудника
	 */
	@Override
	public void addEmployee(EmployeeDTO employeeDTO) {
		Employee employee = Employee.convertToEmployee(employeeDTO);
		employeeRepository.save(employee);
	}

	/**
	 * Поиск сотрудника по идентификационному номеру сотрудника
	 *
	 * @param id идентификационный номер
	 * @return сотрудник
	 */
	@Override
	public Optional<Employee> findById(Integer id) {
		return employeeRepository.findById(id);
	}

	/**
	 * Поиск всех сотрудников
	 *
	 * @return список сотрудников
	 */
	@Override
	public List<EmployeeDTO> findAll() {
		return employeeRepository.findAll().stream()
				.map(Employee::convertToDto)
				.collect(Collectors.toList());
	}

	/**
	 * Удаление сотрудника из БД по идентификационному номеру
	 *
	 * @param id идентификационный номер
	 */
	@Override
	public void deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
	}

	/**
	 * Поиск отделов с количеством сотрудников больше одного
	 * и вычисление средней зарплаты по этим отделам
	 *
	 * @return список отделов с средней заработной платой
	 */
	@Override
	@Transactional
	public List<AverageSalaryDTO> getAverageSalaryByDepartment() {
		List<AverageSalaryDTO> listDTO = new ArrayList<>();
		List<Department> listDepartment = employeeRepository.getDuplicateDepartment();
		for (Department department : listDepartment) {
			AverageSalaryDTO averageSalaryDTO = new AverageSalaryDTO();
			averageSalaryDTO.setDepartment(department);
			averageSalaryDTO.setAverageSalary(employeeRepository.getAverageSalaryByDepartment(department));
			listDTO.add(averageSalaryDTO);
		}
		return listDTO;
	}

	/**
	 * Поиск отделов с количеством сотрудников больше одного
	 * и нахождение максимальной зарплаты в данных отделах
	 *
	 * @return список отделов с максимальной зарплатой
	 */
	@Override
	public List<MaxSalaryDTO> getMaxSalaryByDepartment() {
		List<MaxSalaryDTO> list = new ArrayList<>();
		List<Department> listDepartment = employeeRepository.getDuplicateDepartment();
		for (Department department : listDepartment) {
			MaxSalaryDTO maxSalaryDTO = new MaxSalaryDTO();
			maxSalaryDTO.setDepartment(department);
			maxSalaryDTO.setMaxSalary(employeeRepository.getMaxSalaryByDepartment(department));
			list.add(maxSalaryDTO);
		}
		return list;
	}
}
