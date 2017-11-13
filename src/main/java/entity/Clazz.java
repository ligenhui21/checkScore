package entity;

public class Clazz {
    private Long id;
    private String clazzNo; //课程编号
    private String clazzName;   //课程名
    private Integer clazzType;  //课程类型（0-统考课程；1-衔接课程；2-学分互认课程；3-毕业论文）

    public Clazz(String clazzNo, String clazzName, Integer clazzType){
        this.clazzNo = clazzNo;
        this.clazzName = clazzName;
        this.clazzType = clazzType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClazzNo() {
        return clazzNo;
    }

    public void setClazzNo(String clazzNo) {
        this.clazzNo = clazzNo;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Integer getClazzType() {
        return clazzType;
    }

    public void setClazzType(Integer clazzType) {
        this.clazzType = clazzType;
    }
}
