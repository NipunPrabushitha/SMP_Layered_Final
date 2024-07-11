package lk.SMP.DAO.custom;

import lk.SMP.DAO.CrudDAO;
import lk.SMP.DAO.SQLUtil;
import lk.SMP.entity.Harvest;
import lk.SMP.entity.OrderDetail;
import lk.SMP.model.HarvestDTO;

import java.sql.*;
import java.util.List;

public interface HarvestDAO extends CrudDAO<Harvest> {
    public List<String> getfieldId() throws SQLException, ClassNotFoundException;
    //public boolean delete(String harvestId) throws SQLException, ClassNotFoundException;
   // public String getId(String fieldName) throws SQLException, ClassNotFoundException;
  //  public List<String> getfieldName() throws SQLException, ClassNotFoundException;
   // public Harvest searchById(String harvestid) throws SQLException, ClassNotFoundException;
    //public boolean update(Harvest harvest) throws SQLException, ClassNotFoundException;
    //public boolean save(Harvest harvest) throws SQLException, ClassNotFoundException;
    //public List<Harvest> getAll() throws SQLException, ClassNotFoundException;
   // public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

    //public List<String> getCodes() throws SQLException, ClassNotFoundException;
    //public boolean update(List<OrderDetail> odList) throws SQLException;
    public List<String> getCodes() throws SQLException, ClassNotFoundException;
    public Harvest searchById(String harvestid) throws SQLException, ClassNotFoundException;
    public List<String> getfieldName() throws SQLException, ClassNotFoundException;
    public String getId(String fieldName) throws SQLException, ClassNotFoundException;
    public boolean update(List<OrderDetail> odList) throws SQLException;

}
