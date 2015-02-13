package org.vs.webresource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.vs.domain.Employee;
import org.vs.domain.ObjectMotherEmployee;
import org.vs.service.EmployeeService;

import java.math.BigInteger;

@RestController
public class EmployeeWebResource {

    EmployeeService employeeService;

    @Autowired
    public EmployeeWebResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employee/get", method = RequestMethod.GET)
    public Employee getEmployeeById(String employeeId) {
        return employeeService.getEmployee(new BigInteger(employeeId));
    }

}
