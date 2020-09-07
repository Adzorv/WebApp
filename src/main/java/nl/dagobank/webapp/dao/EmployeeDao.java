package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {
    Optional<Employee> findByUserName( String userName);
    Optional<Employee> findByRole( String role );
}
