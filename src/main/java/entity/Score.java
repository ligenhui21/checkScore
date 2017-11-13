package entity;

public class Score {
    private Long id;
    private String idcardNo;    //身份证号
    private String clazzNo; //课程编号
    private int scoreExcel;  //excel中的分数
    private int scoreHtml;  //网站上的分数
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getClazzNo() {
        return clazzNo;
    }

    public void setClazzNo(String clazzNo) {
        this.clazzNo = clazzNo;
    }

    public int getScoreExcel() {
        return scoreExcel;
    }

    public void setScoreExcel(int scoreExcel) {
        this.scoreExcel = scoreExcel;
    }

    public int getScoreHtml() {
        return scoreHtml;
    }

    public void setScoreHtml(int scoreHtml) {
        this.scoreHtml = scoreHtml;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
