package lk.SMP.DAO.custom.impl;

import javafx.scene.control.Alert;
import lk.SMP.DAO.SQLUtil;
import lk.SMP.DAO.custom.CustomerDAO;
import lk.SMP.model.CustomerDTO;
import lk.SMP.model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("DELETE FROM customer WHERE customerId =?",id);
    }
    @Override
    public boolean save(CustomerDTO customer) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("INSERT INTO customer VALUES(?,?,?,?)",customer.getCustomerId(),
                    customer.getName(),
                    customer.getContactNumber(),
                    customer.getAddress());
    }
    @Override
    public  List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");

        List<CustomerDTO> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String address = resultSet.getString(4);

            CustomerDTO customer = new CustomerDTO(id, name,tel,address);
            cusList.add(customer);
        }
        return cusList;
    }
    @Override
    public  boolean update(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET name =?, address =?, contactNumber =? WHERE customerId =?";

       return SQLUtil.execute("UPDATE customer SET name =?, address =?, contactNumber =? WHERE customerId =?",
                customer.getName(),
                customer.getAddress(),
                customer.getContactNumber(),
                customer.getCustomerId());


    }
    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customerId = ?",id);
        if (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String address = resultSet.getString(4);


            CustomerDTO customer = new CustomerDTO(cus_id, name, tel, address);

            return customer;
        } else {
        new Alert(Alert.AlertType.ERROR, "Customer Not Found").show();
    }
        return null;
    }
    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT customerId FROM customer");
        while (resultSet.next()) {
            String CustomerId = resultSet.getNString(1);
            ids.add(CustomerId);
        }
        return ids;
    }
}
