package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1168_요세푸스_문제_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken()) - 1;

        List<Integer> people = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();
        for(int i = 1 ; i <= N ; i++){
            people.add(i);
        }

        int idx = 0;
        sb.append("<");
        for(int i = 0 ; i < N ;i++){
            idx = (idx + K) % people.size();
            answer.add(people.get(idx));
            people.remove(idx);
        }

        for(int i = 0 ; i < answer.size() - 1; i++)
            sb.append(answer.get(i) + ", ");

        sb.append(answer.get(answer.size()) - 1);
        sb.append(">");
        System.out.println(sb.toString());
    }
}
