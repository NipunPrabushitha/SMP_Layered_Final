package lk.SMP.DAO.custom.impl;

import lk.SMP.DAO.SQLUtil;
import lk.SMP.DAO.custom.EmployeeDAO;
import lk.SMP.entity.Employee;
import lk.SMP.model.CustomerDTO;
import lk.SMP.model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?)",
                employee.getEmployeeId(),
                employee.getName(),
                employee.getContactNumber(),
                employee.getFieldId(),
                employee.getUserId());
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE employee SET name = ?, contactNumber = ?, fieldId = ?, userId =  ? WHERE employeeId = ?",
                employee.getName(),
                employee.getContactNumber(),
                employee.getFieldId(),
                employee.getUserId(),
                employee.getEmployeeId());
    }

    @Override
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE employeeId = ?",employeeId);
    }

    @Override
    public  Employee search(String employeeId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE employeeId = ?", employeeId);
        if (resultSet.next()) {
            String name = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String fieldId = resultSet.getString(4);
            String userId = resultSet.getString(5);

            Employee employee = new Employee(employeeId,name,contact,fieldId,userId);

            return employee;
        }
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");

        List<Employee> employeeList = new ArrayList<>();

        while (resultSet.next()){
            String employeeId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contactNumber = resultSet.getString(3);
            String fieldId = resultSet.getString(4);
            String userId = resultSet.getString(5);

            Employee employee = new Employee(employeeId,name,contactNumber,fieldId,userId);

            employeeList.add(employee);
        }
        return employeeList;
    }
    @Override
    public String getId(String employeeName) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT employeeId FROM employee WHERE name = ?",employeeName);
        if (resultSet.next()){
            String employeeId = resultSet.getString(1);

            return employeeId;
        }
        return null;
    }
    @Override
    public List<String> getEmployeedName() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT name FROM employee");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}
