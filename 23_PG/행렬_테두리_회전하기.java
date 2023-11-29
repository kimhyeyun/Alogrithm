import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 행렬_테두리_회전하기 {

    public int[] solution(int rows, int columns, int[][] queries) {

        List<Integer> answer = new ArrayList<>();
        int[][] rectacgle = init(rows, columns);

        for (int[] query : queries) {
            int x1 = query[0] - 1;
            int y1 = query[1] - 1;
            int x2 = query[2] - 1;
            int y2 = query[3] - 1;

            answer.add(rotateRectagle(rectacgle, x1, y1, x2, y2));
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private int rotateRectagle(int[][] rectacgle, int x1, int y1, int x2, int y2) {
        int tmp = rectacgle[x1][y1];
        int min = tmp;

        // 왼쪽
        for (int i = x1; i < x2; i++) {
            rectacgle[i][y1] = rectacgle[i + 1][y1];
            min = Math.min(min, rectacgle[i][y1]);
        }

        // 아래
        for (int j = y1; j < y2; j++) {
            rectacgle[x2][j] = rectacgle[x2][j + 1];
            min = Math.min(min, rectacgle[x2][j]);
        }

        // 오른쪽
        for (int i = x2; i > x1; i--) {
            rectacgle[i][y2] = rectacgle[i - 1][y2];
            min = Math.min(min, rectacgle[i][y2]);
        }

        // 위
        for (int j = y2; j > y1; j--) {
            rectacgle[x1][j] = rectacgle[x1][j - 1];
            min = Math.min(min, rectacgle[x1][j]);
        }

        rectacgle[x1][y1 + 1] = tmp;

        return min;
    }

    private int[][] init(int row, int columns) {
        int[][] tmp = new int[row][columns];

        int num = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {
                tmp[i][j] = num++;
            }
        }
        return tmp;
    }

}
