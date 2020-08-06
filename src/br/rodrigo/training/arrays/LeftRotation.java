package br.rodrigo.training.arrays;

import java.io.IOException;

public class LeftRotation {

    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {

        int[] rotatedArray = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            int pos = i - d;
            if(pos < 0) {
                pos = pos + a.length;
            }

            rotatedArray[pos] = a[i];
        }

        return rotatedArray;
    }
    public static void main(String[] args) throws IOException {

        int[] a = {1,2,3,4,5};

        int[] result = rotLeft(a, 4);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

}
