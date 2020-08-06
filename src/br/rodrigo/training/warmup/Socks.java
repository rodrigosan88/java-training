package br.rodrigo.training.warmup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Socks {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        int count = 0;
        Map<Integer, Integer> socksMap = new HashMap<>();

        for (int i = 0; i < ar.length; i++) {
            if(socksMap.containsKey(ar[i])){
                socksMap.replace(ar[i], socksMap.get(ar[i]) + 1);
            } else {
                socksMap.put(ar[i], 1);
            }
        }
        for(Map.Entry<Integer, Integer> entry : socksMap.entrySet()) {
            int div = entry.getValue() / 2;
            count = count + div;

        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        int ar[] = {10, 20, 20, 10, 10, 30, 50, 10, 20};
        int result = sockMerchant(ar.length, ar);
        System.out.println(result);
    }

}
