package lk.SMP.bo.custom.Impl;

import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.OrderDAO;
import lk.SMP.bo.custom.OrderBO;
import lk.SMP.entity.Order;
import lk.SMP.model.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO  = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDER);


    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public boolean saveOrder(Order order) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(order.getOrderId(), order.getCustomerId(), order.getDate(), order.getNetTotal()));
    }
    @Override
    public List<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> allOrders = new ArrayList<>();
        List<Order> all =  orderDAO.getAll();
        for (Order order : all) {
            allOrders.add(new OrderDTO(order.getOrderId(), order.getCustomerId(), order.getDate(), order.getNetTotal()));
        }
        return allOrders;
    }


    @Override
    public boolean updateOrders(OrderDTO order) throws SQLException, ClassNotFoundException {
        return orderDAO.update(new Order(order.getOrderId(), order.getCustomerId(), order.getDate(), order.getNetTotal()));
    }


    @Override
    public OrderDTO searchOrder(String id) throws SQLException, ClassNotFoundException {
        Order order = orderDAO.search(id);
        return new OrderDTO(order.getOrderId(), order.getCustomerId(), order.getDate(), order.getNetTotal());
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getId() throws SQLException, ClassNotFoundException {
        return orderDAO.getId();
    }

}
