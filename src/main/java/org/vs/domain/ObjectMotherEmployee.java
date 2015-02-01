package org.vs.domain;

import org.joda.time.LocalDate;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Date;

public class ObjectMotherEmployee {

    public static Employee getEmployee(BigInteger employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFirstName("Vishal");
        employee.setLastName("Sinha");
        employee.setPhone("96888 09809");
        employee.setJoiningDate(new LocalDate());

        return employee;
    }

}
