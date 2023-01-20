
public class 数分解 {

    public static void main(String[] args) {
        int n = 2019;
        int result = 0;
        for (int i = 1; i <= 2019; i++) {
            for (int j = i + 1; j <= n && n - i - j > j; j++) {
                int k = n - i - j;
                if (!check(i) && !check(j) && !check(k)) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    public static boolean check(int number) {
        while (number > 0) {
            int t = number % 10;
            if (t == 2 || t == 4) {
                return true;
            }
            number /= 10;
        }
        return false;
    }

}
