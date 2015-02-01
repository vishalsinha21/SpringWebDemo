package org.vs.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.vs.domain.Employee;
import org.vs.domain.ObjectMotherEmployee;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"file:src/main/resources/spring/applicationContext.xml"})

@Transactional
public class EmployeeDaoImplTest {

    @Autowired
    private EmployeeDaoImpl employeeDao;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void should_create_and_get_employee() {
        Employee employee = ObjectMotherEmployee.getEmployee(BigInteger.ONE);
        employeeDao.createEmployee(employee);
        Employee result = employeeDao.getEmployeeById(BigInteger.ONE);

        assertEquals(employee.getEmployeeId(), result.getEmployeeId());
    }

    @Test(expected = DuplicateKeyException.class)
    public void should_throw_exception_when_creating_employee_with_same_id() {
        Employee employee = ObjectMotherEmployee.getEmployee(BigInteger.ONE);
        employeeDao.createEmployee(employee);
        employeeDao.createEmployee(employee);
    }


}