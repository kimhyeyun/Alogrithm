package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_10815_숫자_카드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(stringTokenizer.nextToken());
            if(set.contains(x)) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }

        System.out.println(sb);
    }
}
