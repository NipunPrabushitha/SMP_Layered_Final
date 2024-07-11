package lk.SMP.DAO.custom.impl;

import lk.SMP.DAO.SQLUtil;
import lk.SMP.DAO.custom.SalaryDAO;
import lk.SMP.entity.Salary;
import lk.SMP.model.CustomerDTO;
import lk.SMP.model.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    public boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO salary VALUES(?,?,?,?)",
                salary.getSalaryId(),
                salary.getAmount(),
                salary.getPaymentDate(),
                salary.getEmployeeId());
    }

    public boolean update(Salary salary) throws SQLException, ClassNotFoundException {
     return SQLUtil.execute("UPDATE salary SET amount = ?, employeeId = ? WHERE salaryId = ?",
                salary.getAmount(),
                salary.getEmployeeId(),
                salary.getSalaryId());
    }

    @Override
    public Salary search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(String salaryId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM salary WHERE salaryId = ?",salaryId);
    }

    public List<Salary> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM salary");

        List<Salary> salaryList = new ArrayList<>();

        while (resultSet.next()){
            String salaryId = resultSet.getString(1);
            String amount = resultSet.getString(2);
            Date paymentDate = resultSet.getDate(3);
            String employeeId = resultSet.getString(4);

            Salary salary = new Salary(salaryId,amount,paymentDate,employeeId);
            salaryList.add(salary);
        }
        return salaryList;
    }
}
