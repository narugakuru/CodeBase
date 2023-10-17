package AllTestSystem;

//文学系： 演讲 35% 作品 35%  期末考试 15%  期中考试 15%
public class LiteratureDepartment extends StudentData implements Department {
    private double speechGrade;
    private double production;

    public double getSpeechGrade() {
        return speechGrade;
    }

    public double getProduction() {
        return production;
    }

    @Override
    public String toString() {
        return "LiteratureDepartment{" +
                "speechGrade=" + speechGrade +
                ", production=" + production +
                '}';
    }

    @Override
    public double getAllTest() {
        return speechGrade * 0.35 + production * 0.35 + getMidGrade() * 0.15 + getFinalGrade() * 0.15;
    }

    public LiteratureDepartment(String sNumber, String sname, String sex, int age, double midGrade,
                                double finalGrade, double speechGrade, double production) {
        super(sNumber, sname, sex, age, midGrade, finalGrade);
        this.speechGrade = speechGrade;
        this.production = production;
    }


}
