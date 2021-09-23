import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class 표편집 {
    // ㅅㅣ가ㄴ초ㅏ...
/* 
    public static String solution(int n, int k, String[] cmd) {
        String answer = "";

        Stack<Integer> delStack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        int now = k;
        int nowValue = k;

        for(int i = 0 ; i < n ; i++){
            list.add(i);
        }

        for(String s : cmd){
            String[] op = s.split(" ");

            if(op[0].equals("D")){
                int x = Integer.parseInt(op[1]);
                now = now + x;
                nowValue = list.get(now);
            }
            else if(op[0].equals("U")){
                int x = Integer.parseInt(op[1]);
                now = now - x;
                nowValue = list.get(now);
            }
            else if(op[0].equals("C")){
                delStack.push(list.get(now));
                list.remove(now);
                if(list.size() <= now)
                    now = now - 1;
                
                nowValue = list.get(now);
            }
            else{
                list.add(delStack.pop());
                Collections.sort(list);
                if(nowValue != list.get(now)){
                    now = list.indexOf(nowValue);
                }
            }
        }

        int idx = 0;
        for(int i = 0 ; i < list.size() ; i++){
            if(list.get(i) == idx) 
                answer += "O";
            else{
                answer += "X";
                i--;
            }
            idx++;
        }
        

        return answer; 
    } */

    // 연결리스트를 이용한 풀이

    public static class Node{
        int pre;
        int cur;
        int next;

        Node(int pre, int cur, int next){
            this.pre = pre;
            this.cur = cur;
            this.next = next;
        }
    }

    public static String solution(int n, int k, String[] cmd){

        int[] pre = new int[n];
        int[] next = new int[n];

        for(int i = 0 ; i < n; i++){
            pre[i] = i-1;
            next[i] = i+1;
        }
        next[n-1] = -1;

        Stack<Node> delStack = new Stack<>();
        StringBuilder sb = new StringBuilder("O".repeat(n));
        int now = k;

        for(String c : cmd){
            String[] op = c.split(" ");

            if(op[0].equals("U")){
                int x = Integer.parseInt(op[1]);
                while(x-- > 0){
                    now = pre[now];
                }
            }
            else if(op[0].equals("D")){
                int x = Integer.parseInt(op[1]);
                while(x-- > 0){
                    now = next[now];
                }
            }
            else if(op[0].equals("C")){
                delStack.push(new Node(pre[now], now, next[now]));
                if(pre[now] != -1)
                    next[pre[now]] = next[now];
                if(next[now] != -1)
                    pre[next[now]] = pre[now];
                sb.setCharAt(now, 'X');

                if(next[now] != -1)
                    now = next[now];
                else
                    now = pre[now];
            }
            else{
                Node node = delStack.pop();

                if(node.pre != -1)
                    next[node.pre] = node.cur;
                if(node.next != -1)
                    pre[node.next] = node.cur;
                
                sb.setCharAt(node.cur, 'O');
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        System.out.println(solution(8, 2, cmd));
    }
    
}
