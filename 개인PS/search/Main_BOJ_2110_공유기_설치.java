package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2110_공유기_설치 {
    static int N, C, left, right, mid;
    static int[] houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);
        left = 1;
        right = houses[N - 1] - houses[0] + 1;

        while (left < right) {
            mid = (left + right) / 2;

            if (canInstall(mid) < C) {
                right = mid;
            } else left = mid + 1;

        }
        System.out.println(left - 1);

    }

    private static int canInstall(int mid) {
        int cnt = 1;
        int lastHouse = houses[0];

        for (int i = 1; i < N; i++) {
            int nowHouse = houses[i];

            if (nowHouse - lastHouse >= mid) {
                cnt += 1;
                lastHouse = nowHouse;
            }
        }

        return cnt;
    }
}
