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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.CustomerDAO;
import lk.SMP.DAO.custom.impl.CustomerDAOImpl;
import lk.SMP.Util.Regex;
import lk.SMP.bo.BOFactory;
import lk.SMP.bo.custom.CustomerBO;
import lk.SMP.entity.Customer;
import lk.SMP.model.CustomerDTO;
import lk.SMP.model.Tm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {

    public TableColumn<?,?> tblId;
    public TableColumn<?,?> tblName;
    public TableColumn<?,?> tblAddress;
    public TableColumn<?,?> tblContactNumber;
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtName;
    //CustomerDAO customerDAO = new CustomerDAOImpl();
    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    public void initialize() {

        setCellValueFactory();
        loadAllCustomers();
        System.out.println("Check");
    }
    private void loadAllCustomers() {
        tblCustomer.getItems().clear();
        try {
            ArrayList<CustomerDTO> allSuppliers = (ArrayList<CustomerDTO>) customerBO.getAllCustomer();

            for (CustomerDTO b : allSuppliers) {
                tblCustomer.getItems().add(new CustomerTm(b.getCustomerId(),b.getName(),b.getContactNumber(),b.getAddress()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event)  {
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
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtCustomerId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContactNo.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();

        try {
            boolean isDeleted = customerBO.deleteCustomer(id);

            if (isDeleted) {
                initialize();
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (isValid()) {
            String id = txtCustomerId.getText();
            String name = txtName.getText();
            String contact = txtContactNo.getText();
            String address = txtAddress.getText();

            CustomerDTO customer = new CustomerDTO(id, name, contact, address);

            try {
                boolean isSaved = customerBO.saveCustomer(customer);

                if (isSaved) {
                    initialize();
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Validation Error");
                alert.setHeaderText("Validation Failed");
                alert.setContentText("Please fill in all fields correctly.");
                alert.showAndWait();
            }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerId = txtCustomerId.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        String Contact = txtContactNo.getText();

        CustomerDTO customer = new CustomerDTO(customerId,Name,Address,Contact);

        boolean isUpdate = customerBO.updateCustomer(customer);
        if (isUpdate) {
            initialize();
            new Alert(Alert.AlertType.INFORMATION, "Customer Updated Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Customer Not Updated").show();
        }
    }


    @FXML
    void txtCustomerOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        try{
                CustomerDTO customer = customerBO.searchCustomer(id);
            if (customer == null) {
                new Alert(Alert.AlertType.INFORMATION,"Customer ID Not Found!");
            }else {

                txtName.setText(customer.getName());
                txtContactNo.setText(customer.getContactNumber());
                txtAddress.setText(customer.getAddress());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"Customer ID Not Found!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isValid() {

        boolean isIdValid = Regex.setTextColor(lk.SMP.Util.TextField.IDC, txtCustomerId);
        boolean isNameValid = Regex.setTextColor(lk.SMP.Util.TextField.NAME, txtName);
        boolean isAddressValid = Regex.setTextColor(lk.SMP.Util.TextField.ADDRESS, txtAddress);
        boolean isContactValid = Regex.setTextColor(lk.SMP.Util.TextField.CONTACT, txtContactNo);

        return isIdValid && isNameValid && isAddressValid && isContactValid;
    }

    public void CustomerIdOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.SMP.Util.TextField.IDC, txtCustomerId);
    }

    public void ContactOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.SMP.Util.TextField.CONTACT, txtContactNo);
    }

    public void CustomerNameOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.SMP.Util.TextField.NAME, txtName);
    }

    public void AddressOnKeyRelesed(KeyEvent keyEvent) {
        //Regex.setTextColor(lk.SMP.Util.TextField.ADDRESS, txtAddress);
    }
}
