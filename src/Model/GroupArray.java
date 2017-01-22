package Model;

import java.util.ArrayList;

public class GroupArray {
    ArrayList<Group> arrGroups;

    public GroupArray(){
        arrGroups = new ArrayList<Group>();
    }
    public void addGroup(Group group){
        arrGroups.add(group);
    }

    public ArrayList<Group> getArrayGroup(){
        return arrGroups;
    }

    public Group getGroup(int index){
        return arrGroups.get(index);
    }
}

