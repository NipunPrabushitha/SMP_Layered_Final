package lk.SMP.bo.custom;

import lk.SMP.bo.SuperBO;
import lk.SMP.entity.Supplier;
import lk.SMP.model.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    boolean saveSupplier(Supplier supplier) throws SQLException, ClassNotFoundException;

    List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    boolean updateSuppliers(Supplier supplier) throws SQLException, ClassNotFoundException;

    Supplier searchSupplier(String id) throws SQLException, ClassNotFoundException;

    List<String> getIds() throws SQLException, ClassNotFoundException;
}
