package lk.SMP.bo.custom.Impl;

import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.CustomerDAO;
import lk.SMP.bo.custom.CustomerBO;
import lk.SMP.entity.Customer;
import lk.SMP.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.CUSTOMER);


    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getContactNumber(), customer.getAddress()));
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allSuppliers = new ArrayList<>();
        ArrayList<CustomerDTO> all = (ArrayList<CustomerDTO>) customerDAO.getAll();
        for (CustomerDTO c : all) {
            allSuppliers.add(new CustomerDTO(c.getCustomerId(), c.getName(), c.getContactNumber(), c.getAddress()));
        }
        return allSuppliers;
    }


    @Override
    public boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getContactNumber(), customer.getAddress()));
    }


@Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
    CustomerDTO customer = customerDAO.search(id);
    return new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getContactNumber(), customer.getAddress());
}

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}

