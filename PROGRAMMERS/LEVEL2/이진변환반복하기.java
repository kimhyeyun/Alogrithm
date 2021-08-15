import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class 이진변환반복하기 {
    public static int[] solution(String s) {
        int[] answer = new int[2];
        int cnt = 0;
        int zeroCnt = 0;
        
        while(!s.equals("1")){
            cnt++;

            int size = s.length();
            s = s.replace("0", "");

            int l = s.length();
            zeroCnt += size-l;

            s = Integer.toBinaryString(l);
        }

        answer[0] = cnt;
        answer[1] = zeroCnt;

        return answer;
    }
    public static void main(String[] args) {
       int[] ans = solution("110010101001");

       for(int a : ans){
           System.out.println(a);
       }
    }
}
