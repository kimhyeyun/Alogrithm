package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_BOJ_10866_덱 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;


        Deque<Integer> deque = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        while(N-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());
            String input = stringTokenizer.nextToken();

            if(input.equals("push_front")){
                int x = Integer.parseInt(stringTokenizer.nextToken());
                deque.addFirst(x);
            }
            else if(input.equals("push_back")){
                int x = Integer.parseInt(stringTokenizer.nextToken());
                deque.addLast(x);
            }
            else if(input.equals("pop_front")){
                if(deque.isEmpty())
                    sb.append(-1).append("\n");
                else
                    sb.append(deque.pollFirst()).append("\n");
            }
            else if(input.equals("pop_back")){
                if(deque.isEmpty())
                    sb.append(-1).append("\n");
                else
                    sb.append(deque.pollLast()).append("\n");
            }
            else if(input.equals("size")){
                sb.append(deque.size()).append("\n");
            }
            else if(input.equals("empty")){
                sb.append(deque.isEmpty() ? 1 : 0).append("\n");
            }
            else if(input.equals("front")){
                sb.append(deque.isEmpty() ? -1 : deque.peekFirst()).append("\n");
            }
            else{
                sb.append(deque.isEmpty() ? -1 : deque.peekLast()).append("\n");
            }
        }
        System.out.print(sb);
    }
}
