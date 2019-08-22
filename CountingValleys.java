import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CountingValleys {

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        int height = 0, valleyCount = 0;
        for (char c: s.toCharArray()) {
            if (c=='U')
                height++;
            else {
                height--;
                if (height == -1)
                    valleyCount++;
            }
        }
        return valleyCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.print("Enter n: ");

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        System.out.print("Enter String: ");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);
        System.out.println("result: " + result);

        scanner.close();
    }
}
