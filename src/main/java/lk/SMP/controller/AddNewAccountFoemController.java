package lk.SMP.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.UserDAO;
import lk.SMP.DAO.custom.impl.UserDAOImpl;
import lk.SMP.entity.User;
import lk.SMP.model.UserDTO;


import java.sql.SQLException;



public class AddNewAccountFoemController {

    @FXML
    private Button btnSave;

    @FXML
    private AnchorPane rooter;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtSecondPassword;

    @FXML
    private TextField txtUserName;
    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.USER);

    @FXML
    void btnClickOnAction(ActionEvent event) {
        String name = txtUserName.getText();
        String password = txtPassword.getText();
        String secondPassword = txtSecondPassword.getText();

        String id = null;

        if (password.equals(secondPassword)){
            try {
                id = generateNextAssestId();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            try {
               boolean isSaved = userDAO.save(new User(id, name, password));
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Password isn't match").show();
        }
    }

    private String generateNextAssestId() throws SQLException, ClassNotFoundException {
        return userDAO.generateNextId();
    }
    public static String splitOrderId(String string) {
        if(string != null) {
            String[] strings = string.split("U0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "U00"+id;
            }else {
                if (length < 3){
                    return "U0"+id;
                }else {
                    return "O"+id;
                }
            }
        }
        return "U001";
     }
    }



