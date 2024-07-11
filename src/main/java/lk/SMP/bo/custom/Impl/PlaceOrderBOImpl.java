package lk.SMP.bo.custom.Impl;

import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.CustomerDAO;
import lk.SMP.DAO.custom.HarvestDAO;
import lk.SMP.DAO.custom.OrderDAO;
import lk.SMP.DAO.custom.OrderDetailDAO;
import lk.SMP.bo.custom.PlaceOrderBO;
import lk.SMP.entity.Harvest;
import lk.SMP.entity.Order;
import lk.SMP.entity.OrderDetail;
import lk.SMP.model.CustomerDTO;
import lk.SMP.model.HarvestDTO;
import lk.SMP.model.OrderDTO;
import lk.SMP.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ORDER_DETAIL);

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.CUSTOMER);

    HarvestDAO harvestDAO = (HarvestDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.HARVEST);
    @Override
    public boolean save(OrderDetail od) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.save(new OrderDetail(od.getOrderId(),od.getHarvestId(),od.getQty(),od.getUnitPrice(),od.getSubItemTotal()));
    }

    @Override
    public boolean save(OrderDTO order) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(order.getOrderId(),order.getCustomerId(),order.getDate(),order.getNetTotal()));
    }

    @Override
    public String getId() throws SQLException, ClassNotFoundException {
        return orderDAO.getId();
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return harvestDAO.getCodes();
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getIds();
    }

    @Override
    public boolean update(List<OrderDetailDTO> odList) throws SQLException {
        return false;
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public HarvestDTO searchById(String harvestid) throws SQLException, ClassNotFoundException {
        Harvest harvest =  harvestDAO.searchById(harvestid);
        return new HarvestDTO(harvest.getHarvestId(),harvest.getCropType(),(harvest.getQuantity()),harvest.getDate(),harvest.getFieldId(),harvest.getUnitPrice(),harvest.getWaste());
    }
}
