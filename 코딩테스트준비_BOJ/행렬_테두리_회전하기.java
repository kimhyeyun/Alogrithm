public class 행렬_테두리_회전하기 {
    public static int[] solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows][columns];
        int[] answer = new int[queries.length];

        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = num++;
            }
        }

        for (int q = 0; q < queries.length; q++) {
            int x1 = queries[q][0] - 1;
            int y1 = queries[q][1] - 1;
            int x2 = queries[q][2] - 1;
            int y2 = queries[q][3] - 1;

            int tmp = arr[x1][y1];
            int min = tmp;

            //왼
            for (int i = x1; i < x2; i++) {
                arr[i][y1] = arr[i + 1][y1];
                min = arr[i][y1] < min ? arr[i][y1] : min;
            }

            //아래
            for (int j = y1; j < y2; j++) {
                arr[x2][j] = arr[x2][j + 1];
                min = arr[x2][j] < min ? arr[x2][j] : min;
            }

            // 오른
            for (int i = x2; i > x1; i--) {
                arr[i][y2] = arr[i - 1][y2];
                min = arr[i][y2] < min ? arr[i][y2] : min;
            }

            // 위
            for (int j = y2; j > y1; j--) {
                arr[x1][j] = arr[x1][j - 1];
                min = arr[x1][j] < min ? arr[x1][j] : min;
            }

            arr[x1][y1 + 1] = tmp;

            answer[q] = min;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        int[] answer = solution(6, 6, queries);

        for (int a : answer) {
            System.out.println(a);
        }
    }
}
