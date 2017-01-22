package Dao;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DaoFactory {

    public Connection getConnection() throws SQLException;

    public GroupDao getGroupDao(Connection connection);

    public StudentDao getStudentDao(Connection connection);

    public Connection getConnectionApp() throws SQLException, NamingException;
}