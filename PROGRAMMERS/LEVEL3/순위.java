public class 순위 {

    public static int solution(int n, int[][] results) {
        // Floyd Warshall 알고리즘 이용 풀이

        int answer = 0;

        int[][] floyd = new int[n + 1][n + 1];

        // -1 로 초기화
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                floyd[i][j] = -1;
            }
        }

        // 이긴선수는 1 , 진 선수는 0
        for (int i = 0; i < results.length; i++) {
            int win = results[i][0];
            int lose = results[i][1];

            floyd[win][lose] = 1;
            floyd[lose][win] = 0;
        }

        // k = 거쳐가는 선수
        for (int k = 1; k < n + 1; k++) {
            // i = 춣발하는 선수
            for (int i = 1; i < n + 1; i++) {
                // 본인은 패스
                if(k == i)
                    continue;
                for (int j = 1; j < n + 1; j++) {
                    if (i == j || k == j)
                        continue;

                    // i선수가 k선수에게 졌는데, k선수가 j선수에게 지면 -> i선수는 j선수에게 짐
                    if(floyd[i][k] == 0 && floyd[k][j] == 0)
                        floyd[i][j] = 0;
                    // i선수가 k선수에게 이겼는데, k선수가 j선수에게 이기면 -> i선수는 j선수에게 이김
                    else if(floyd[i][k] == 1  && floyd[k][j] == 1)
                        floyd[i][j] = 1;
                    
                }

            }
        }

        for (int i = 1; i < n + 1; i++) {
            boolean flag = true;
            for (int j = 1; j < n + 1; j++) {
                if (i == j)
                    continue;
                if (floyd[i][j] == -1) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] results = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
        System.out.println(solution(5, results));
    }
}
