package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 문자열집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        
        List<String> list = new LinkedList<>();
        int answer = 0;

        for(int i = 0; i < N; i++){
            list.add(br.readLine());
        }

        for(int i = 0; i < M; i++){
            if(list.contains(br.readLine()))
                answer++;
        }
        
        System.out.println(answer);
        
    }
}
