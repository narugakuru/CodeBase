public class t02 {
    public static void main(String[] args) {

        int sum=0;
        long N = 2022222022;
        for (long i = 2022; i <= N; i++) {
            if (cheat(i)) {
                sum++;
            }
        }
        System.out.println(sum);


    }

    static boolean cheat(Long i) {
        long cur = 0;
        long temp = 0;
        boolean flag = true;
        while (i > 0) {
            cur = i % 10;
            if (flag) {
                if (cur >= temp) {

                }else {
                    flag=false;
                }
                temp=cur;
            } else {
                if (cur<=temp){

                }else {
                    return false;
                }
                temp=cur;
            }
            i/=10;
        }

        return true;
    }
}
