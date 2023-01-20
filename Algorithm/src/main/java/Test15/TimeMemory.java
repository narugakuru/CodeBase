package Test15;

public class TimeMemory {
    public static long concurrentTime1, concurrentTime2, concurrentMemory1, concurrentMemory2;

    public static void start() {
        //得到程序开始时的系统时间（纳秒级，最终转化毫秒，保留小数点后两位）
        concurrentTime1 = System.nanoTime();
        //得到虚拟机运行、程序开始执行时jvm所占用的内存。
        Runtime runtime = Runtime.getRuntime();
        concurrentMemory1 = runtime.freeMemory();
    }

    public static void end() {
        //得到程序执行完毕时的系统时间（毫秒级）
        concurrentTime2 = System.nanoTime();
        //得到虚拟机运行、所要测试的执行代码执行完毕时jvm所占用的内存（byte）。
        Runtime runtime = Runtime.getRuntime();
        concurrentMemory2 = runtime.freeMemory();
        //计算start和end之间的代码执行期间所耗时间(ms)与内存(M)。
        // 1毫秒(ms) = 1000微秒(us) = 1000 000纳秒(ns)
        // 1M = 1*2^20 byte = 1024 * 1024 byte;
        long time = (concurrentTime2 - concurrentTime1);
        long memory = (concurrentMemory1 - concurrentMemory2);
        System.out.println("消耗时间"+ time +"ms, 消耗内存：" +memory);
    }

}
