package lk.SMP.DAO;


import lk.SMP.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil<T> {
    public static  <T> T execute(String sql, Object... o) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        for (int i = 0; i < o.length; i++) {
            stm.setObject((i+ 1), o[i]);
        }

        if (sql.startsWith("SELECT")){
            return (T) stm.executeQuery();
        }else{
            return (T)(Boolean) (stm.executeUpdate() > 0);
        }
    }
}
