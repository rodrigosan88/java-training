package br.rodrigo.training.arrays;

import java.io.IOException;
import java.util.Scanner;

public class Hourglass {

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int sum = 0;
        Integer higher = null;

        for (int i = 0; i <= arr.length - 3; i++) {

            for (int j = 0; j <= arr[i].length - 3; j++) {
                sum = arr[i][j] + sum;
                sum = arr[i][j+1] + sum;
                sum = arr[i][j+2] + sum;
                sum = arr[i+1][j+1] + sum;
                sum = arr[i+2][j] + sum;
                sum = arr[i+2][j+1] + sum;
                sum = arr[i+2][j+2] + sum;

                if(higher == null){
                    higher = sum;
                } else if(sum > higher) {
                    higher = sum;
                }
                sum = 0;
            }
        }

        return higher;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int[][] arr =
                {{1, 1, 1, 0, 0, 0},
                 {0, 1, 0, 0, 0, 0},
                 {1, 1, 1, 0, 0, 0},
                 {0, 0, 2, 4, 4, 0},
                 {0, 0, 0, 2, 0, 0},
                 {0, 0, 1, 2, 4, 0}};

        int[][] arr2 =
                {{-1, -1, 0, -9, -2, -2},
                {-2, -1, -6, -8, -2, -5},
                {-1, -1, -1, -2, -3, -4},
                {-1, -9, -2, -4, -4, -5},
                {-7, -3, -3, -2, -9, -9},
                {-1, -3, -1, -2, -4, -5}};

        int result = hourglassSum(arr2);

        System.out.println(result);
    }

}
