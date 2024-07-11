package lk.SMP.DAO.custom;

import lk.SMP.DAO.CrudDAO;
import lk.SMP.entity.Customer;
import lk.SMP.entity.Order;
import lk.SMP.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    /*public String getCurrentId() throws SQLException, ClassNotFoundException;

    public boolean save(Order order) throws SQLException, ClassNotFoundException;
    public String getId() throws SQLException, ClassNotFoundException;
    public boolean save(OrderDetail od) throws SQLException, ClassNotFoundException;*/
    public String getId() throws SQLException, ClassNotFoundException;
}
