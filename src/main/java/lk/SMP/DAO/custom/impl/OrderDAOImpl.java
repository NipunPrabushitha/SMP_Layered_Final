package lk.SMP.DAO.custom.impl;

import lk.SMP.DAO.SQLUtil;
import lk.SMP.DAO.custom.OrderDAO;
import lk.SMP.controller.PlaceOrderFormController;
import lk.SMP.entity.Customer;
import lk.SMP.entity.Order;
import lk.SMP.entity.OrderDetail;
import lk.SMP.model.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    public String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?, ?)",
                order.getOrderId(), order.getCustomerId(), order.getDate(), order.getNetTotal());
    }

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    public String getId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT OrderId FROM orders ORDER BY OrderId DESC LIMIT 1");
        if(resultSet.next()) {
            return PlaceOrderFormController.splitOrderId(resultSet.getString(1));
        }
        return PlaceOrderFormController.splitOrderId(null);
    }
    public boolean save(OrderDetail od) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO orderAndHarvestInfo VALUES(?, ?, ?, ?,?)",
                od.getOrderId(), od.getHarvestId(), od.getQty(), od.getUnitPrice(), od.getSubItemTotal());
    }
}
