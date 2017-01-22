package lab_2_nc;

import Dao.DaoFactory;
import Dao.GroupDao;
import Dao.JDBCDaoFactory;
import Dao.StudentDao;
import Model.Group;
import Model.Student;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

public class JDBCLabNC {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, NamingException {
        DaoFactory daoFactory = new JDBCDaoFactory();
        ArrayList<Group> groupList;
        ArrayList<Student> students;
        try(Connection con = daoFactory.getConnection()){
            GroupDao dao = daoFactory.getGroupDao(con);
            groupList = dao.getAll();

            Group group = dao.getGroupByNumber(6313);
            System.out.print(group.getDepartment());

            Student student;
            StudentDao daoStudent = daoFactory.getStudentDao(con);

            student = daoStudent.getStudentById(2);
            System.out.print(student.getSurname());

            //student = daoStudent.getStudentBySurname("Lebedev");
            //System.out.print(student.getId());
            group = new Group(5, "6317", "DateBas");
            // dao.insertGroup(group, 5);
            //dao.deleteGroup(5);
            // dao.updateGroup(group);

            student = new Student("Alferov", "Ev", "6314", "2015-10-11", 8);
            Student student2 = new Student("Shlikov", "Lesha", "6313", "2012-09-06", 8);
//            daoStudent.insertStudent(student);
            System.out.println("Студенты группы 6313");
            students = daoStudent.getStudentByGroup("6313");
            //daoStudent.deleteStudent(8);
            daoStudent.updateStudent(student2);

            //students = daoStudent.getAllStudent();

            for(int i =0;i<students.size();i++){
                System.out.println(students.get(i).getName());
            }

        }
        for(int i=0;i<groupList.size();i++){
            System.out.print(groupList.get(i).getNumber());
        }




    }
       /* try {

        Locale.setDefault(Locale.US);
        createDbUserTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
}
    /*
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
             System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","123456");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    private static void createDbUserTable() throws SQLException {
    Connection dbConnection = null;
    Statement statement = null;

    String createTableSQL = "CREATE TABLE DBUSER("
            + "USER_ID NUMBER(5) NOT NULL, "
            + "USERNAME VARCHAR(20) NOT NULL, "
            + "CREATED_BY VARCHAR(20) NOT NULL, "
            + "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) "
            + ")";

    try {
        dbConnection = getDBConnection();
        statement = dbConnection.createStatement();

                // выполнить SQL запрос
        statement.execute(createTableSQL);
        System.out.println("Table \"dbuser\" is created!");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } finally {
        if (statement != null) {
            statement.close();
        }
        if (dbConnection != null) {
            dbConnection.close();
        }
    }
}

}*/

