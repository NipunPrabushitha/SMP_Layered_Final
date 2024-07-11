package lk.SMP.DAO.custom.impl;

import lk.SMP.DAO.SQLUtil;
import lk.SMP.DAO.custom.OrderDetailDAO;
import lk.SMP.entity.OrderDetail;
import lk.SMP.model.CustomerDTO;
import lk.SMP.model.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean save(OrderDetail od) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO orderAndHarvestInfo VALUES(?, ?, ?, ?,?)",
                od.getOrderId(), od.getHarvestId(), od.getQty(), od.getUnitPrice(), od.getSubItemTotal());
    }

    @Override
    public List<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
