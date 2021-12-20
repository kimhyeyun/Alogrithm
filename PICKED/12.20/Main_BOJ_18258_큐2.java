import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_BOJ_18258_큐2 {
    static LinkedList<Integer> queue;
    static StringTokenizer stringTokenizer;
    static StringBuilder stringBuilder;
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        stringBuilder = new StringBuilder();

        queue = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());

        while(N-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());
            String str = stringTokenizer.nextToken();

            if(str.equals("push")){
                int x = Integer.parseInt(stringTokenizer.nextToken());
                queue.add(x);
            }
            else if(str.equals("pop")){
                stringBuilder.append(queue.isEmpty() ? -1 : queue.pollFirst()).append("\n");
            }
            else if(str.equals("size")){
                stringBuilder.append(queue.size()).append("\n");
            }
            else if(str.equals("empty")){
                stringBuilder.append(queue.isEmpty() ? 1 : 0).append("\n");
            }
            else if(str.equals("front")){
                stringBuilder.append(queue.isEmpty() ? -1 : queue.peekFirst()).append("\n");
            }
            else{
                stringBuilder.append(queue.isEmpty() ? -1 : queue.peekLast()).append("\n");
            }
        }

        System.out.println(stringBuilder);
    }


    public static void main(String[] args) throws IOException {
        solution();
    }
}
