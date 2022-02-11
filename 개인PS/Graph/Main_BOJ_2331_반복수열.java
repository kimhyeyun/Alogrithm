package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_2331_반복수열 {
    static int A, P, ans;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        A = Integer.parseInt(stringTokenizer.nextToken());
        P = Integer.parseInt(stringTokenizer.nextToken());

        list = new ArrayList<>();
        list.add(A);

        while (true) {
            int tmp = list.get(list.size() - 1);
            int result = 0;
            while (tmp != 0) {
                result += Math.pow(tmp % 10, P);
                tmp /= 10;
            }

            if (list.contains(result)) {
                ans = list.indexOf(result);
                System.out.println(ans);
                break;
            }
            list.add(result);
        }
    }
}
