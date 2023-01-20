package lanqiao11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class t02日期 {

    // 不需要非要找12点，因为都是从0点算也是一样的
    public static void main(String[] args) throws ParseException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format.parse("1921-7-23");
        Date date2 = format.parse("2020-7-1");
        int a = (int) ((date2.getTime() - date1.getTime()) / (1000 * 60));
        System.out.println(a);
    }
}
