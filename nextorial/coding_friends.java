import java.io.*;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
public class coding_friends {

    class Result {

        /*
         * Complete the 'minNum' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER samDaily
         *  2. INTEGER kellyDaily
         *  3. INTEGER difference
         */

        public static int minNum(int samDaily, int kellyDaily, int difference) {
            if(samDaily >= kellyDaily) return -1;

            int diff = kellyDaily - samDaily;
            int div = difference / diff;

            return 1 + div;
        }

    }
    public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int samDaily = Integer.parseInt(bufferedReader.readLine().trim());

            int kellyDaily = Integer.parseInt(bufferedReader.readLine().trim());

            int difference = Integer.parseInt(bufferedReader.readLine().trim());

            int result = Result.minNum(samDaily, kellyDaily, difference);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
