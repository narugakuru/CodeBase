import java.util.*;

public class Coin {
    //n>3
    public List<List<Integer>> solution(int n, int coin) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> ret = new ArrayList<>(4);
        Collections.addAll(ret, 98, 0, 1, 1);
        results.add(ret);
        List<Integer> dp = new LinkedList<>();
        Collections.addAll(dp, 0, 1, 1);
        for (int i = 5; i <= n; i++) {

            List<Integer> retNow = new ArrayList<>(i);
            //初始化
            for (int i1 = 0; i1 < i; i1++) {
                retNow.add(0);
            }
            //除自己外需要支持的海盗数
            int num = i / 2;
            //找出需要金币最小的几个海盗

            Set<Integer> set = new HashSet<>();
            int sum = 0;
            while (--num >= 0) {
                int min = Integer.MAX_VALUE;
                int index = 0;
                for (int j = 0; j < dp.size(); j++) {
                    if (min > dp.get(j) && !set.contains(j)) {
                        min = dp.get(j);
                        index = j;
                    }
                }
                set.add(index);
                min = min + 1;
                sum += min;
                dp.set(index, min);
                retNow.set(index + 2, min);
            }
            dp.add(0, 0);
            if (coin - sum < 0)
                break;
            retNow.set(0, coin - sum);
            results.add(retNow);
        }

        return results;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new Coin().solution(100, 100);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}