package Dao;


import Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCStudentDao implements StudentDao {
    private final Connection connection;

    public JDBCStudentDao(Connection connection){
        this.connection = connection;
    }

    ArrayList<Student> students = new ArrayList<Student>();

    @Override
    public void insertStudent(Student student) throws SQLException{
        String sql = "INSERT INTO STUDENTS (ID_STUDENT, NUMBER_GROUP, FIRSTNAME, SURNAME, DATE_PRIEM) "
                + "VALUES ('"+student.getId()+"', '"+student.getGroup()+"', '"+student.getSurname()+"', '"+student.getName()+"', "
                + "TO_DATE('"+student.getDate()+" 00:00:00', 'YYYY-MM-DD HH24:MI:SS'))";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
    }

    public void deleteStudent(int id)  throws SQLException {
        String sql = "DELETE FROM students WHERE id_student="+id;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
    }

    @Override
    public void updateStudent(Student student)  throws SQLException {
        String sql = "UPDATE STUDENTS SET FIRSTNAME = '"+student.getName()+"', SURNAME = '"+student.getSurname()+"', "
                + "DATE_PRIEM = TO_DATE('"+student.getDate()+" 00:00:00', 'YYYY-MM-DD HH24:MI:SS') WHERE ID_STUDENT = '"+student.getId()+"'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
    }

    @Override
    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id_student="+id;
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Student student = null;

        while(result.next()){
            student = new Student();
            student.setName(result.getString("firstname"));
            student.setSurname(result.getString("surname"));
            student.setGroup(result.getString("number_group"));
            student.setDate(result.getString("date_priem"));
        }
        return student;
    }

    @Override
    public ArrayList<Student> getStudentByGroup(String number) throws SQLException {
        String sql = "SELECT * FROM students WHERE number_group="+number;
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Student student = null;

        while(result.next()){
            student = new Student();
            student.setName(result.getString("firstname"));
            student.setSurname(result.getString("surname"));
            student.setGroup(result.getString("number_group"));
            student.setDate(result.getString("date_priem"));
            students.add(student);
        }
        return students;
    }

    @Override //не работает
    public Student getStudentBySurname(String surname) throws SQLException {
        String sql = "SELECT * FROM students WHERE surname="+surname;
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        Student student = null;
        while(result.next()){
            student = new Student();
            student.setName(result.getString("firstname"));
            student.setSurname(result.getString("surname"));
            student.setGroup(result.getString("number_group"));
            student.setDate(result.getString("date_priem"));
            student.setId(Integer.parseInt("id_student"));
        }
        return student;
    }

    public ArrayList<Student> getAllStudent() throws SQLException {
        String sql = "SELECT * FROM students";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Student student = null;

        while(result.next()){
            student = new Student();
            student.setName(result.getString("firstname"));
            student.setSurname(result.getString("surname"));
            student.setGroup(result.getString("number_group"));
            student.setDate(result.getString("date_priem"));
            student.setId(Integer.valueOf(result.getString("id_student")));
            students.add(student);
        }
        return students;
    }




}
