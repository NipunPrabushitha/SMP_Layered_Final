package lk.SMP.bo.custom.Impl;

import lk.SMP.DAO.DAOFactory;
import lk.SMP.DAO.custom.UserDAO;
import lk.SMP.bo.custom.UserBO;
import lk.SMP.entity.User;
import lk.SMP.model.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.USER);


    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public boolean saveUser(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(user.getId(),user.getName(),user.getPassword()));
    }

    @Override
    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        ArrayList<UserDTO> allUsers = new ArrayList<>();
        List<User> all =  userDAO.getAll();
        for (User user : all) {
            allUsers.add(new UserDTO(user.getId(),user.getName(),user.getPassword()));
        }
        return allUsers;
    }


    @Override
    public boolean updateUsers(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(user.getId(),user.getName(),user.getPassword()));
    }


    @Override
    public UserDTO searchUsers(String id) throws SQLException, ClassNotFoundException {
        User user = userDAO.search(id);
        return new UserDTO(user.getId(),user.getName(),user.getPassword());
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean cheak(String userId, String pw) throws SQLException, ClassNotFoundException {
        return userDAO.cheak(userId, pw);
    }

}
