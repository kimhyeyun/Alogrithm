package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_10816_숫자_카드_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(stringTokenizer.nextToken());
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int M = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(stringTokenizer.nextToken());
            if(map.containsKey(x)) sb.append(map.get(x)).append(" ");
            else sb.append("0").append(" ");
        }

        System.out.println(sb);
    }
}
