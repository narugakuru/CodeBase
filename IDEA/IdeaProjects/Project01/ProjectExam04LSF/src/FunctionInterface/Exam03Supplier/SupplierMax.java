package FunctionInterface.Exam03Supplier;

import java.util.ArrayList;
import java.util.function.Supplier;

public class SupplierMax {
    public static void main(String[] args) {
        int[] arr={51,62,48,31,44};
        System.out.println(max(arr));

    }

    private static int getMax(Supplier<Integer> sup){
        return sup.get();
    }

    private static int max(int[] arr){
        return getMax(()->{
            int max= arr[0];
            for(int i=0;i<arr.length;i++){
                if(max<arr[i])
                    max=arr[i];
            }
            return max;
        });

    }
}
