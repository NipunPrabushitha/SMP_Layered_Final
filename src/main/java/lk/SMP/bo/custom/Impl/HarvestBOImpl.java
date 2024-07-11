package lk.SMP.bo.custom.Impl;

import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.HarvestDAO;
import lk.SMP.bo.custom.HarvestBO;
import lk.SMP.entity.Harvest;
import lk.SMP.model.HarvestDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HarvestBOImpl implements HarvestBO {

    HarvestDAO harvestDAO = (HarvestDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.HARVEST);


    @Override
    public List<String> getfieldId() throws SQLException, ClassNotFoundException {
        return harvestDAO.getfieldId();
    }

    @Override
    public String getId(String fieldName) throws SQLException, ClassNotFoundException {
        return harvestDAO.getId(fieldName);
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return harvestDAO.getCodes();
    }

    @Override
    public List<String> getfieldName() throws SQLException, ClassNotFoundException {
        return harvestDAO.getfieldName();
    }

    @Override
    public Harvest searchById(String harvestid) throws SQLException, ClassNotFoundException {
        return harvestDAO.searchById(harvestid);
    }

    @Override
    public boolean deleteHarvest(String id) throws SQLException, ClassNotFoundException {
        return harvestDAO.delete(id);
    }

    @Override
    public boolean saveHarvest(Harvest harvest) throws SQLException, ClassNotFoundException {
        return harvestDAO.save(new Harvest(harvest.getHarvestId(),harvest.getCropType(),harvest.getQuantity(),harvest.getDate(),harvest.getFieldId(),harvest.getUnitPrice(),harvest.getWaste()));
    }

    @Override
    public ArrayList<Harvest> getAllHarvest() throws SQLException, ClassNotFoundException {
        ArrayList<Harvest> allHarvest = new ArrayList<>();
        List<Harvest> all =  harvestDAO.getAll();
        for (Harvest harvest : all) {
            allHarvest.add(new Harvest(harvest.getHarvestId(),harvest.getCropType(),harvest.getQuantity(),harvest.getDate(),harvest.getFieldId(),harvest.getUnitPrice(),harvest.getWaste()));
        }
        return allHarvest;
    }


    @Override
    public boolean updateHarvest(Harvest harvest) throws SQLException, ClassNotFoundException {
        return harvestDAO.update(new Harvest(harvest.getHarvestId(),harvest.getCropType(),harvest.getQuantity(),harvest.getDate(),harvest.getFieldId(),harvest.getUnitPrice(),harvest.getWaste()));
    }


    @Override
    public HarvestDTO searchHarvest(String id) throws SQLException, ClassNotFoundException {
        Harvest harvest = harvestDAO.search(id);
        return new HarvestDTO(harvest.getHarvestId(),harvest.getCropType(),harvest.getQuantity(),harvest.getDate(),harvest.getFieldId(),harvest.getUnitPrice(),harvest.getWaste());
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
