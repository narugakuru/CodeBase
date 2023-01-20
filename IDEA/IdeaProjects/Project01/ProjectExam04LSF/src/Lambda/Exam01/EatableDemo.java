package Lambda.Exam01;

public abstract class EatableDemo implements Eatable{

    private static void useEatable(Eatable e){
            e.eat();
        }

    public static void main(String[] args) {
//
        useEatable(new EatableImp());
//
        useEatable(new Eatable() {
            @Override
            public void eat() {
                System.out.println("do everything is not, eat is first!!");
            }
        });
//
        useEatable(()->{
            System.out.println("do everything is not, eat is first!!");
        });

    }
}
