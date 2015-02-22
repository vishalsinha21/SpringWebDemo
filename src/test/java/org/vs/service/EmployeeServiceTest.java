package org.vs.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.vs.dao.EmployeeDaoImpl;
import org.vs.domain.Employee;
import org.vs.domain.ObjectMotherEmployee;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeServiceTest {

    @Mock
    EmployeeDaoImpl employeeDaoMock;

    EmployeeService employeeService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        employeeService = new EmployeeService(employeeDaoMock);
    }

    @Test
    public void should_create_employee() throws Exception {
        Employee employee = ObjectMotherEmployee.getEmployee(BigInteger.ONE);

        employeeService.createEmployee(employee);

        verify(employeeDaoMock).createEmployee(employee);
    }

    @Test
    public void should_get_employee_for_employee_id() throws Exception {
        Employee employee = ObjectMotherEmployee.getEmployee(BigInteger.ONE);
        when(employeeDaoMock.getEmployeeById(BigInteger.ONE)).thenReturn(employee);

        Employee result = employeeService.getEmployee(BigInteger.ONE);

        assertEquals(employee.getEmployeeId(), result.getEmployeeId());
        assertEquals(employee.getFirstName(), result.getFirstName());
        assertEquals(employee.getLastName(), result.getLastName());
        assertEquals(employee.getPhone(), result.getPhone());
        assertEquals(employee.getJoiningDate(), result.getJoiningDate());
    }

    @Test
    public void should_update_phone_for_employee_id() throws Exception {
        String phone = "432434234";
        employeeService.updatePhoneForEmployeeId(BigInteger.ONE, phone);

        verify(employeeDaoMock).updatePhoneForEmpId(BigInteger.ONE, phone);
    }

    @Test
    public void should_delete_employee() throws Exception {
        employeeService.deleteEmployee(BigInteger.ONE);

        verify(employeeDaoMock).deleteEmployee(BigInteger.ONE);
    }

    @Test
    public void should_get_all_employees() throws Exception {
        Employee employee1 = ObjectMotherEmployee.getEmployee(BigInteger.valueOf(1));
        Employee employee2 = ObjectMotherEmployee.getEmployee(BigInteger.valueOf(2));
        Employee employee3 = ObjectMotherEmployee.getEmployee(BigInteger.valueOf(3));
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        when(employeeDaoMock.getAllEmployees()).thenReturn(employeeList);

        List result = employeeService.getAllEmployees();

        assertEquals(employeeList.size(), result.size());
    }

}