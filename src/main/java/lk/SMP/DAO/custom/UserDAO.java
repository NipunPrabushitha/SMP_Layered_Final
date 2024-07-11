package lk.SMP.DAO.custom;

import lk.SMP.DAO.CrudDAO;
import lk.SMP.entity.User;
import lk.SMP.model.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    /*public List<String> getUserId() throws SQLException, ClassNotFoundException;
    public String generateNextId() throws SQLException, ClassNotFoundException;
    public boolean save(User user) throws SQLException, ClassNotFoundException;*/
    public String generateNextId() throws SQLException, ClassNotFoundException;
    public List<String> getUserId() throws SQLException, ClassNotFoundException;
    public boolean cheak(String userId, String pw) throws SQLException, ClassNotFoundException;

}
