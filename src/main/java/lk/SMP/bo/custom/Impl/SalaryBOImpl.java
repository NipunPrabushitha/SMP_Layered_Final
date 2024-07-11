package lk.SMP.bo.custom.Impl;

import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.SalaryDAO;
import lk.SMP.bo.custom.SalaryBO;
import lk.SMP.entity.Salary;
import lk.SMP.model.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.SALARY);


    @Override
    public boolean deleteSalary(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }

    @Override
    public boolean saveSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(salary.getSalaryId(),salary.getAmount(),salary.getPaymentDate(),salary.getEmployeeId()));
    }

    @Override
    public List<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException {
        ArrayList<SalaryDTO> allSalaries = new ArrayList<>();
        List<Salary> all =  salaryDAO.getAll();
        for (Salary salary : all) {
            allSalaries.add(new SalaryDTO(salary.getSalaryId(),salary.getAmount(),salary.getPaymentDate(),salary.getEmployeeId()));
        }
        return allSalaries;
    }


    @Override
    public boolean updateSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(salary.getSalaryId(),salary.getAmount(),salary.getPaymentDate(),salary.getEmployeeId()));
    }


    @Override
    public SalaryDTO searchSalary(String id) throws SQLException, ClassNotFoundException {
        Salary salary = salaryDAO.search(id);
        return new SalaryDTO(salary.getSalaryId(),salary.getAmount(),salary.getPaymentDate(),salary.getEmployeeId());
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }



}
