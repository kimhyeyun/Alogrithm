public class N으로_표현 {
    int answer;
    public int solution(int N, int number) {
        answer = Integer.MAX_VALUE;
        DFS(N, number, 0, 0);

        return answer > 8 ? -1 : answer;
    }

    private void DFS(int n, int number, int count, int cur) {
        if(count > 8) return;
        if (cur == number) {
            answer = Math.min(answer, count);
            return;
        }

        int tmp = 0;
        for (int i = 0; i + count < 9; i++) {
            tmp = tmp * 10 + n;

            DFS(n, number, count + i + 1, cur + tmp);
            DFS(n, number, count + i + 1, cur - tmp);
            DFS(n, number, count + i + 1, cur * tmp);
            DFS(n, number, count + i + 1, cur / tmp);
        }
    }
}
