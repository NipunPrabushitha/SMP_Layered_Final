package lk.SMP.bo.custom;

import lk.SMP.bo.SuperBO;
import lk.SMP.model.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;

    boolean saveUser(UserDTO user) throws SQLException, ClassNotFoundException;

    List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;

    boolean updateUsers(UserDTO user) throws SQLException, ClassNotFoundException;

    UserDTO searchUsers(String id) throws SQLException, ClassNotFoundException;

    List<String> getIds() throws SQLException, ClassNotFoundException;
    public boolean cheak(String userId, String pw) throws SQLException, ClassNotFoundException;
}
