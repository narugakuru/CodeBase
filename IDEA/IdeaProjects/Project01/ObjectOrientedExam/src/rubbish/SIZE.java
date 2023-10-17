package rubbish;

import java.lang.instrument.Instrumentation;

public class SIZE{

    public static void main(String[] args) throws NullPointerException{
        int in = 0;
        double db = 0;
        float fl = 0;
        byte by = 0;

        System.out.println(Sizeof(in));
        System.out.println(Sizeof(db));
        System.out.println(Sizeof(fl));
        System.out.println(Sizeof(by));

    }

    static Instrumentation inst;

    public static long Sizeof(Object o) {
        return inst.getObjectSize(o);
    }
}
