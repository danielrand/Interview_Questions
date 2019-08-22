import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class NewYearChaos {
    static void minimumBribes(int[] q) {
        int bribeCount = 0;
        for (int i = q.length-1; i >=0; i--) {
            if (q[i] - (i+1) > 2) {
                System.out.println("TOO CHAOTIC");
                return;
            }
            for (int j = Math.max(0,q[i]-2); j < i; j++)
                if (q[j] > q[i]) bribeCount++;
        }
        System.out.println(bribeCount);
    }
    public static void swap (int [] q, int a, int b) {
        int temp = q[b];
        q[b] = q[a];
        q[a] = temp;
    }
    // Complete the minimumBribes function below.
    static void minimumBribesUsingBubbleSort(int[] q) {
        boolean sorted = false;
        int [] hashedSwapCount = new int [q.length+1];
        int totalSwaps = 0;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < q.length - 1; i++) {
                if (q[i] > q[i+1]) {
                    sorted = false;
                    hashedSwapCount[q[i]]++;
                    if (hashedSwapCount[q[i]] > 2 ) {
                        System.out.println("too chaotic");
                        return;
                    }
                    swap(q, i, i + 1);
                    totalSwaps++;
                }
            }
        }
        System.out.println(totalSwaps);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
