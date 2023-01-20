import java.util.Scanner;

public class T03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] rs = new int[26];
        int[] to = new int[26];
        char[] az = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',};
        for (char c : s.toCharArray()) {
            rs[c - 65]++;
        }

        int max = -1;
        int a = 0;
        for (int i = 0; i < 26; i++) {
            if (rs[i] == max) {
                to[++a] = i;
            }
            if (rs[i] > max) {
                for (int j = 0; j < 26; j++) {
                    to[j] = -1;
                }
                a = 0;
                to[a] = i;
                max = rs[i];
            }
        }

        for (int i = 0; i < 26; i++) {
            char A = 'A';
            if (to[i] >= 0) {
                System.out.print(az[to[i]]);
            } else {
                break;
            }
        }

    }
}