package lk.SMP.bo.custom.Impl;

import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.StockDAO;
import lk.SMP.bo.custom.StockBO;
import lk.SMP.entity.Stock;
import lk.SMP.model.StockDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO = (StockDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.STOCK);


    @Override
    public boolean deleteStock(String id) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(id);
    }

    @Override
    public boolean saveStock(StockDTO stock) throws SQLException, ClassNotFoundException {
        return stockDAO.save(new Stock(stock.getProductId(),stock.getQuantity(),stock.getUnitPrice(),stock.getDate(),stock.getName()));
    }

    @Override
    public List<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException {
        ArrayList<StockDTO> allStocks = new ArrayList<>();
        List<Stock> all =  stockDAO.getAll();
        for (Stock stock : all) {
            allStocks.add(new StockDTO(stock.getProductId(),stock.getQuantity(),stock.getUnitPrice(),stock.getDate(),stock.getName()));
        }
        return allStocks;
    }


    @Override
    public boolean updateStocks(StockDTO stock) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(stock.getProductId(),stock.getQuantity(),stock.getUnitPrice(),stock.getDate(),stock.getName()));
    }


    @Override
    public StockDTO searchStocks(String id) throws SQLException, ClassNotFoundException {
        Stock stock = stockDAO.search(id);
        return new StockDTO(stock.getProductId(),stock.getQuantity(),stock.getUnitPrice(),stock.getDate(),stock.getName());
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

}
