import java.util.ArrayList;
import java.util.List;

public class 기능개발 {
    public static void main(String[] args) {
        int[] p = {93,30,55};
        int[] s = {1,30,5};

        int[] ans = solution(p, s);

        for(int a : ans){
            System.out.println(a);
        }
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        int[] days = new int[progresses.length];
        for(int i = 0;i < progresses.length;i++){
            days[i] = (100-progresses[i])/speeds[i];

            if((100 - progresses[i]) % speeds[i] != 0){
                days[i] += 1;
            }
        }

        List<Integer> ans = new ArrayList<>();
        int cnt = 0;
        int max = days[0];
        for(int i = 0;i < days.length;i++){
            if(days[i] <= max)
                cnt++;
            else{
                ans.add(cnt);
                cnt = 0;
                max = days[i];
                i--;
            }
        }
        ans.add(cnt);

        answer = new int[ans.size()];
        int i = 0;
        for(int a : ans){
            answer[i++] = a;
        }

        return answer;
    }
}
