import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class string_seach {
    class Result {

        /*
         * Complete the 'getMaximumRemovals' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY order
         *  2. STRING source
         *  3. STRING target
         */

        static boolean flag;
        public static int getMaximumRemovals(List<Integer> order, String source, String target) {
            // Write your code here
            char[] sourceChar = source.toCharArray();
            for (int i = 0; i < order.size(); i++) {
                flag = false;
                sourceChar[order.get(i) - 1] = '-';
                String str = "";
                for (int j = 0; j < sourceChar.length; j++) {
                    if (j == 0 && sourceChar[j] == '-') continue;
                    if (sourceChar[j] != '-') str += sourceChar[j];
                    if (sourceChar[j] == '-' || j == sourceChar.length - 1){
                        check(str, 0, 0, target, new boolean[str.length()], "");
                        str = "";
                    }
                    if(flag) break;
                }
                if (!flag) return i;
            }
            return order.size() - 1;
        }

        private static void check(String str, int idx, int cnt, String target, boolean[] isVisited, String s) {
            if (cnt == target.length()) {
                if (s.equals(target)) {
                    flag = true;
                    return;
                }
                return;
            }

            for (int i = idx; i < str.length(); i++) {
                if(isVisited[i]) continue;
                isVisited[i] = true;
                s += str.charAt(i);
                check(str, i + 1, cnt + 1, target, isVisited, s);
                isVisited[i] = false;
                s = s.substring(0, s.length() - 1);
            }
            return;
        }

    }

    public static void main(String[] args) {
        List<Integer> order = new ArrayList<>();
        order.add(1);
        order.add(4);
        order.add(2);
        order.add(3);
        order.add(5);
        System.out.println(Result.getMaximumRemovals(order, "hkbdi", "kd"));
    }

    public class Solution {
        public static void main(String[] args) throws IOException {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//            int orderCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//            List<Integer> order = IntStream.range(0, orderCount).mapToObj(i -> {
//                        try {
//                            return bufferedReader.readLine().replaceAll("\\s+$", "");
//                        } catch (IOException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                    })
//                    .map(String::trim)
//                    .map(Integer::parseInt)
//                    .collect(toList());
//
//            String source = bufferedReader.readLine();
//
//            String target = bufferedReader.readLine();
//
//            int result = Result.getMaximumRemovals(order, source, target);
//
//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
//
//            bufferedReader.close();
//            bufferedWriter.close();

        }

    }
}
