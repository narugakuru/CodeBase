package org.example;

import org.apache.hadoop.hive.ql.exec.UDF;

public class NumToSinoUDF extends UDF {

    // 零、壹、贰、叁、肆、伍、陆、柒、捌、玖、拾、佰、仟、万、亿
    private static String[] sino = new String[] {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    private static String[] unit = new String[] {"","拾","佰","仟","万","拾","佰","仟","亿"};
    public String evaluate(Integer num) {

        StringBuilder result = new StringBuilder();
        int len = num.toString().length();
        for (int i = 0; i < len; i++) {
            int shu = num % 10;
            if (shu != 0 || i == 4) {
                result.append(unit[i]);
            }
            result.append(sino[shu]);
            num = num / 10;

        }

        return result.reverse().toString()
                .replaceFirst("零+$", "")
                .replaceFirst("零{2,}", "零");
    }

    public static void main(String[] args) {
        NumToSinoUDF nts = new NumToSinoUDF();
        System.out.println(nts.evaluate(123456));
        System.out.println(nts.evaluate(100000));
        System.out.println(nts.evaluate(1000020));
    }
}
