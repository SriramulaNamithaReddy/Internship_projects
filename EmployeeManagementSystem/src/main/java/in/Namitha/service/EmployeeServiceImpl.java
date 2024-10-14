package in.Namitha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.Namitha.entity.Employee;
import in.Namitha.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	private final EmployeeRepo employeeRepo;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
		this.employeeRepo=employeeRepo;
	}
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long idno) {
		return employeeRepo.findById(idno);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		if(employee.getIdno()==null || !employeeRepo.existsById(employee.getIdno())) {
			throw new RuntimeException("Employee not found");
		}
		return employeeRepo.save(employee);
	}

	@Override
	public void deleteEmployeeById(Long idno) {
		employeeRepo.deleteById(idno);
		
	}
	
}
