package lk.SMP.bo.custom;

import lk.SMP.bo.SuperBO;
import lk.SMP.model.StockDTO;

import java.sql.SQLException;
import java.util.List;

public interface StockBO extends SuperBO {
    boolean deleteStock(String id) throws SQLException, ClassNotFoundException;

    boolean saveStock(StockDTO stock) throws SQLException, ClassNotFoundException;

    List<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException;

    boolean updateStocks(StockDTO stock) throws SQLException, ClassNotFoundException;

    StockDTO searchStocks(String id) throws SQLException, ClassNotFoundException;

    List<String> getIds() throws SQLException, ClassNotFoundException;
}
