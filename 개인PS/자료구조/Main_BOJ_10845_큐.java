package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_BOJ_10845_큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer;

        Deque<Integer> queue = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());

        while(N-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());

            String input = stringTokenizer.nextToken();
            if(input.equals("push")){
                int x = Integer.parseInt(stringTokenizer.nextToken());
                queue.addLast(x);
            }
            else if(input.equals("pop")){
                if(queue.isEmpty())
                    stringBuilder.append(-1).append("\n");
                else
                    stringBuilder.append(queue.pollFirst()).append("\n");
            }
            else if(input.equals("size")){
                stringBuilder.append(queue.size()).append("\n");
            }
            else if(input.equals("empty")){
                if(queue.isEmpty())
                    stringBuilder.append(1).append("\n");
                else
                    stringBuilder.append(0).append("\n");
            }
            else if(input.equals("front")){
                if(queue.isEmpty())
                    stringBuilder.append(-1).append("\n");
                else
                    stringBuilder.append(queue.peekFirst()).append("\n");
            }
            else{
                if(queue.isEmpty())
                    stringBuilder.append(-1).append("\n");
                else
                    stringBuilder.append(queue.peekLast()).append("\n");
            }
        }
        System.out.print(stringBuilder);

    }
}
