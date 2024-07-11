package lk.SMP.bo.custom;

import lk.SMP.bo.SuperBO;
import lk.SMP.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException;

    ArrayList<Employee> getAllEmployees() throws SQLException, ClassNotFoundException;

    boolean updateEmployee(Employee c) throws SQLException, ClassNotFoundException;

    Employee searchEmployee(String id) throws SQLException, ClassNotFoundException;

    List<String> getIds() throws SQLException, ClassNotFoundException;
}

