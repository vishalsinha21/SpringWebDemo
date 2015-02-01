package org.vs.dao;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.vs.domain.Employee;
import util.DaoUtils;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class EmployeeDaoImpl {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(@Qualifier("dataSource") DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void createEmployee(Employee employee) {
        String columns = "EmployeeId, FirstName, LastName, Phone, JoiningDate";
        String valuesNames = ":EmployeeId, :FirstName, :LastName, :Phone, :JoiningDate";
        String sql = "INSERT INTO Employee (" + columns + ") VALUES (" + valuesNames + ")";

        MapSqlParameterSource input = new MapSqlParameterSource();
        input.addValue("EmployeeId", employee.getEmployeeId().longValue());
        input.addValue("FirstName", employee.getFirstName());
        input.addValue("LastName", employee.getLastName());
        input.addValue("Phone", employee.getPhone());
        input.addValue("JoiningDate", DaoUtils.convertToDateOrNull(employee.getJoiningDate()));

        namedJdbcTemplate.update(sql, input);
    }

    public Employee getEmployeeById(BigInteger employeeId) {
        String SQL = "SELECT * FROM Employee where employeeId = ?";
        return jdbcTemplate.queryForObject(SQL, new EmployeeRowMapper(), employeeId.longValue());
    }

    public void updatePhoneForEmpId(BigInteger employeeId, String phoneNo) {
        String SQL = "UPDATE Employee set phone = ? where employeeId = ?";
        jdbcTemplate.update(SQL, phoneNo, employeeId.longValue());
    }

    public void deleteEmployee(BigInteger employeeId) {
        String SQL = "DELETE from Employee where employeeId = ?";
        jdbcTemplate.update(SQL, employeeId.longValue());
    }

    private class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setEmployeeId(BigInteger.valueOf(rs.getLong("EmployeeId")));
            employee.setFirstName(rs.getString("FirstName"));
            employee.setLastName(rs.getString("LastName"));
            employee.setPhone(rs.getString("Phone"));
            employee.setJoiningDate(DaoUtils.sqlTimestampToJodaDateTime(rs.getDate("JoiningDate")));
            return employee;
        }
    }

}


