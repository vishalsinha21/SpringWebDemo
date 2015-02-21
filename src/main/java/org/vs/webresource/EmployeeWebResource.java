package org.vs.webresource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vs.domain.Employee;
import org.vs.service.EmployeeService;

import java.math.BigInteger;
import java.util.List;

@RestController
public class EmployeeWebResource {

    EmployeeService employeeService;

    @Autowired
    public EmployeeWebResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable String employeeId) {
        return employeeService.getEmployee(new BigInteger(employeeId));
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}
