package Dao;

import Model.Group;
import Model.Student;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GroupDao {

    public void insertGroup(Group group) throws SQLException;

    public void deleteGroup(int id) throws SQLException ;

    public void updateGroup(Group group) throws SQLException ;

    public ArrayList<Group> getAll() throws SQLException;

    public Group getGroupById(int id) throws SQLException;

    public Group getGroupByNumber(int number) throws SQLException;

}