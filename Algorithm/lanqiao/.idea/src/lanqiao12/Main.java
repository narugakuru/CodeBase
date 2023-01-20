package lanqiao12;

public class Main {
    public static void main(String[] args) {
        System.out.println(t02(2021));
    }

    //  ASC
    void test01() {
        System.out.println((int) 'L'); // 76
    }

    //  卡片
    static int t02(int upper) {
        int[] count = new int[10];
        for (int n = 1, k = 1; ; k = ++n)
            do {
                if (++count[k % 10] > upper)
                    return n - 1;
            } while ((k /= 10) > 0);

    }

    //    直线
    static void t03() {

    }


}
