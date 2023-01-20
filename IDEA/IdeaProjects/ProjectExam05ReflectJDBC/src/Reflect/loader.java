package Reflect;

import sun.applet.AppletClassLoader;

public class loader {
    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
//        AppletClassLoader系统

        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);
//        PlatformClassLoader平台

        ClassLoader n = parent.getParent();
        System.out.println(n);
//      null内置
    }

}
