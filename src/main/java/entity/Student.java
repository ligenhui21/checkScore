package entity;

public class Student {

    private Long id;
    private Integer studentNo;  //序号
    private String studentName; //学生姓名
    private Integer sex;    //性别（0-男；1-女）
    private String nation;  //民族
    private String oldticketNo; //老准考证号
    private String newticketNo; //新准考证号
    private String idcardNo;    //身份证号
    private String school;  //主考学校
    private String speciality;  //衔接专业
    private Integer level;  //报考层次（0-专科；1-本科）
    private String registerTime;    //注册入学时间
    private String department;  //所属项目部
    private String principal;   //负责人
    private String unit;    //上报单位
    private String teacher; //老师
    private Integer isMembership;   //是否在籍、退报、转专业、或毕业、拟毕业
    private String turnSpeciality;  //所转专业
    private String remark;  //备注
    private String newRemark;   //2016年5月备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Integer studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getOldticketNo() {
        return oldticketNo;
    }

    public void setOldticketNo(String oldticketNo) {
        this.oldticketNo = oldticketNo;
    }

    public String getNewticketNo() {
        return newticketNo;
    }

    public void setNewticketNo(String newticketNo) {
        this.newticketNo = newticketNo;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getIsMembership() {
        return isMembership;
    }

    public void setIsMembership(Integer isMembership) {
        this.isMembership = isMembership;
    }

    public String getTurnSpeciality() {
        return turnSpeciality;
    }

    public void setTurnSpeciality(String turnSpeciality) {
        this.turnSpeciality = turnSpeciality;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNewRemark() {
        return newRemark;
    }

    public void setNewRemark(String newRemark) {
        this.newRemark = newRemark;
    }
}
