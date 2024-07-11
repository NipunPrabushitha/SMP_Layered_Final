package lk.SMP.bo.custom.Impl;

import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.SupplierDAO;
import lk.SMP.bo.custom.SupplierBO;
import lk.SMP.entity.Supplier;
import lk.SMP.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.SUPPLIER);


    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean saveSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplier.getSupplierId(),supplier.getName(),supplier.getContactNumber(),supplier.getAddress()));
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> allSuppliers = new ArrayList<>();
        List<Supplier> all =  supplierDAO.getAll();
        for (Supplier supplier : all) {
            allSuppliers.add(new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getContactNumber(),supplier.getAddress()));
        }
        return allSuppliers;
    }


    @Override
    public boolean updateSuppliers(Supplier supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplier.getSupplierId(),supplier.getName(),supplier.getContactNumber(),supplier.getAddress()));
    }


    @Override
    public Supplier searchSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.search(id);
        return new Supplier(supplier.getSupplierId(),supplier.getName(),supplier.getContactNumber(),supplier.getAddress());
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

}
