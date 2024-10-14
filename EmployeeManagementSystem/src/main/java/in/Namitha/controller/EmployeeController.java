package in.Namitha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.Namitha.entity.Employee;
import in.Namitha.service.EmployeeService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private final EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	
	@GetMapping
	public String listEmployees(Model model) {
		model.addAttribute("employees",employeeService.getAllEmployees());
		return "employees/list";
	}
	
	@GetMapping("/add")
	public String showAddForm(Employee employee) {
		return "employees/add";
	}
	@PostMapping("/add")
	public String addEmployee(@Valid Employee employee, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "employees/add";
		}
		employeeService.saveEmployee(employee);
		return "redirect:/employees";
	}
	@GetMapping("/edit/{idno}")
	public String showUpdateForm(@PathVariable("idno") Long idno,Model model) {
		Employee employee= employeeService.getEmployeeById(idno).orElseThrow();
		model.addAttribute("employee", employee);
		return "employees/edit";
	}
	
	@PostMapping("/update/{idno}")
	public String updateEmployee(@PathVariable("idno") Long idno,@Valid Employee employee, BindingResult result, Model model) {
		if(result.hasErrors()) {
			employee.setIdno(idno);
			return "employees/edit";
		}
		employeeService.updateEmployee(employee);
		return "redirect:/employees";
	}
	@GetMapping("/delete/{idno}")
	public String deleteEmployee(@PathVariable("idno") Long idno,Model model) {
		employeeService.deleteEmployeeById(idno);
		return "redirect:/employees";
		
	}
	
	@GetMapping("/{idno}")
	public String viewEmployee(@PathVariable("idno") Long idno,Model model) {
		Employee employee=employeeService.getEmployeeById(idno).orElseThrow();
		model.addAttribute("employee", employee);
		return "employees/view";
	}

}
