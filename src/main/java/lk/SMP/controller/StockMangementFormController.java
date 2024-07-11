package lk.SMP.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.SMP.DAO.custom.impl.StockDAOImpl;
import lk.SMP.entity.Stock;
import lk.SMP.model.Tm.StockTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StockMangementFormController {

    @FXML
    private TableColumn<?, ?> colExpireDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<StockTm> tblStock;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtExpireDate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtUnitPrice;

    public void initialize() {
        setCellValueFactory();
        loadAllSuppliers();
    }
    private void loadAllSuppliers() {
        ObservableList<StockTm> obList = FXCollections.observableArrayList();

        try {
            List<Stock> stockList = new StockDAOImpl().getAll();
            for (Stock stock : stockList) {
                StockTm tm = new StockTm(
                        stock.getProductId(),
                        stock.getQuantity(),
                        stock.getUnitPrice(),
                        stock.getDate(),
                        stock.getName()
                );

                obList.add(tm);
            }

            tblStock.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colExpireDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void clearFields() {
        txtProductId.setText("");
        txtQuantity.setText("");
        txtUnitPrice.setText("");
        txtExpireDate.setText("");
        txtName.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtProductId.getText();
        try {
            boolean isDeleted = new StockDAOImpl().delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted Successfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String productId = txtProductId.getText();
        String name = txtName.getText();
        String expireDate = txtExpireDate.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double quantity = Double.parseDouble(txtQuantity.getText());

        Stock stock = new Stock(productId, name, quantity, expireDate, unitPrice);

        try {

            boolean isSaved = new StockDAOImpl().save(stock);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Stock Saved Successfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String productId = txtProductId.getText();
        String Name = txtName.getText();
        double quantity = Double.parseDouble(txtQuantity.getText());
        String expiryDate = txtExpireDate.getText();
        double unitPrice  = Double.parseDouble(txtUnitPrice.getText());

        Stock stock = new Stock(productId, Name, quantity, expiryDate, unitPrice);

        boolean isUpdate = false;
        try {
            isUpdate = new StockDAOImpl().update(stock);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Updated Successfully").show();
            clearFields();
        }else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Updated").show();
        }

    }

    @FXML
    void txtStockOnAction(ActionEvent event) {
        String productId = txtProductId.getText();

        try{
            Stock stock = new StockDAOImpl().search(productId);
            txtName.setText(stock.getName());
            txtUnitPrice.setText(String.valueOf(stock.getUnitPrice()));
            txtQuantity.setText(String.valueOf(stock.getQuantity()));
            txtExpireDate.setText(stock.getDate());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"Product ID Not Found!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}