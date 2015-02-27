package org.vs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vs.dao.EmployeeDaoImpl;
import org.vs.domain.Employee;

import java.math.BigInteger;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDaoImpl employeeDaoImpl;

    @Autowired
    public EmployeeService(EmployeeDaoImpl employeeDaoImpl) {
        this.employeeDaoImpl = employeeDaoImpl;
    }

    public void createEmployee(Employee employee) {
        employeeDaoImpl.createEmployee(employee);
    }

    public Employee getEmployee(BigInteger employeeId) {
        return employeeDaoImpl.getEmployeeById(employeeId);
    }

    public void updatePhoneForEmployeeId(BigInteger employeeId, String phone) {
        employeeDaoImpl.updatePhoneForEmpId(employeeId, phone);
    }

    public void deleteEmployee(BigInteger employeeId) {
        employeeDaoImpl.deleteEmployee(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return employeeDaoImpl.getAllEmployees();
    }

    public void updateEmployee(Employee employee) {
        employeeDaoImpl.updateEmployee(employee);
    }
}
