package org.example;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;


public class UdfTest extends UDF {
    private static final String[] UNITS = {"", "十", "百", "千", "万", "十万", "百万", "千万", "亿"};
    private static final String[] DIGITS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    public Text evaluate(Integer number) {
        if (number == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int unitIndex = 0;
        do {
            int num = number % 10;
            if (num == 0) {
                // 如果当前数字为0，则只有在单位为万、亿等时才添加单位
                if (unitIndex == 4 || unitIndex == 8) {
                    sb.insert(0, UNITS[unitIndex]);
                }
            } else {
                sb.insert(0, DIGITS[num] + UNITS[unitIndex]);
            }
            number /= 10;
            unitIndex++;
        } while (number > 0);
        return new Text(sb.toString());
    }

}
