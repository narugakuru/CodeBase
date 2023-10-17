package AllTestSystem;
//• 学号:XXXXXXXX 姓名：XXX 性别：X 年龄：XX 期中考试 期末考试 /综合成绩
public class StudentData {
    private String sNumber;
    private String sname;
    private String sex;
    private int age;
    private double midGrade;
    private double finalGrade;

    @Override
    public String toString() {
        return "StudentData{" +
                "sNumber=" + sNumber +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", midGrade=" + midGrade +
                ", finalGrade=" + finalGrade +
                '}';
    }

    public String getsNumber() {
        return sNumber;
    }

    public String getSname() {
        return sname;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public double getMidGrade() {
        return midGrade;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public StudentData(String sNumber, String sname, String sex, int age, double midGrade, double finalGrade) {
        this.sNumber = sNumber;
        this.sname = sname;
        this.sex = sex;
        this.age = age;
        this.midGrade = midGrade;
        this.finalGrade = finalGrade;
    }

}
