package lk.SMP.bo.custom;

import javafx.scene.control.Alert;
import lk.SMP.DAO.SQLUtil;
import lk.SMP.bo.SuperBO;
import lk.SMP.entity.Customer;
import lk.SMP.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
     boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
     boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    public  boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;
    public List<String> getIds() throws SQLException, ClassNotFoundException;
}
