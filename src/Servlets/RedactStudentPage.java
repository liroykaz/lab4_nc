package Servlets;

import Dao.DaoFactory;
import Dao.JDBCDaoFactory;
import Dao.StudentDao;
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
public class RedactStudentPage extends HttpServlet {
    String page = "RedactStudentPage.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            daoStudent.updateStudent(student);
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
        out.println("Запись студента " + surname + " " + name + " успешно обновлена!" );
        out.println("Нажмите на ссылку, чтобы вернуться к просмотру списка студентов.<p><a href='/AddStudentPage.jsp'>Cтраница добавления студентов</a></p>" +
                "<p><a href='/'>Список всех студентов</a></p>");
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
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            Locale.setDefault(Locale.US);
            DaoFactory daoFactory = new JDBCDaoFactory();
            /*Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");*/
            Connection connection = daoFactory.getConnectionApp();
            StudentDao daoStudent = daoFactory.getStudentDao(connection);
            students = daoStudent.getAllStudent();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        req.setAttribute("data", students);
// Переходим на JSP страницу
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }

    }
}
