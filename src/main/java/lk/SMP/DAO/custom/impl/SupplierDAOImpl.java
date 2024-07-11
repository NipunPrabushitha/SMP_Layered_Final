package lk.SMP.DAO.custom.impl;

import javafx.scene.control.Alert;
import lk.SMP.DAO.SQLUtil;
import lk.SMP.DAO.custom.SupplierDAO;
import lk.SMP.entity.Supplier;
import lk.SMP.model.CustomerDTO;
import lk.SMP.model.EmployeeDTO;
import lk.SMP.model.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");

        List<Supplier> supList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String tel = resultSet.getString(3);
            String address = resultSet.getString(4);

            Supplier supplier = new Supplier(id, name,tel,address);
            supList.add(supplier);
        }
        return supList;
    }

    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET SupplierName =?, address =?, contactNumber =? WHERE supplierId =?",
                supplier.getName(),
                supplier.getAddress(),
                supplier.getContactNumber(),
                supplier.getSupplierId());
    }
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM supplier WHERE supplierId =?",id);
    }
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?,?,?,?)",
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getContactNumber(),
                supplier.getAddress());
    }
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier WHERE supplierId =?",id);
        if (resultSet.next()) {
            String name = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String address = resultSet.getString(4);

            Supplier supplier = new Supplier(id,name,contact,address);
            return supplier;
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Found").show();
        }
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

}
