public class test {
    public static void main(String[] args) {
        byte aByte = 13;
        System.out.println(String.format("%02x", aByte));
        byte bByte  = Integer.valueOf("9f",16).byteValue();
        System.out.println(Integer.valueOf("9f",16));
        System.out.println(bByte);
/*
        byteValue()
        作为一个窄化转换后 byte返回该 Integer值。
        valueOf(String s, int radix)
        返回一个 Integer持物提取指定的 String解析时由第二个参数给定值的基数。
        parseInt(String s, int radix)
        将字符串参数作为一个符号整数，进制由第二个参数指定。*/
    }
}
