import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class AC {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            String command = br.readLine();
            int N = Integer.parseInt(br.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), "[],");
           
            // list 이용 -> 시간초과
            /* Boolean flag = true;
            List<Integer> list = new LinkedList<>();
            for(int i = 0 ;i < N; i++){
                list.add(Integer.parseInt(stringTokenizer.nextToken()));
            }

            for(int i = 0; i < command.length(); i++){
                char c = command.charAt(i);

                if(c == 'R'){
                    Collections.reverse(list);
                }
                else if(c == 'D'){
                    if(list.isEmpty()){
                        sb.append("error\n");
                        flag = false;
                        break;
                    }
                    else{
                        list.remove(0);
                    }

                }
            }

            if(flag){
                sb.append("[");
                for(int l : list){
                    sb.append(l).append(",");
                }
                sb.deleteCharAt(sb.length()-1);
                sb.append("]\n");
            }
        }
        System.out.println(sb); */

            boolean isLeft = true;
            Boolean error = false;
            Deque<Integer> deque = new LinkedList<>();
            
            while(stringTokenizer.hasMoreTokens()){
                deque.add(Integer.parseInt(stringTokenizer.nextToken()));
            }

            for(int i = 0; i < command.length(); i++){
                char c = command.charAt(i);

                if(c == 'R'){
                    isLeft = !isLeft;
                }

                else if(c == 'D'){

                    if(deque.isEmpty()){
                        sb.append("error\n");
                        error = true;
                        break;
                    }

                    if(isLeft)
                        deque.pollFirst();
                    else
                        deque.pollLast();         
                }
            }

            if(!deque.isEmpty()){
                sb.append("[");

                while(deque.size() > 1){
                    if(isLeft){
                        sb.append(deque.pollFirst() + ",");
                    }
                    else{
                        sb.append(deque.pollLast() + ",");
                    }
                }

                sb.append(deque.poll() + "]").append("\n");
            }

            else if(!error){
                sb.append("[]\n");
            }
        }

        System.out.println(sb);
    }
}
