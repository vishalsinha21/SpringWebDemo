package org.vs.dao;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.vs.domain.Employee;
import org.vs.domain.ObjectMotherEmployee;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/applicationContext.xml"})
@Transactional
public class EmployeeDaoImplTest {

    @Autowired
    private EmployeeDaoImpl employeeDaoImpl;

    @Test
    public void should_create_and_get_employee() {
        Employee employee = ObjectMotherEmployee.getEmployee(BigInteger.ONE);
        employeeDaoImpl.createEmployee(employee);

        Employee result = employeeDaoImpl.getEmployeeById(BigInteger.ONE);

        assertEquals(employee.getEmployeeId(), result.getEmployeeId());
        assertEquals(employee.getFirstName(), result.getFirstName());
        assertEquals(employee.getLastName(), result.getLastName());
        assertEquals(employee.getPhone(), result.getPhone());
        assertEquals(employee.getJoiningDate().toString(), result.getJoiningDate().toString());
    }

    @Test(expected = DuplicateKeyException.class)
    public void should_throw_exception_when_creating_employee_with_same_id() {
        Employee employee = ObjectMotherEmployee.getEmployee(BigInteger.ONE);
        employeeDaoImpl.createEmployee(employee);
        employeeDaoImpl.createEmployee(employee);
    }

    @Test
    public void should_update_phone_for_employee_id() {
        Employee employee = ObjectMotherEmployee.getEmployee(BigInteger.ONE);
        String newPhoneNo = "9837 1983";
        employeeDaoImpl.createEmployee(employee);

        employeeDaoImpl.updatePhoneForEmpId(BigInteger.ONE, newPhoneNo);

        Employee result = employeeDaoImpl.getEmployeeById(BigInteger.ONE);

        assertEquals(employee.getEmployeeId(), result.getEmployeeId());
        assertEquals(employee.getFirstName(), result.getFirstName());
        assertEquals(employee.getLastName(), result.getLastName());
        assertEquals(newPhoneNo, result.getPhone());
        assertEquals(employee.getJoiningDate().toString(), result.getJoiningDate().toString());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void should_throw_empty_result_exception_when_retrieving_deleted_employee() {
        Employee employee = ObjectMotherEmployee.getEmployee(BigInteger.ONE);
        employeeDaoImpl.createEmployee(employee);

        employeeDaoImpl.deleteEmployee(BigInteger.ONE);

        employeeDaoImpl.getEmployeeById(BigInteger.ONE);
    }

    @Test
    public void should_create_and_get_all_employees() {
        Employee employee1 = ObjectMotherEmployee.getEmployee(BigInteger.valueOf(1));
        Employee employee2 = ObjectMotherEmployee.getEmployee(BigInteger.valueOf(2));
        Employee employee3 = ObjectMotherEmployee.getEmployee(BigInteger.valueOf(3));
        employeeDaoImpl.createEmployee(employee1);
        employeeDaoImpl.createEmployee(employee2);
        employeeDaoImpl.createEmployee(employee3);

        List<Employee> empList = employeeDaoImpl.getAllEmployees();

        assertTrue(empList.size() >= 3);
    }

    @Test
    public void should_update_employee_details_for_emp_id() {
        Employee employee = ObjectMotherEmployee.getEmployee(BigInteger.ONE);
        employeeDaoImpl.createEmployee(employee);

        employee.setPhone("4564 64564");
        employee.setFirstName("Steve");
        employee.setLastName("Jobs");
        employee.setJoiningDate(LocalDate.parse("2015-01-01"));
        employeeDaoImpl.updateEmployee(employee);

        Employee result = employeeDaoImpl.getEmployeeById(BigInteger.ONE);

        assertEquals(employee.getEmployeeId(), result.getEmployeeId());
        assertEquals(employee.getFirstName(), result.getFirstName());
        assertEquals(employee.getLastName(), result.getLastName());
        assertEquals(employee.getPhone(), result.getPhone());
        assertEquals(employee.getJoiningDate().toString(), result.getJoiningDate().toString());
    }

}