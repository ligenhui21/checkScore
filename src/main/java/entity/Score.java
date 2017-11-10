package entity;

public class Score {
    private Long id;
    private String idcardNo;    //身份证号
    private String clazzNo; //课程编号
    private int score;  //分数

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
