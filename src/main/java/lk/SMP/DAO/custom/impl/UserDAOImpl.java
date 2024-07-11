package lk.SMP.DAO.custom.impl;

import javafx.scene.control.Alert;
import lk.SMP.DAO.SQLUtil;
import lk.SMP.DAO.custom.UserDAO;
import lk.SMP.entity.User;
import lk.SMP.model.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.SMP.controller.AddNewAccountFoemController.splitOrderId;

public class UserDAOImpl implements UserDAO {
    public List<String> getUserId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT userId FROM user");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT userId FROM user ORDER BY userId DESC LIMIT 1");
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean save(User user) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("INSERT INTO user VALUES(?,?,?)",
                user.getId(),
                user.getName(),
                user.getPassword());
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean cheak(String userId, String pw) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT userName, password FROM user WHERE userName = ?",userId);
        if(resultSet.next()) {

            String dbPw = resultSet.getString("password");
            if(pw.equals(dbPw)) {
                return  true;
            } else {

                new Alert(Alert.AlertType.ERROR, "sorry! password is incorrect!").show();
                return false;
            }

        } else {
            new Alert(Alert.AlertType.INFORMATION, "sorry! user id can't be find!").show();
            return false;
        }
    }

}
