import java.util.Arrays;
import java.util.Scanner;

public class t04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int index=n / 2;
        int[] temp = Arrays.copyOf(a, n);
        Arrays.sort(temp);
        int mid = temp[index];

        for (int i = 0; i < n; i++) {
            if (a[i] >= mid) {
                a[i] = 0;
            } else {
                a[i] = mid - a[i] + 1;
            }
        }


        for (int i = 0; i < n; i++) {
            System.out.print( a[i]+" ");
        }

    }

}
