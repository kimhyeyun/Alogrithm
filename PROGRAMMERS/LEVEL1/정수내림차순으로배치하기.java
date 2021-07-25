import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 정수내림차순으로배치하기 {
    public static void main(String[] args) {
        System.out.println(reverseLong((long) 118372));
    }

    public static long solution(long n) {
        long answer = 0;

        List<Integer> list = new ArrayList<>();
        while(n!=0){
            list.add((int)(n%10));
            n /= 10;
        }

        Collections.sort(list);

        for(int i = 0;i < list.size();i++){
           answer += Math.pow(10, i) * list.get(i);
        }

        return answer;
    }

    // 다른사람 코드
    static String res;
    public static long reverseLong(Long n){
        res = "";
        
        // n -> String -> Char 배열
        // 각 char을 앞으로 붙임
        Long.toString(n).chars().sorted().forEach(c -> res = Character.valueOf((char) c) + res);

        return Long.parseLong(res);
    }
}
