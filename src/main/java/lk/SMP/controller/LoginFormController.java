package lk.SMP.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.UserDAO;
import lk.SMP.DAO.custom.impl.UserDAOImpl;
import lk.SMP.Util.Regex;
import lk.SMP.bo.BOFactory;
import lk.SMP.bo.custom.UserBO;
import lk.SMP.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane rootNode;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void btnClickOnAction(ActionEvent actionEvent) throws IOException {

        String userId = txtUserName.getText();
        String pw = txtPassword.getText();

        try {
            checkCredential(userId, pw);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private void checkCredential(String userId, String pw) throws SQLException, IOException, ClassNotFoundException {
        boolean ischeacked = userBO.cheak(userId, pw);
        if (ischeacked) {
            navigateToTheDashboard();
        } else {

        }
    }

    public void navigateToTheDashboard()  {
        AnchorPane rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/DashBoard.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    @FXML
    void txtPasswordAction(KeyEvent event) {
        Regex.setTextColor(lk.SMP.Util.TextField.PASSWORD, txtPassword);
    }
    public boolean isValied(){
        if (!Regex.setTextColor(lk.SMP.Util.TextField.NAME,txtUserName)) return false;
        if (!Regex.setTextColor(lk.SMP.Util.TextField.PASSWORD,txtPassword)) return false;
        return true;
    }
    @FXML
    void txtUserNameAction(KeyEvent event) {
        Regex.setTextColor(lk.SMP.Util.TextField.NAME, txtUserName);
    }

}