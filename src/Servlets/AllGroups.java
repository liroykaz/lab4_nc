package Servlets;

import Dao.DaoFactory;
import Dao.GroupDao;
import Dao.JDBCDaoFactory;
import Dao.StudentDao;
import Model.Group;
import Model.Student;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Павел on 15.04.2016.
 */
public class AllGroups extends HttpServlet {
    private ServletConfig config;
// Это наша JSP страница

    String page = "AllGroups.jsp";
    public void init(ServletConfig config)
            throws ServletException {
        this.config = config;
    }


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("UTF-8");
        ArrayList<Group> groups = new ArrayList<Group>();
        try {
            Locale.setDefault(Locale.US);
            DaoFactory daoFactory = new JDBCDaoFactory();
            Connection connection = daoFactory.getConnectionApp();
            GroupDao daoGroup = daoFactory.getGroupDao(connection);
            //StudentDao daoStudent = daoFactory.getStudentDao(connection);
            groups = daoGroup.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        req.setAttribute("data", groups);
        // Переходим на JSP страницу
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }
}
