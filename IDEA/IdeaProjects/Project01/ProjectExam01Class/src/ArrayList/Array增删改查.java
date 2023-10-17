package ArrayList;

import java.util.ArrayList;
//array增删改查
public class Array增删改查 {
    public static void main(String[] args) {
//        add,remove,set,get,size;增删改查
        ArrayList<String> array = new ArrayList<>();
        array.add("hello");
        System.out.println("array:" + array.add("world"));
        array.add(1, "java");
        System.out.println(array);

        System.out.println("set:" + array.set(1, "giao"));
        System.out.println(array);

        System.out.println("get:" + array.get(0));

        System.out.println("remove:" + array.remove("java"));
        System.out.println("remove:" + array.remove(1));
        System.out.println("size；"+array.size());
        System.out.println(array);


    }
}
