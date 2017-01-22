package Model;


public class Student {
    String name;
    String surname;
    String date;
    String fac;
    String group;
    int id;

    public Student(){
        name = "Vasya";
        surname = "Ivanov";
        group = "1";
        date = "1.1.2015";
        id = 1;
        // fac = "Inform";
    }

    public Student(String surname,String name, String group, String date, int id){
        this.surname = surname;
        this.name = name;
        this.group = group;
        this.date = date;
        this.id = id;
        //  this.fac = facult;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getGroup(){
        return group;
    }

    /* public String getFacult(){
        return fac;
    }*/

    /*public void setFacult(String facult){
        this.fac = facult;
    }*/
    public void setGroup(String group){
        this.group = group;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String toString(){
        String s = "";
        s = this.getSurname() + " " + this.getName() + " " + this.getGroup() + " " + this.getDate() + "\n";
        return s;
    }
}
