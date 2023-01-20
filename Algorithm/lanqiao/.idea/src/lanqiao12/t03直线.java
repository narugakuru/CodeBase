package lanqiao12;

import java.util.HashSet;
import java.util.Set;

public class t03直线 {
    public static void main(String[] args) {
        new t03直线().run();
    }

    int X = 20, Y = 21;

    void run() {
        Set<Line> set = new HashSet();
        for (int x1 = 0; x1 < X; x1++)
            for (int y1 = 0; y1 < Y; y1++)
                for (int x2 = x1; x2 < X; x2++)
                    for (double y2 = 0; y2 < Y; y2++)
                        if (x1 != x2) {
                            double k = (y2 - y1) / (x2 - x1);
                            double b = -x1 * k + y1;
                            set.add(new Line(k, b));
                        }
        System.out.println(set.size() + X); // 41255
    }

    class Line {
        double k, b;

        Line(double b, double k) {
            this.k = k;
            this.b = b;
        }

        @Override
        public boolean equals(Object obj) {
            return k == ((Line) obj).k && b == ((Line) obj).b;
        }

        @Override
        public int hashCode() {
            return (int) k ^ (int) b;
        }
    }

}
