package test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

public class Main {
    static int count = 1;
    static int x = 2, y = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int forword = 1;
        int n;
        n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            if ("W".equals(s)) {
                switch (forword) {
                    case 1: {
                        y++;
                        pd();
                        break;
                    }
                    case 2: {
                        x++;
                        pd();
                        break;
                    }
                    case 3: {
                        y--;
                        pd();
                        break;
                    }
                    case 4: {
                        x--;
                        pd();
                        break;
                    }
                }

            } else if ("L".equals(s)) {
                forword--;
                if (forword<1){
                    forword=4;
                }
            } else if ("R".equals(s)) {
                forword++;
                if (forword>4){
                    forword=1;
                }
            }
        }
        System.out.println(count);
    }

    static void pd() {
        if (x - 3 == 1 || y - 3 == 1) {
            x = x % 3;
            y = y % 3;
            count++;
        } else if (x < 1) {
            x = 3;
            count++;
        } else if (y < 1) {
            y = 3;
            count++;
        }
    }
}


/*
  public static void main(String[] args) {
        int count = get();
        System.out.println(count);
    }

    static int get() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int count = 0;

        if (n >= m) {
            return (int) (n - m);
        }

        while (n < m) {
            if (n * 5 == m ) {
                n *= 5;
                count++;
            } else {
                n -= 1;
                n *= 5;
                count += 2;
            }
        }
        count += (n - m);
        return count;
    }
* */


/*
public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int kind = sc.nextInt();
        double[][] res = new double[kind][2];
        for(int i=0;i<kind;i++){
            res[i][0] = sc.nextInt();
            res[i][1] = sc.nextDouble();
        }

        double Psum=0;
        double sum = 0;
        for (int i = 0;i<kind;i++){
            Psum+= res[i][0]*res[i][1];
            sum += res[i][0];
        }

        double ave = Psum/sum;

         Psum = 0;
        for (int i = 0;i<kind;i++) {
            Psum+= Math.pow((res[i][1]-ave),2) * res[i][0];
        }
        double result = Psum/sum;
        result = Math.sqrt(result);
        System.out.println(String.format("%.2f",result));
    }
* */