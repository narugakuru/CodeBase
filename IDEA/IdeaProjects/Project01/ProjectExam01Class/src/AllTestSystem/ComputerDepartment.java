package AllTestSystem;

//• 计算机系：操作能力，英语写作
//• 计算机系： 操作能力 40%  英语写作 20%  期末考试 20%  期中考试 20%
public class ComputerDepartment extends StudentData implements Department {

    private double operationAbilityGrade;
    private double englishWritingGrade;

    public double getOperationAbilityGrade() {
        return operationAbilityGrade;
    }

    public double getEnglishWritingGrade() {
        return englishWritingGrade;
    }
    @Override
    public String toString() {
        return "ComputerDepartment{" +
                "operationAbilityGrade=" + operationAbilityGrade +
                ", englishWritingGrade=" + englishWritingGrade +
                '}';
    }

    public ComputerDepartment(String sNumber, String sname, String sex, int age, double midGrade,
                              double finalGrade, double operationAbilityGrade, double englishWritingGrade) {
        super(sNumber, sname, sex, age, midGrade, finalGrade);
        this.operationAbilityGrade = operationAbilityGrade;
        this.englishWritingGrade = englishWritingGrade;
    }

    @Override
    public double getAllTest() {
        return operationAbilityGrade * 0.4 + englishWritingGrade * 0.2 + getMidGrade() * 0.2 + getFinalGrade() * 0.2;
    }

}