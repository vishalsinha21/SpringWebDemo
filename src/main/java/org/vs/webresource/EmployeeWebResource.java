package org.vs.webresource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.vs.domain.Employee;
import org.vs.domain.ObjectMotherEmployee;

import java.math.BigInteger;

@RestController
public class EmployeeWebResource {

    @RequestMapping(value = "/employee/get", method = RequestMethod.GET)
    public Employee getEmployeeById() {
        return ObjectMotherEmployee.getEmployee(BigInteger.ONE);
    }

}
