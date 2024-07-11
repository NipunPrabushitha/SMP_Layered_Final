package lk.SMP.DAO.custom;

import lk.SMP.DAO.CrudDAO;
import lk.SMP.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {
    //public boolean save(Employee employee) throws SQLException, ClassNotFoundException;

    //public boolean update(Employee employee) throws SQLException, ClassNotFoundException;

    //public boolean delete(String employeeId) throws SQLException, ClassNotFoundException;
    //public  Employee search(String employeeId) throws SQLException, ClassNotFoundException;

    //public List<Employee> getAll() throws SQLException, ClassNotFoundException;
    public String getId(String employeeName) throws SQLException, ClassNotFoundException;
    public List<String> getEmployeedName() throws SQLException, ClassNotFoundException;
}
