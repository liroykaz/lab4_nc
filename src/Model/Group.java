package Model;


import java.util.ArrayList;

public class Group {
    String number;
    String department;
    ArrayList<Student> students = new ArrayList<Student>();
    Student[] studentMas;
    int id;
    //конструктор по умолчанию
    public Group(){
        this.number = "001";
        this.department = "informatika";
        this.id = 1;
    }
    //конструктор с параметрами
    public Group(int id, String number, String department){
        this.number = number;
        this.department = department;
        this.id = id;
    }
    //геттеры сеттеры
    public String getNumber(){
        return number;
    }
    public void setNumber(String number){
        this.number = number;
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public void addStudent(Student student){
        students.add(student);
    }
    //получение ArrayLIst студентов
    public ArrayList<Student> getStudentArray(){
        return students;
    }
    //получение массива студентов
    public Student[] getStudentMas(){
        studentMas = students.toArray(new Student[students.size()]);
        return studentMas;
    }
    //метод удаления студентов
    public void removeStudent(int index){
        students.remove(index);
    }
    //метод изменения студентов
    public void setStudent(int index, Student student){
        students.set(index, student);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
