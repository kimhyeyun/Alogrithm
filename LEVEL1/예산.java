import java.util.Arrays;

public class 예산 {
    public static void main(String[] args) {
        int[] d = {2,2,3,3};
        System.out.println(solution(d, 10));
    }

    public static int solution(int[] d, int budget) {
        int answer = 0;

        Arrays.sort(d);

        for(int i=0;i<d.length;i++){
            if(d[i] <= budget){
                budget-=d[i];
                answer++;
            }
            else{
                break;
            }
        }

        return answer;
    }
}
