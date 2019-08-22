import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MinimumSwaps {
    public static void swap (int arr[], int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int i = 0, swapCount = 0;
        while (i<  arr.length-1) {
            while (arr[i] != i+1) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i] == j+1 || arr[j] == i+1) {
                        swap(arr, i, j);
                        swapCount++;
                        break;
                    }
                }
            }
            i++;
        }
        return swapCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);
        System.out.println(res);

        scanner.close();
    }
}
