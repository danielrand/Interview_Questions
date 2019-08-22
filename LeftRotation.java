import java.io.*;
import java.util.*;

public class LeftRotation {

    // Complete the rotLeft function below.
    static int[] rotLeft1(int[] a, int d) {
        int newIndex;
        int shiftedAry [] = new int [a.length];
        for (int i = 0; i < a.length; i++) {
            newIndex = ((i-d)%a.length + a.length) % a.length;
            shiftedAry[newIndex] = a[i];
        }
        return shiftedAry;
    }
    static int[] rotLeft(int[] a, int d) {
        int n = a.length;
        int[] rotated = new int[n];
        d=d%n;
        System.arraycopy(a, d, rotated, 0, n - d);
        System.arraycopy(a, 0, rotated, n - d, d);
        return rotated;
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] result = rotLeft(a, d);
        for (int i: result)
            System.out.print(i + " ");
        scanner.close();
    }
}
