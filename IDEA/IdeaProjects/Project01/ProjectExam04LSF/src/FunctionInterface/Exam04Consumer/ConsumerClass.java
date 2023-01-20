package FunctionInterface.Exam04Consumer;

import java.util.function.Consumer;

public class ConsumerClass {
    public static void main(String[] args) {
        String[] strArray = {"ai bo", "18", "dio", "100", "jojo", "16"};

            printInfo(strArray, str -> System.out.print("name: "+ str.split(",")[0]),
                str -> System.out.println("age: " +Integer.parseInt(str.split(",")[1])));

    }

    private static void printInfo(String[] strArray, Consumer<String> con1, Consumer<String> con2) {
        for (String str : strArray) {
            con1.andThen(con2).accept(str);
        }

    }

}
