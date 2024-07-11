package lk.SMP.DAO.custom.impl;

import lk.SMP.DAO.SQLUtil;
import lk.SMP.DAO.custom.HarvestDAO;
import lk.SMP.entity.Harvest;
import lk.SMP.entity.OrderDetail;
import lk.SMP.model.CustomerDTO;
import lk.SMP.model.EmployeeDTO;
import lk.SMP.model.HarvestDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HarvestDAOImpl implements HarvestDAO {
    public List<String> getfieldId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT cropType FROM field");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
    public boolean delete(String harvestId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM harvest WHERE harvestId = ?",harvestId);
    }
    public String getId(String fieldName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT fieldId FROM field WHERE cropType = ?",fieldName);
        if (resultSet.next()){
            String fieldId = resultSet.getString(1);

            return fieldId;
        }
        return null;
    }
    public List<String> getfieldName() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT cropType FROM field");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
    public Harvest searchById(String harvestid) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM harvest WHERE harvestId = ?",harvestid);
        if (resultSet.next()) {
            String harvestId = resultSet.getString(1);
            String cropType = resultSet.getString(2);
            double quantity = Double.parseDouble(resultSet.getString(3));
            Date date = Date.valueOf(resultSet.getString(4));
            String fieldId = resultSet.getString(5);
            double unitPrice = Double.parseDouble(resultSet.getString(6));
            double waste = Double.parseDouble(resultSet.getString(7));


            Harvest harvest = new Harvest(harvestId,cropType,quantity,date,fieldId,unitPrice,waste );

            return harvest;
        }

        return null;
    }
    public boolean update(Harvest harvest) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE harvest SET cropType = ?, quantityOnHand = ?, fieldId = ?, unitPrice_200g =  ?, waste = ?   WHERE harvestId = ?",
                harvest.getCropType(), harvest.getQuantity(), harvest.getFieldId(), harvest.getUnitPrice(), harvest.getWaste(), harvest.getHarvestId());
    }

    @Override
    public Harvest search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(Harvest harvest) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO harvest VALUES(?, ?, ?, ?,?,?,?)",
                harvest.getHarvestId(), harvest.getCropType(), harvest.getQuantity(), harvest.getDate(), harvest.getFieldId(), harvest.getUnitPrice(), harvest.getWaste());
    }
    public List<Harvest> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM harvest");

        List<Harvest> harvestList = new ArrayList<>();

        while (resultSet.next()){
            String harvestId = resultSet.getString(1);
            String cropType = resultSet.getString(2);
            double quantity = resultSet.getDouble(3);
            Date date = resultSet.getDate(4);
            String fieldId = resultSet.getString(5);
            double unitPrice = resultSet.getDouble(6);
            double waste = resultSet.getDouble(7);

            Harvest harvest = new Harvest(harvestId,cropType,quantity,date,fieldId,unitPrice,waste);
            harvestList.add(harvest);

        }

        return harvestList;
    }
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE harvest SET quantityOnHand = quantityOnHand - ? WHERE harvestId = ?",qty,itemCode);
    }

    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT harvestId FROM harvest");

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }
    public boolean update(List<OrderDetail> odList) throws SQLException {
        for (OrderDetail od : odList) {
            boolean isUpdateQty = false;
            try {
                isUpdateQty = updateQty(od.getHarvestId(), od.getQty());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }
}
