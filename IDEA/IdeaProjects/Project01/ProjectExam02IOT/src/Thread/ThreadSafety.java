package Thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

public class ThreadSafety {
    public static void main(String[] args) {
        StringBuffer s1=new StringBuffer();//线程安全lock,synchronized
        StringBuilder s2=new StringBuilder();

        Vector<String> v=new Vector<String>();
        ArrayList<String> array =new ArrayList<>();

        Hashtable<String,String> ht=new Hashtable<>();
        HashMap<String,String> hm=new HashMap<>();
    }
}
