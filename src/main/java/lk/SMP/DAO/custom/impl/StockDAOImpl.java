package lk.SMP.DAO.custom.impl;

import javafx.scene.control.Alert;
import lk.SMP.DAO.SQLUtil;
import lk.SMP.DAO.custom.StockDAO;
import lk.SMP.entity.Stock;
import lk.SMP.model.CustomerDTO;
import lk.SMP.model.EmployeeDTO;
import lk.SMP.model.StockDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
    return SQLUtil.execute("DELETE FROM stock WHERE productId =?",id);
    }
    public List<Stock> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM stock");

        List<Stock> stockList = new ArrayList<>();

        while (resultSet.next()) {
            String productId = resultSet.getString(1);
            double quantity = Double.parseDouble(resultSet.getString(2));
            double unitPrice= Double.parseDouble(resultSet.getString(3));
            String expireDate = resultSet.getString(4);
            String name = resultSet.getString(5);

            Stock stock = new Stock(productId,name,quantity,expireDate,unitPrice);
            stockList.add(stock);
        }
        return stockList;
    }

    public boolean update(Stock stock) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock SET quantity =?, unitprice =?, expiryDate =?, name =? WHERE productId =?",
                stock.getQuantity(),
                stock.getUnitPrice(),
                stock.getDate(),
                stock.getName(),
                stock.getProductId()
        );
    }
    public boolean save(Stock stock) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO stock VALUES(?,?,?,?,?)",
                stock.getProductId(),
                stock.getQuantity(),
                stock.getUnitPrice(),
                stock.getDate(),
                stock.getName()
        );
    }
    public Stock search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM stock WHERE productId =?",id);
        if (resultSet.next()) {
            String quantity = resultSet.getString(2);
            String unitPrice = resultSet.getString(3);
            String expireDate = resultSet.getString(4);
            String name = resultSet.getString(5);
            Stock stock = new Stock(id,name,Double.parseDouble(quantity),expireDate,Double.parseDouble(unitPrice));
            return stock;

        } else {
            new Alert(Alert.AlertType.ERROR, "Stock Not Found").show();
            return null;
        }
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
