package org.vs.webresource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) {
        Employee employee = employeeService.getEmployee(new BigInteger(employeeId));
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(new BigInteger(employeeId));
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.PUT)
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return new ResponseEntity(HttpStatus.OK);
    }

}
