package org.vs.domain;

import java.math.BigInteger;
import java.util.Date;

public class ObjectMotherEmployee {

    public static Employee getEmployee(BigInteger employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFirstName("Vishal");
        employee.setLastName("Sinha");
        employee.setPhone("96888 09809");

        return employee;
    }

}
