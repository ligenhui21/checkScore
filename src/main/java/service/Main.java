package service;

import dao.IStudent;
import entity.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Main {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("configure.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSessionFactory.getConfiguration().addMapper(IStudent.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }

    public static void testInsert(){
        SqlSession session = sqlSessionFactory.openSession();
        IStudent iStudent = session.getMapper(IStudent.class);
        Student student = new Student();
        student.setStudentName("test");
        iStudent.insertStudent(student);
        session.commit();;
    }

    public static void main(String[] args) {
        testInsert();
    }
}
