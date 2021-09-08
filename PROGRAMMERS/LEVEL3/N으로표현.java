public class N으로표현{

    static int answer = 9;
    public static int solution(int N, int number) {

        DFS(N, number, 0, 0);

        if(answer == 9)
            return -1;
        return answer;
    }

    private static void DFS(int n, int number, int cnt, int curNumber) {
        if(cnt > 8)
            return;
    
        if(curNumber == number){
            answer = Math.min(answer, cnt);
            return;
        }

        int tmp = 0;
        for(int i = 0 ; i + cnt < 9 ; i++){
            tmp = tmp * 10 + n;

            DFS(n, number, cnt + i + 1, curNumber + tmp);
            DFS(n, number, cnt + i + 1, curNumber - tmp);
            DFS(n, number, cnt + i + 1, curNumber * tmp);
            DFS(n, number, cnt + i + 1, curNumber / tmp);
        }

    }

    public static void main(String[] args) {
        System.out.println(solution(5, 12));
    }
}