import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 큐2 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Deque<Integer> q = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");

            String str = st.nextToken();
            if(str.equals("push")){
                q.add(Integer.parseInt(st.nextToken()));
            }
            else if(str.equals("pop")){
                if(q.isEmpty())
                    sb.append("-1\n");
                else
                    sb.append(q.poll()+"\n");
            }
            else if(str.equals("size")){
                sb.append(q.size()+"\n");
            }
            else if(str.equals("empty")){
                if(q.isEmpty())
                    sb.append("1\n");
                else
                    sb.append("0\n");
            }
            else if(str.equals("front")){
                if(q.isEmpty())
                    sb.append("-1\n");
                else
                    sb.append(q.peek()+"\n");
            }
            else if(str.equals("back")){
                if(q.isEmpty())
                    sb.append("-1\n");
                else{
                    sb.append(q.peekLast()+"\n");
                }
                    
            }
        }

        System.out.println(sb);
    }
}
