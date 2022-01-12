package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_11651_좌표_정렬하기_2 {

    static class pair implements Comparable<pair>{
        int x;
        int y;

        public pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(pair o) {
            int result = this.y - o.y;

            result = result == 0 ? this.x - o.x : result;

            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        List<pair> pairs = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ;i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            pairs.add(new pair(x, y));
        }

        Collections.sort(pairs);
        for(pair p : pairs){
            sb.append(p.x + " " + p.y).append("\n");
        }

        System.out.println(sb);

    }

}
