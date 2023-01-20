package rubbish;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class newclass {
    public static void main(String[] args) throws IOException {
        StringBuffer sb = new StringBuffer(5);
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        sb = br.readLine();
        String s = br.readLine();
//        String s = "abc";
        for (int i = 0; i < s.length(); i++)
            System.out.println(s.charAt(i));
    }

}
