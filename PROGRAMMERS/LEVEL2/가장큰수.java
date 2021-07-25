import java.util.Arrays;
import java.util.Comparator;

public class 가장큰수 {

    public static String solution(int[] numbers) {
        String answer = "";

        // 문자열 리턴을 해줄 스트링배열 생성
        String[] str = new String[numbers.length];

        // int 배열 -> String 배열
        for(int i = 0; i < numbers.length;i++){
            str[i] = String.valueOf(numbers[i]);
        }

        // 내림차순 정렬
        Arrays.sort(str, new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                // TODO Auto-generated method stub
                return (o2+o1).compareTo(o1+o2);
            }
            
        });

        if(str[0].equals("0"))
            return "0";

        // 0이 아니면 문자열을 더해줌
        for(String s : str)
            answer += s;

        return answer;
    }

    public static void main(String[] args) {
        int[] n = {6,10,2};
        System.out.println(solution(n));
    }
}
