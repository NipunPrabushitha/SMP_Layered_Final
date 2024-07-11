package lk.SMP.bo.custom.Impl;

import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.EmployeeDAO;
import lk.SMP.bo.custom.EmployeeBO;
import lk.SMP.entity.Employee;
import lk.SMP.model.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);


    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employee.getEmployeeId(), employee.getName(), employee.getContactNumber(), employee.getFieldId(),employee.getUserId()));
    }

    @Override
    public ArrayList<Employee> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        List<Employee> all =  employeeDAO.getAll();
        for (Employee c : all) {
            allEmployees.add(new Employee(c.getEmployeeId(), c.getName(), c.getContactNumber(), c.getFieldId(),c.getUserId()));
        }
        return allEmployees;
    }


    @Override
    public boolean updateEmployee(Employee c) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(c.getEmployeeId(), c.getName(), c.getContactNumber(), c.getFieldId(),c.getUserId()));
    }


    @Override
    public Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(id);
        return new Employee(employee.getEmployeeId(), employee.getName(), employee.getContactNumber(), employee.getFieldId(),employee.getUserId());
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}

