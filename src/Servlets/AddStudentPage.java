package Servlets;

import Dao.DaoFactory;
import Dao.JDBCDaoFactory;
import Dao.StudentDao;
import Model.Student;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Павел on 13.04.2016.
 */

public class AddStudentPage extends HttpServlet{
    String page = "AddStudentPage.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp);
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        int id = Integer.valueOf(req.getParameter("id_of_student"));
        String name = req.getParameter("name_of_student");
        String surname = req.getParameter("surname_of_student");
        String date = req.getParameter("date_of_student");
        String group = req.getParameter("group_of_student");

        PrintWriter out = resp.getWriter();
        DaoFactory daoFactory = new JDBCDaoFactory();
        Student student = new Student(surname, name, group, date, id);
        Connection connection = null;
        try {
            connection = daoFactory.getConnectionApp();
            StudentDao daoStudent = daoFactory.getStudentDao(connection);
            daoStudent.insertStudent(student);
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
        out.println("Студент " + surname + " " + name + " добавлен в группу " + group);
        out.println("Нажмите на ссылку, чтобы вернуться к просмотру списка студентов.<p><a href='/AddStudentPage.jsp'>Cтраница добавления студентов</a></p>" +
                "<p><a href='/'>Список всех студентов</a></p>");
        out.close();

        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String name = req.getParameter("id_of_student");
        PrintWriter out = resp.getWriter();
        out.println("<h3>"+"SUCCESS!"+"</h3>"+name);
        out.close();
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }
}
