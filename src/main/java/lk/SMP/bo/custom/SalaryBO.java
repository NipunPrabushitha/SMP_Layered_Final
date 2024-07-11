package lk.SMP.bo.custom;

import lk.SMP.bo.SuperBO;
import lk.SMP.model.SalaryDTO;

import java.sql.SQLException;
import java.util.List;

public interface SalaryBO extends SuperBO {
    boolean deleteSalary(String id) throws SQLException, ClassNotFoundException;

    boolean saveSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException;

    List<SalaryDTO> getAllSalaries() throws SQLException, ClassNotFoundException;

    boolean updateSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException;

    SalaryDTO searchSalary(String id) throws SQLException, ClassNotFoundException;

    List<String> getIds() throws SQLException, ClassNotFoundException;
}
