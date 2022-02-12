package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1654_랜선_자르기 {
    static int K, N, max = 0;
    static long ans = 0;
    static int[] lenOfWire;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        K = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);

        lenOfWire = new int[K];
        for (int i = 0; i < K; i++) {
            lenOfWire[i] = Integer.parseInt(br.readLine());
            max = max < lenOfWire[i] ? lenOfWire[i] : max;
        }

        long left = 1;
        long right = max;
        while (left <= right) {
            long mid = (left + right) / 2;

            if (check(mid)) {
                ans = ans < mid ? mid : ans;

                left = mid + 1;
            } else right = mid - 1;
        }

        System.out.println(ans);
    }

    private static boolean check(long len) {
        int cntOfWire = 0;
        for (int l : lenOfWire) {
            cntOfWire += l / len;
        }
        return cntOfWire >= N;
    }
}
