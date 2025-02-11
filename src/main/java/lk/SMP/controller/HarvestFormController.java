package lk.SMP.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.HarvestDAO;
import lk.SMP.DAO.custom.impl.HarvestDAOImpl;
import lk.SMP.bo.custom.HarvestBO;
import lk.SMP.entity.Harvest;
import lk.SMP.model.Tm.HarvestTm;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class HarvestFormController {

    @FXML
    private JFXComboBox<String> cmbFieldName;

    @FXML
    private TableColumn<?, ?> colCropType;

    @FXML
    private TableColumn<?, ?> colFieldId;

    @FXML
    private TableColumn<?, ?> colHarvestDate;

    @FXML
    private TableColumn<?, ?> colHarvestId;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colWaste;

    @FXML
    private Label lblFieldId;

    @FXML
    private Label lbtDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<HarvestTm> tblHarvest;

    @FXML
    private TextField txtCropType;

    @FXML
    private TextField txtHarvestId;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtWaste;

   // HarvestDAO harvestDAO = (HarvestDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.HARVEST);

    HarvestBO harvestBO = (HarvestBO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.HARVEST);

    public void initialize() {
        setDate();
        setCellValueFactory();
        loadAllCustomer();
        getFieldName();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lbtDate.setText(String.valueOf(now));
    }

    private void loadAllCustomer(){
        ObservableList<HarvestTm> obList = FXCollections.observableArrayList();

        try {
            List<Harvest> harvestList = harvestBO.getAllHarvest();
            for (Harvest harvest : harvestList){
                HarvestTm harvestTm = new HarvestTm(
                        harvest.getHarvestId(),
                        harvest.getCropType(),
                        harvest.getQuantity(),
                        harvest.getDate(),
                        harvest.getFieldId(),
                        harvest.getUnitPrice(),
                        harvest.getWaste()
                );

                obList.add(harvestTm);
            }
            tblHarvest.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory(){
        colHarvestId.setCellValueFactory(new PropertyValueFactory<>("harvestId"));
        colCropType.setCellValueFactory(new PropertyValueFactory<>("cropType"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colHarvestDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colFieldId.setCellValueFactory(new PropertyValueFactory<>("fieldId"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colWaste.setCellValueFactory(new PropertyValueFactory<>("waste"));
    }
    private void clearFields() {
        txtHarvestId.setText("");
        txtWaste.setText("");
        txtUnitPrice.setText("");
        txtCropType.setText("");
        txtQuantity.setText("");
        lblFieldId.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
            clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String harvestId = txtHarvestId.getText();

        try {
            boolean isDelete = harvestBO.deleteHarvest(harvestId);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Harvest is Deleted!").show();
                clearFields();
            }
        }catch (SQLException e){
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
        String harvestId = txtHarvestId.getText();
        String cropType = txtCropType.getText();
        double quantity = Double.parseDouble(txtQuantity.getText());
        Date harvestDate = Date.valueOf(lbtDate.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double waste = Double.parseDouble(txtWaste.getText());
        String fieldId = lblFieldId.getText();



        try {
            Harvest harvest = new Harvest(harvestId,cropType,quantity,harvestDate,fieldId,unitPrice,waste);

            boolean isSaved = harvestBO.saveHarvest(harvest);

            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "New Harvest is Saved....!").show();

                //clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String harvestId = txtHarvestId.getText();
        String cropType = txtCropType.getText();
        double quantity = Double.parseDouble(txtQuantity.getText());
        String fieldId = lblFieldId.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double waste = Double.parseDouble(txtWaste.getText());
        Date date = null;


        try {
            Harvest harvest = new Harvest(harvestId,cropType,quantity,date,fieldId,unitPrice,waste);

            boolean isUpdate = harvestBO.updateHarvest(harvest);

            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION, "Harvest is Updated....!").show();
               // clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtHarvestOnAction(ActionEvent event)  {
        String harvestId = txtHarvestId.getText();
        try {
            Harvest harvest = new HarvestDAOImpl().searchById(harvestId);
            if (harvest != null) {
                txtCropType.setText(harvest.getCropType());
                txtQuantity.setText(String.valueOf(harvest.getQuantity()));
                lblFieldId.setText(harvest.getFieldId());
                txtUnitPrice.setText(String.valueOf(harvest.getUnitPrice()));
                txtWaste.setText(String.valueOf(harvest.getWaste()));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbFieldNameOnAction(ActionEvent actionEvent) {
        setCustomerId();
    }
    private void getFieldName(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = harvestBO.getfieldName();

            for (String code : idList) {
                obList.add(code);
            }
            cmbFieldName.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCustomerId(){
        String fieldName = cmbFieldName.getValue();

        try {
            String fieldId = harvestBO.getId(fieldName);
            lblFieldId.setText(fieldId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
