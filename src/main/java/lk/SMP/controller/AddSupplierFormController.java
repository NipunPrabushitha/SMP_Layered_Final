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
import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.SupplierDAO;
import lk.SMP.DAO.custom.impl.SupplierDAOImpl;
import lk.SMP.bo.BOFactory;
import lk.SMP.bo.custom.SupplierBO;
import lk.SMP.entity.Supplier;
import lk.SMP.model.Tm.SupplierTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddSupplierFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSupplierId;

    //SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.SUPPLIER);


    @FXML
    void btnClearOnAction(ActionEvent event) {
            clearFields();
    }

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    public void initialize() {
        setCellValueFactory();
        loadAllSuppliers();
    }
    private void loadAllSuppliers() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supplierList = new SupplierDAOImpl().getAll();
            for (Supplier supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getSupplierId(),
                        supplier.getName(),
                        supplier.getContactNumber(),
                        supplier.getAddress()
                );

                obList.add(tm);
            }

            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

    }
    private void clearFields() {
        txtSupplierId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContactNo.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        try {
            boolean isDeleted = supplierBO.deleteSupplier(id);
            if (isDeleted) {
                initialize();
                new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContactNo.getText();

        Supplier supplier = new Supplier(id, name, address, contact);
        try {

            boolean isSaved = supplierBO.saveSupplier(supplier);
            if (isSaved) {
                initialize();
                new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SupplierId = txtSupplierId.getText();
        String Name = txtName.getText();
        String Contact = txtContactNo.getText();
        String Address = txtAddress.getText();

        Supplier supplier = new Supplier(SupplierId, Name, Contact, Address);

        boolean isUpdate = supplierBO.updateSuppliers(supplier);
        if (isUpdate) {
            initialize();
            new Alert(Alert.AlertType.INFORMATION, "Supplier Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Updated").show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();

        try{
            Supplier supplier = supplierBO.searchSupplier(id);

            txtName.setText(supplier.getName());
            txtContactNo.setText(supplier.getContactNumber());
            txtAddress.setText(supplier.getAddress());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"Supplier ID Not Found!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }
}
