package lanqiao12;

public class t02卡片 {
    public static void main(String[] args) {
        new t02卡片().run();
    }
    void run() {
        System.out.println(calc(2021)); // 3181
    }
    int calc(int upper) {
        int[] count = new int[10];
        for (int n = 1, k = 1;; k = ++n)
            do {
                if (++count[k % 10] > upper)
                    return n - 1;
            } while ((k /= 10) > 0);
    }
/*

    //map枚举,实际上不需要map
    static Map<Integer,Integer> map;
    public static void main(String[] args) {
        map = new HashMap<Integer, Integer>();
        for(int i=0;i<=9;i++) {
            map.put(i,2021);
        }
        //从1开始拼凑数字
        for(int i=1;i<100000;i++) {
            if(pd(i))System.out.println(i);
            else break;
        }
    }
    //对要拼凑的数字进行处理
    public static boolean pd(int idx) {
        int index = idx;
        Integer a;
        //每次线处理个位数，然后/10，再处理个位数（原先的十位数)
        while(index>0) {
            a = map.get(index%10);
            if(a==0)return false;
            map.put(index%10, a-1);
            index/=10;
        }
        return true;
    }
*/

}
