package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1158_요세푸스_문제 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        List<Integer> people = new ArrayList<>();
        for(int i = 1 ; i <= N ; i++){
            people.add(i);
        }

        int idx = -1;
        sb.append("<");
        while(!people.isEmpty()){
            idx = (idx + K) % people.size();
            sb.append(people.get(idx) + ", ");
            people.remove(idx);
            idx--;
        }

        sb.setLength(sb.length() - 2);
        sb.append(">");
        System.out.println(sb);
    }
}
