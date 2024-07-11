package lk.SMP.bo.custom;

import lk.SMP.bo.SuperBO;
import lk.SMP.entity.Harvest;
import lk.SMP.entity.OrderDetail;
import lk.SMP.model.CustomerDTO;
import lk.SMP.model.HarvestDTO;
import lk.SMP.model.OrderDTO;
import lk.SMP.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    public boolean save(OrderDetail od) throws SQLException, ClassNotFoundException;
    public boolean save(OrderDTO order) throws SQLException, ClassNotFoundException;
    public String getId() throws SQLException, ClassNotFoundException;
    public List<String> getCodes() throws SQLException, ClassNotFoundException;
    public List<String> getIds() throws SQLException, ClassNotFoundException;
    public boolean update(List<OrderDetailDTO> odList) throws SQLException;
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException;
    public HarvestDTO searchById(String harvestid) throws SQLException, ClassNotFoundException;
}
