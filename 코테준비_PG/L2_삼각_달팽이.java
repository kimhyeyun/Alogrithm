public class L2_삼각_달팽이 {
    public int[] solution(int n) {
        int[] answer = new int[n * (n + 1) / 2];
        int[][] arr = new int[n][n];

        int num = 1;
        int x = -1, y = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; i < n; j++) {
                if(i % 3 == 0) x += 1;
                else if (i % 3 == 1) y += 1;
                else {
                    x -= 1;
                    y -= 1;
                }

                arr[x][y] = num++;
            }
        }


        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == 0) break;
                answer[idx++] = arr[i][j];
            }
        }
        return answer;
    }

}
