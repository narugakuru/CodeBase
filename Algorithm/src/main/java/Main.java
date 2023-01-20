import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int M, N;
        Scanner sc = new Scanner(System.in);
        M = 3;
        N = 10;
        int[] list = new int[N];
        

    }


}

/*
int n, count = 0;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        String[][] a = new String[n][5];

        for (int i = 0; i < n; i++) {
            a[i][0] = sc.next();
            a[i][1] = sc.next();
            a[i][2] = sc.next();
            a[i][3] = sc.next();
            a[i][4] = sc.next();
        }

        for (int i = 0; i < n; i++) {
            if ("L".equals(a[i][0])) {
                if ("G".equals(a[i][1]) && "C".equals(a[i][2])) {
                    if ("N".equals(a[i][3]) | "U".equals(a[i][3])) {
                        count++;
                    }
                }
            } else if ("T".equals(a[i][0])) {
                if ("G".equals(a[i][1]) && "C".equals(a[i][2])) {
                    if ("N".equals(a[i][3])) {
                        int t = Integer.parseInt(a[i][4]);
                        if(t<2){
                            count++;
                        }
                    }
                }
            }
        }
        System.out.println(count);
*/

/*
 int N;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        while (true) {
            int res = 0;
            int n = N;
            while (n != 0) {
                int tmp = n % 10;
                n = n / 10;
                res = res * 10 + tmp;
            }
            if ((res+N) >= 100000 && (res+N) <= 200000) {
                System.out.println(N);
                break;
            }
            N++;
        }
* */


/*    int[] a = new int[]{
            1, 34, 45, 123, 65, 234, 6, 1
    };
    int tar = 34;
        System.out.println(mthd(a, tar));

    public static int mthd(int[] a, int tar) {
        for (int step = a.length / 2; step > 0; step = step / 2) {
            for (int i = step; i < a.length; i++) {
                for (int j = i - step; j >= 0; j = j - step) {
                    if (a[j] > a[j + step]) {
                        a[j] = a[j] + a[j + step];
                        a[j + step] = a[j] - a[j + step];
                        a[j] = a[j] - a[j + step];
                        System.out.println(a[j] + " " + a[j + step]);
                    }
                }
            }
        }
        int tmp = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == tar) {
                tmp = i;break;
            }

        }
        return tmp;
    }*/


/*

    public static void main(String []args){
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int ans = 0;
        for(int i = 1;i <= N;i++){
            double temp = Math.pow(i,1.5);
            if(Math.abs(temp - (int)temp) < 1e-6){
                ans++;
            }
        }
        System.out.println(ans);
    }
*/


//2
/*
        int n = 0;
        int count = 0;
        Scanner sc = new Scanner(System.in);
        n = Integer.parseInt(sc.nextLine());
        for (int i = 1;i<=n;i++){
            int flag = 0;
            for(int j = 2;j<i;j++){
                if(i %j==0){
                    flag++;
                }
                if(flag==2){
                    count++;
                    System.out.println(i);
                    break;
                }
            }
        }
        System.out.println(count);
* */

//1
/*
        int n = 0, a = 1;
        int count = 0;
        Scanner sc = new Scanner(System.in);
        while (a >= n) {
            n = sc.nextInt();
            a = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            if (i%2==0){
                if((i%a)==(a-1)){
                    count++;
                }
            }
        }
        System.out.println(count);
*/

