package in.Namitha.service;

import java.util.List;
import java.util.Optional;

import in.Namitha.entity.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	Optional<Employee> getEmployeeById(Long idno);
	Employee saveEmployee(Employee employee);
	Employee updateEmployee(Employee employee);
	void deleteEmployeeById(Long idno);

}
