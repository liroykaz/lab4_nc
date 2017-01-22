package Dao;

import Model.Group;
import Model.Student;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDao {

    public void insertStudent(Student student) throws SQLException;

    public void deleteStudent(int id) throws SQLException;

    public void updateStudent(Student student)  throws SQLException;

    public Student getStudentById(int id) throws SQLException;

    public Student getStudentBySurname(String surname) throws SQLException;

    public ArrayList<Student> getAllStudent() throws SQLException;

    public ArrayList<Student> getStudentByGroup(String number) throws SQLException;
}