package dao;

import entity.Student;
import org.apache.ibatis.annotations.Insert;

public interface IStudent {
    @Insert("INSERT INTO student(student_name) VALUES (#{studentName})")
    public void insertStudent(Student student);
}
