package lk.SMP.bo.custom;

import lk.SMP.bo.SuperBO;
import lk.SMP.entity.Order;
import lk.SMP.model.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;

    boolean saveOrder(Order order) throws SQLException, ClassNotFoundException;

    List<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException;

    boolean updateOrders(OrderDTO order) throws SQLException, ClassNotFoundException;

    OrderDTO searchOrder(String id) throws SQLException, ClassNotFoundException;

    List<String> getIds() throws SQLException, ClassNotFoundException;
    public String getId() throws SQLException, ClassNotFoundException;
}
