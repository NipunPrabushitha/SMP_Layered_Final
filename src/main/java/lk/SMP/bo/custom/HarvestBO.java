package lk.SMP.bo.custom;

import lk.SMP.bo.SuperBO;
import lk.SMP.entity.Harvest;
import lk.SMP.model.HarvestDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface HarvestBO extends SuperBO {
   public List<String> getfieldId() throws SQLException, ClassNotFoundException;
    //public boolean delete(String harvestId) throws SQLException, ClassNotFoundException;
   public String getId(String fieldName) throws SQLException, ClassNotFoundException;
    public List<String> getCodes() throws SQLException, ClassNotFoundException;
    public List<String> getfieldName() throws SQLException, ClassNotFoundException;
    public Harvest searchById(String harvestid) throws SQLException, ClassNotFoundException;
   // public boolean update(Harvest harvest) throws SQLException, ClassNotFoundException;


    //public Harvest search(String id) throws SQLException, ClassNotFoundException;


    //ArrayList<Harvest> getAllHarvest() throws SQLException, ClassNotFoundException;

   // boolean updateHarvest(HarvestDTO harvest) throws SQLException, ClassNotFoundException;

    //HarvestDTO searchHarvest(String id) throws SQLException, ClassNotFoundException;

    boolean deleteHarvest(String id) throws SQLException, ClassNotFoundException;

    boolean saveHarvest(Harvest harvest) throws SQLException, ClassNotFoundException;

    ArrayList<Harvest> getAllHarvest() throws SQLException, ClassNotFoundException;

    boolean updateHarvest(Harvest harvest) throws SQLException, ClassNotFoundException;

    HarvestDTO searchHarvest(String id) throws SQLException, ClassNotFoundException;

    List<String> getIds() throws SQLException, ClassNotFoundException;

    // public List<String> getIds() throws SQLException, ClassNotFoundException;

   // public boolean save(Harvest harvest) throws SQLException, ClassNotFoundException;
   // public List<Harvest> getAll() throws SQLException, ClassNotFoundException;
    //public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

   // public List<String> getCodes() throws SQLException, ClassNotFoundException;
   // public boolean update(List<OrderDetail> odList) throws SQLException;
}
