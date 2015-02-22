package org.vs.webresource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.vs.domain.Employee;
import org.vs.domain.ObjectMotherEmployee;
import org.vs.service.EmployeeService;
import org.vs.webresource.EmployeeWebResource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeWebResourceTest {

    EmployeeWebResource employeeWebResource;

    @Mock
    EmployeeService employeeService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        employeeWebResource = new EmployeeWebResource(employeeService);
    }

    @Test
    public void should_get_employee_by_id() throws Exception {
        BigInteger empId = BigInteger.ONE;
        when(employeeService.getEmployee(empId)).thenReturn(ObjectMotherEmployee.getEmployee(empId));

        Employee employee = employeeWebResource.getEmployeeById(empId.toString()).getBody();
        verify(employeeService).getEmployee(empId);
        assertEquals(empId, employee.getEmployeeId());
    }

    @Test
    public void should_delete_employee_by_id() throws Exception {
        BigInteger empId = BigInteger.ONE;

        employeeWebResource.deleteEmployee(empId.toString());
        verify(employeeService).deleteEmployee(empId);
    }

    @Test
    public void should_create_employee() throws Exception {
        BigInteger empId = BigInteger.ONE;
        Employee employee = ObjectMotherEmployee.getEmployee(empId);

        employeeWebResource.createEmployee(employee);
        verify(employeeService).createEmployee(employee);
    }

    @Test
    public void should_get_all_employees() throws Exception {
        Employee emp1 = ObjectMotherEmployee.getEmployee(BigInteger.valueOf(1));
        Employee emp2 = ObjectMotherEmployee.getEmployee(BigInteger.valueOf(2));
        Employee emp3 = ObjectMotherEmployee.getEmployee(BigInteger.valueOf(3));
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);

        when(employeeService.getAllEmployees()).thenReturn(employeeList);

        List result = employeeWebResource.getAllEmployees().getBody();
        verify(employeeService).getAllEmployees();
        assertEquals(employeeList.size(), result.size());
    }
}