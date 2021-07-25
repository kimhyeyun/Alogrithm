import java.util.ArrayList;

public class 삼진법뒤집기 {

    public static void main(String[] args) {
        System.out.println(solution(125));
    }

    public static int solution(int n) {
        int answer = 0;
        ArrayList<Integer> three = new ArrayList<>();

        while(n != 0){
            three.add(n%3);
            n/=3;
        }

        for(int i=three.size()-1;i>=0;i--){
            int a = three.get(i);
            answer += Math.pow(3, three.size()-1-i)*a;
       }

        return answer;
    }
}
