package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2805_나무_자르기 {
    static int N, M;
    static long left = 1, right = 0, mid, ans = 0;
    static int[] heightOfTrees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        heightOfTrees = new int[N];
        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heightOfTrees[i] = Integer.parseInt(stringTokenizer.nextToken());

            right = right < heightOfTrees[i] ? heightOfTrees[i] : right;
        }

        while (left <= right) {
            mid = (left + right) / 2;
            if (check(mid)) {
                left = mid + 1;

                ans = ans < mid ? mid : ans;
            } else right = mid - 1;
        }
        System.out.println(ans);
    }

    private static boolean check(long mid) {
        long cnt = 0;
        for (int h : heightOfTrees) {
            long l = h - mid;
            if(l > 0) cnt += l;
        }

        return cnt >= M;
    }
}
