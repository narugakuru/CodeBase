package AllTestSystem;

/*学号:XXXXXXXX 姓名：XXX 性别：X 年龄：XX 期中考试 期末考试
• 英语系： 演讲 50%  期末考试 25%  期中考试 25%*/
public abstract class EnglishDepartment extends StudentData implements Department {
    private double speechGrade;

    public double getAllTest() {
        return speechGrade * 0.5 + getMidGrade() * 0.25 + getFinalGrade() * 0.25;
    }

    @Override
    public String toString() {
        return "EnglishDepartment{" +
                "speechGrade=" + speechGrade +
                '}';
    }

    public EnglishDepartment(String sNumber, String sname, String sex,
                             int age, double midGrade, double finalGrade, double speechGrade) {
        super(sNumber, sname, sex, age, midGrade, finalGrade);
        this.speechGrade = speechGrade;
    }


}
