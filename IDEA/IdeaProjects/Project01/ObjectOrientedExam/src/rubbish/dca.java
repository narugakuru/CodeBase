package rubbish;

import javax.swing.*;
import java.util.Scanner;

public class dca {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int score= (int)(scanner.nextFloat()/10);
        switch (score){
            case 9 :
                System.out.println("A");break;
            case 8:
                System.out.println("B");break;
            case 7:
                System.out.println("C");break;
            case 6:
                System.out.println("D");break;
            default:
                System.out.println("E");break;
        }

    }

}
