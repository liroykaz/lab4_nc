package Dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class JDBCDaoFactory implements DaoFactory {

    private String user = "system";//Логин пользователя
    private String password = "123456";//Пароль пользователя
    private String url = "jdbc:oracle:thin:@localhost:1521:XE";//URL адрес
    private String driver = "oracle.jdbc.driver.OracleDriver";//Имя драйвера

    @Override
    public Connection getConnection() throws SQLException {
        Locale.setDefault(Locale.US);

        return DriverManager.getConnection(url, user, password);
    }
    @Override
    public Connection getConnectionApp() throws SQLException, NamingException {
        Locale.setDefault(Locale.US);
        Context initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");
        Connection conn = ds.getConnection();
        return conn;
    }


    @Override
    public GroupDao getGroupDao(Connection connection) {
        return new JDBCGroupDao(connection);
    }

    @Override
    public StudentDao getStudentDao(Connection connection) {
        return new JDBCStudentDao(connection);
    }
}
