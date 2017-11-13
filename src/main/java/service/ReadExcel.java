package service;

import entity.Clazz;
import entity.Score;
import entity.Student;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {

    private static final Logger logger = LoggerFactory.getLogger(ReadExcel.class);

    /**
     * 读取文件并创建工作薄
     * @return
     */
    private static HSSFWorkbook readExcel(){
        HSSFWorkbook workbook = null;
        File file = new File("C:\\Users\\Figo\\Desktop\\贵州大学成绩汇总表2016.11.09(1).xls");
        if(file != null){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                workbook = new HSSFWorkbook(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    /**
     * 读取课程
     * @throws Exception
     */
    public static List<Clazz> readClzz() throws Exception {
        HSSFWorkbook workbook = readExcel();
        List<Clazz> list = new ArrayList<Clazz>();
        if(workbook != null){
            HSSFSheet sheet = workbook.getSheet("计算机及其应用");
            Row row0 = sheet.getRow(0);
            Row row1 = sheet.getRow(1);
            int cellCount = row1.getPhysicalNumberOfCells();
            String parentClazz = null; //所属课程
            String clazzValue = null;   //课程名
            for(int j = 18; j < cellCount; j++){
                Cell cell = row1.getCell(j);
                String value = cell.getStringCellValue();
                if(value != null && !value.isEmpty()){
                    clazzValue = value;
                }else{
                    continue;
                }
                String clazzNo = clazzValue.substring(0, 5);
                String clazzName = clazzValue.substring(5).trim();
                Cell celll0 = row0.getCell(j);
                String value0 = celll0.getStringCellValue();
                if(value0 != null && !value0.isEmpty()){
                    parentClazz = value0;
                }
                Integer clazzType = null;
                switch (parentClazz.trim()){
                    case "统考课程":
                        clazzType = 0;
                        break;
                    case "衔接课程":
                        clazzType = 1;
                        break;
                    case "学分互认课程":
                        clazzType = 2;
                        break;
                    case "毕业论文":
                        clazzType = 3;
                        break;
                }
                Clazz clazz = new Clazz(clazzNo, clazzName, clazzType);
                list.add(clazz);
                logger.info("所属课程：{}", parentClazz);
                logger.info("课程名：{}", clazzValue);
            }
        }
            return list;
    }

    /**
     * 读取学生
     * @return
     */
    public static List<Student> readStudent(){
        HSSFWorkbook workbook = readExcel();
        List<Student> studentList = new ArrayList<>();
        if(workbook != null){
            HSSFSheet sheet = workbook.getSheet("计算机及其应用");
            Row row1 = sheet.getRow(1);
            int rowCount = sheet.getPhysicalNumberOfRows();
            for(int i = 2; i < rowCount; i++){
                List<Score> scoreList = new ArrayList<>();
                Row row = sheet.getRow(2);
                int cellCount = row.getPhysicalNumberOfCells();
                Student student = new Student();
                //构建除了分数外的其它属性
                for(int j = 0; j < 18; j++){
                    Cell cell = row.getCell(j);
                    String value = cell.getStringCellValue();
                    switch (j) {
                        case 0 :
                            student.setStudentNo(Integer.valueOf(value));
                            break;
                        case 1 :
                            student.setStudentName(value);
                            break;
                        case 2 :
                            if(value != null && value.trim().length() > 0){
                                int sex = "男".equals(value) ? 0 : 1;
                                student.setSex(sex);
                            }
                            break;
                        case 3 :
                            student.setNation(value);
                            break;
                        case 4 :
                            student.setOldticketNo(value);
                            break;
                        case 5 :
                            student.setNewticketNo(value);
                            break;
                        case 6 :
                            student.setIdcardNo(value);
                            break;
                        case 7 :
                            student.setSchool(value);
                            break;
                        case 8 :
                            student.setSpeciality(value);
                            break;
                        case 9 :
                            if(value != null && value.trim().length() > 0){
                                int level = "专科".equals(value) ? 0 : 1;
                                student.setLevel(level);
                            }
                            break;
                        case 10 :
                            student.setRegisterTime(value);
                            break;
                        case 11 :
                            student.setDepartment(value);
                            break;
                        case 12 :
                            student.setPrincipal(value);
                            break;
                        case 13 :
                            student.setUnit(value);
                            break;
                        case 14 :
                            student.setTeacher(value);
                            break;
                        case 15 :
                            student.setIsMembership(value);
                            break;
                        case 16 :
                            student.setTurnSpeciality(value);
                            break;
                        case 17 :
                            student.setRemark(value);
                            break;
                    }
                }
                for(int k = 18; k < cellCount; k++){
                    Cell cell = row.getCell(k);
                    CellType cellType = cell.getCellTypeEnum();
                    switch (cellType) {

                    }
                    String value = cell.getStringCellValue();
                    if(value != null && value.trim().length() > 0 && StringUtils.isNumeric(value)){
                        Score score = new Score();
                        score.setScoreExcel(Integer.valueOf(value));
                        score.setIdcardNo(student.getIdcardNo());

                        //设置课程编号
                        Cell cell1 = row1.getCell(k);
                        String value1 = cell1.getStringCellValue();
                        if(value1 != null && value1.trim().length() > 0){
                            String clazzNo = value1.substring(0, 5);
                            score.setClazzNo(clazzNo);
                            scoreList.add(score);
                        }
                    }
                }
            }
        }
        return studentList;
    }



    public static void main(String[] args) {
        try {
            //readClzz();
            List<Student> studentList = readStudent();
            readClzz();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
