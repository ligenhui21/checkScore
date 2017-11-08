package entity;

public class Student {

    private Long id;
    private String stuName;
    private String newStudentId;
    private String identityNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getNewStudentId() {
        return newStudentId;
    }

    public void setNewStudentId(String newStudentId) {
        this.newStudentId = newStudentId;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }
}
