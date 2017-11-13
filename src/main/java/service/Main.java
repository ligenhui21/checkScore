package service;

import dao.IClazz;
import dao.IStudent;
import entity.Clazz;
import entity.Student;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("configure.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //sqlSessionFactory.getConfiguration().addMapper(IClazz.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }

    public static void testInsert(){
        SqlSession session = sqlSessionFactory.openSession();
        List<Clazz> list = null;
        try {
            list = ReadExcel.readClzz();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.insert("clazzMapper.insertClazzBatch", list);
       /* Student student = new Student();
        student.setStudentName("test");
        iStudent.insertStudent(student);*/
        session.commit();
    }

    public static void main(String[] args) {
        testInsert();
    }
}
