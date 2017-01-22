package Servlets;

import Dao.DaoFactory;
import Dao.GroupDao;
import Dao.JDBCDaoFactory;
import Dao.StudentDao;
import Model.Group;
import Model.Student;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
public class RedactGroupPage extends HttpServlet {
    String page = "RedactGroupPage.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        int id = Integer.valueOf(req.getParameter("id_of_group"));
        String number = req.getParameter("number_of_group");
        String facult = req.getParameter("facult_of_group");

        PrintWriter out = resp.getWriter();
        DaoFactory daoFactory = new JDBCDaoFactory();
        Group group = new Group(id, number, facult);
        Connection connection = null;
        try {
            connection = daoFactory.getConnectionApp();
            GroupDao daoGroup = daoFactory.getGroupDao(connection);
            daoGroup.updateGroup(group);
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Произошла ошибка добавления студента!");
        } catch (NamingException e) {
            e.printStackTrace();
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        out.println("Группа " + number + " обновлена успешно! ");
        out.println("Нажмите на ссылку, чтобы вернуться к просмотру списка групп.<p><a href='/AddGroupPage.jsp'>Cтраница добавления групп</a></p>" +
                "<p><a href='/all-groups'>Список всех групп</a></p>");
        out.close();

        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
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
