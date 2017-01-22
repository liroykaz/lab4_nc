package Servlets;

import Dao.DaoFactory;
import Dao.JDBCDaoFactory;
import Dao.JDBCGroupDao;
import Dao.StudentDao;
import Model.Student;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.List;


public class AllStudentPage extends HttpServlet {
    private ServletConfig config;
// Это наша JSP страница

    String page = "AllStudents.jsp";

    public void init(ServletConfig config)
            throws ServletException {
        this.config = config;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
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
            request.setAttribute("data", students);
// Переходим на JSP страницу
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }

    }




}


