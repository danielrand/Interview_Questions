import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FrequencyQueries {
    static List<Integer> freqQueryV2(List<List<Integer>> queries) {
        ArrayList <Integer> output = new ArrayList<Integer>();
        HashMap<Integer,Integer> numberCounts = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> histogram = new HashMap<Integer, Integer>();
        for (int i = 0; i < queries.size(); i++) {
            int operation = queries.get(i).get(0);
            int value = queries.get(i).get(1);
            if (operation != 3) {
                if (operation == 1) {
                    // if value not yet in map add 1
                    if (!(numberCounts.containsKey(value))) numberCounts.put(value, 1);
                        // otherwise increment value
                    else numberCounts.replace(value, (numberCounts.get(value) + 1));
                    int newValue = numberCounts.get(value);
                    // if  doesn't exist in histogram add 1
                    if (!(histogram.containsKey(newValue)))
                        histogram.put(newValue,1);
                        // otherwise increment histogram count by 1
                    else histogram.replace(newValue, histogram.get(newValue)+1);
                    // histogram has value - 1 decrement value -1 and remove if 0
                    if (histogram.containsKey(newValue-1)) {
                        histogram.replace(newValue - 1, histogram.get(newValue - 1) - 1);
                        if (histogram.get(newValue - 1) == 0) histogram.remove(newValue-1);
                    }
                } else if (operation == 2 && numberCounts.containsKey(value)) {
                    // decrement count in map
                    numberCounts.replace(value, (numberCounts.get(value) - 1));
                    // remove if 0
                    int newValue;
                    if (numberCounts.get(value) == 0)
                        newValue = numberCounts.remove(value);
                    else newValue = numberCounts.get(value);
                    // if histogram doesnt have the freq and its not 0 put 1
                    if (newValue != 0 && !(histogram.containsKey(newValue)))
                        histogram.put(newValue,1);
                        // otherwise increment count at freq
                    else if (histogram.containsKey(newValue))
                        histogram.replace(newValue, histogram.get(newValue)+1);
                    // if histogram has freq newVal + 1, decrement count
                    if (histogram.containsKey(newValue+1)) {
                        histogram.replace(newValue + 1, histogram.get(newValue + 1) - 1);
                        if (histogram.get(newValue + 1) == 0) histogram.remove(newValue + 1);
                    }
                }
            }
            else {
                output.add(histogram.containsKey(value) ? 1 : 0);
            }
        }
        return output;
    }
    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        ArrayList <Integer> output = new ArrayList<Integer>();
        HashMap<Integer,Integer> numberCounts = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> histogram = new HashMap<Integer, Integer>();
        for (int i = 0; i < queries.size(); i++) {
            int operation = queries.get(i).get(0);
            int value = queries.get(i).get(1);
            if (operation != 3) {
                if (operation == 1) {
                    // if value not yet in map add 1
                    if (!(numberCounts.containsKey(value))) numberCounts.put(value, 1);
                    // otherwise increment value
                    else numberCounts.replace(value, (numberCounts.get(value) + 1));
                    int newValue = numberCounts.get(value);
                    // if  doesn't exist in histogram add 1
                    if (!(histogram.containsKey(newValue)))
                        histogram.put(newValue,1);
                    // otherwise increment histogram count by 1
                    else histogram.replace(newValue, histogram.get(newValue)+1);
                    // histogram has value - 1 decrement value -1 and remove if 0
                    if (histogram.containsKey(newValue-1)) {
                        histogram.replace(newValue - 1, histogram.get(newValue - 1) - 1);
                        if (histogram.get(newValue - 1) == 0) histogram.remove(newValue-1);
                    }
                } else if (operation == 2 && numberCounts.containsKey(value)) {
                    // decrement count in map
                    numberCounts.replace(value, (numberCounts.get(value) - 1));
                    // remove if 0
                    int newValue;
                    if (numberCounts.get(value) == 0)
                        newValue = numberCounts.remove(value);
                    else newValue = numberCounts.get(value);
                    // if histogram doesnt have the freq and its not 0 put 1
                    if (newValue != 0 && !(histogram.containsKey(newValue)))
                        histogram.put(newValue,1);
                    // otherwise increment count at freq
                    else if (histogram.containsKey(newValue))
                        histogram.replace(newValue, histogram.get(newValue)+1);
                    // if histogram has freq newVal + 1, decrement count
                    if (histogram.containsKey(newValue+1)) {
                        histogram.replace(newValue + 1, histogram.get(newValue + 1) - 1);
                        if (histogram.get(newValue + 1) == 0) histogram.remove(newValue + 1);
                    }
                }
            }
            else {
                output.add(histogram.containsKey(value) ? 1 : 0);
            }
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            String[] queriesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> queriesRowItems = new ArrayList<>();

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowTempItems[j]);
                queriesRowItems.add(queriesItem);
            }

            queries.add(queriesRowItems);
        }

        List<Integer> ans = freqQuery(queries);

        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }

        bufferedReader.close();
    }
}
