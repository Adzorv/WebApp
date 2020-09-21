//TODO refactor to DataJPATest with H2Database
/*
package nl.dagobank.webapp.dao;

import nl.dagobank.webapp.domain.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;
    private Employee employee;


    */
/*@BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setFirstName( "Jan" );
        employee.setPrefix( "van de" );
        employee.setLastName( "Jansen" );
        employee.setStreetName( "Overtoom" );
        employee.setHouseNumber( 23 );
        employee.setHouseNumberAnnex( "2 hoog" );
        employee.setPostCode( "1014AA" );
        employee.setCity( "Amsterdam" );
        employee.setPhoneNumber( "0612345678" );
        employee.setBirthDate( LocalDate.of( 2000, 11, 11 ) );
        employee.setEmail( "janj@gmail.com" );
        employee.setBsn( 111222333 );
        employee.setPassword( "test2" );
        employee.setUserName( "test2" );
        employee.setRole( "HoofdMKB" );
        employeeDao.save( employee );

    }*//*


    @AfterEach
    void tearDown() {
        employeeDao.delete( employee );
    }


    @Test
    void findByUserName() {
        Optional<Employee> optionalEmployee = employeeDao.findByInlogCredentialsUserName( "test2" );
        assertTrue( optionalEmployee.isPresent() );
        assertEquals( 111222333, optionalEmployee.get().getPersonalDetails().getBsn() );

    }

    @Test
    void findByRole() {
        Optional<Employee> optionalEmployee = employeeDao.findByRole( "HoofdMKB" );
        assertTrue( optionalEmployee.isPresent() );
        assertEquals( 111222333, optionalEmployee.get().getPersonalDetails().getBsn() );

        Optional<Employee> optionalEmployee1 = employeeDao.findByRole( "hoofdmkb" );
        assertTrue( optionalEmployee1.isPresent() );
    }
}*/
