public class L3_파괴되지_않은_건물 {
    static int[][] sum;
    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int n = board.length;
        int m = board[0].length;
        sum = new int[n + 1][m + 1];

        for (int[] s : skill) {
            preFixSum(s);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                sum[i][j + 1] += sum[i][j];
            }
        }

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n - 1; i++) {
                sum[i + 1][j] += sum[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(board[i][j] + sum[i][j] > 0) answer += 1;
            }
        }


        return answer;
    }

    private static void preFixSum(int[] skill) {
        int tmp = skill[5];
        if(skill[0] == 1) tmp *= -1;

        sum[skill[1]][skill[2]] += tmp;
        sum[skill[1]][skill[4] + 1] += (tmp * -1);
        sum[skill[3] + 1][skill[2]] += (tmp * -1);
        sum[skill[3] + 1][skill[4] + 1] += tmp;
    }


    public static void main(String[] args) {
        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        solution(board, skill);
    }
}
