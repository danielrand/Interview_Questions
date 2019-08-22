import java.io.*;
import java.util.*;;

public class HourglassSum {

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int maxSum = -999999, sum = 0;
        for (int i = 1; i < arr.length-1; i++) {
            for (int j = 1; j < arr[0].length-1; j++) {
                sum = 0;
                sum += arr[i][j]  + arr[i-1][j-1] + arr[i-1][j] + arr[i-1][j+1] + arr[i+1][j-1] + arr[i+1][j] + arr[i+1][j+1];
                if (sum > maxSum)
                    maxSum = sum;
            }
        }
        return maxSum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);
        System.out.println(result);
        scanner.close();
    }
}
