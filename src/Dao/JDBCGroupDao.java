package Dao;

import Model.Group;
import Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCGroupDao implements GroupDao {

    private final Connection connection;

    public JDBCGroupDao(Connection connection){
        this.connection = connection;
    }

    ArrayList<Group> groupList = new ArrayList<Group>();

    @Override
    public void insertGroup(Group group) throws SQLException {
        String sql = "INSERT INTO GROUPS (NUMBER_GROUP, DEPARTMENT, ID_GROUP) VALUES "
                + "('"+group.getNumber()+"', '"+group.getDepartment()+"', '"+group.getId()+"')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
    }

    @Override
    public void deleteGroup(int id) throws SQLException  {
        String sql = "DELETE FROM groups WHERE id_group="+id;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
    }

    @Override
    public void updateGroup(Group group) throws SQLException {
        String sql = "UPDATE groups SET number_group='"+group.getNumber()+"', department='"+group.getDepartment()+"' WHERE id_group='"+group.getId()+"'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
    }

    @Override
    public ArrayList<Group> getAll() throws SQLException {
        String sql = "SELECT * FROM groups";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        //ArrayList<Group> groupList = new ArrayList<Group>();
        while(result.next()){
            Group group = new Group();
            group.setNumber(result.getString("number_group"));
            group.setDepartment(result.getString("department"));
            group.setId(Integer.parseInt(result.getString("id_group")));
            groupList.add(group);
        }

        statement.close();
        result.close();
        return groupList;
    }

    @Override
    public Group getGroupById(int id) throws SQLException {
        String sql = "SELECT * FROM groups WHERE id="+id+";";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        result.next();
        Group group = new Group();
        group.setNumber(result.getString("number"));
        group.setDepartment(result.getString("department"));
        group.setId(Integer.parseInt(result.getString("id_group")));

        statement.close();
        result.close();

        return group;
    }

    @Override
    public Group getGroupByNumber(int number) throws SQLException {
        String sql = "SELECT * FROM groups WHERE number_group="+number+"";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Group group = null;
        while(result.next()){
            group = new Group();
            group.setNumber(result.getString("number_group"));
            group.setDepartment(result.getString("department"));
            group.setId(Integer.parseInt(result.getString("id_group")));
        }

        statement.close();
        result.close();

        return group;
    }

    public ArrayList<Group> getGroupList(){
        return groupList;
    }

}
