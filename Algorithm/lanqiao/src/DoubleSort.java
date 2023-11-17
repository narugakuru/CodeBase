import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DoubleSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Integer> array = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            array.add(i);
        }

        for (int i = 0; i < m; i++) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            if (p == 0) {
                Collections.sort(array.subList(0, q), Collections.reverseOrder());
            } else {
                Collections.sort(array.subList(q - 1, array.size()));
            }
        }

        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}
