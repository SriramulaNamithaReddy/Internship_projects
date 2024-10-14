package in.Namitha.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.Namitha.entity.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
