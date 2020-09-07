package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.Customer;
import nl.dagobank.webapp.domain.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;
    private Employee employee;


    @BeforeEach
    void setUp() {
        employee= new Employee();
        employee.setFirstName("Jan");
        employee.setPrefix("van de");
        employee.setLastName("Jansen");
        employee.setStreetName("Overtoom");
        employee.setHouseNumber(23);
        employee.setHouseNumberAnnex("2 hoog");
        employee.setPostCode("1014AA");
        employee.setCity("Amsterdam");
        employee.setPhoneNumber("0612345678");
        employee.setBirthDate(LocalDate.of(2000, 11, 11));
        employee.setEmail("janj@gmail.com");
        employee.setBsn(111222333);
        employee.setPassword("test2");
        employee.setUserName("test2");
        employeeDao.save(employee);

    }

    @AfterEach
    void tearDown() {
        employeeDao.delete( employee );
    }


    @Test
    void findByUserName() {
        Optional<Employee> optionalEmployee = employeeDao.findByUserName( "test2" );
        assertTrue(optionalEmployee.isPresent());

    }
}