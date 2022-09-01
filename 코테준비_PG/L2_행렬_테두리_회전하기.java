import java.util.ArrayList;
import java.util.List;

public class L2_행렬_테두리_회전하기 {
    public static int[] solution(int rows, int columns, int[][] queries) {
        List<Integer> answer = new ArrayList<>();
        int[][] rectangle = new int[rows][columns];

        int number = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                rectangle[i][j] = number++;
            }
        }

        for (int[] query : queries) {
            int x1 = query[0] - 1;
            int y1 = query[1] - 1;
            int x2 = query[2] - 1;
            int y2 = query[3] - 1;

            int min = rotate(x1, y1, x2, y2, rectangle);

/*            for (int[] re : rectangle) {
                for (int r : re) {
                    System.out.print(r + " ");
                }
                System.out.println();
            }
            System.out.println();*/

            answer.add(min);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int rotate(int x1, int y1, int x2, int y2, int[][] rectangle) {
        int min = rectangle[x1][y1];
        int tmp = rectangle[x1][y1];

        for (int i = x1 + 1; i <= x2; i++) {
            rectangle[i - 1][y1] = rectangle[i][y1];
            min = Math.min(min, rectangle[i][y1]);
        }
        for (int j = y1 + 1; j <= y2; j++) {
            rectangle[x2][j - 1] = rectangle[x2][j];
            min = Math.min(min, rectangle[x2][j]);
        }
        for (int i = x2 - 1; i >= x1; i--){
            rectangle[i + 1][y2] = rectangle[i][y2];
            min = Math.min(min, rectangle[i][y2]);
        }
        for (int j = y2 - 1; j >= y1; j--) {
            rectangle[x1][j + 1] = rectangle[x1][j];
            min = Math.min(min, rectangle[x1][j]);
        }

        rectangle[x1][y1 + 1] = tmp;

        return min;
    }

    public static void main(String[] args) {
        int[][] queries = {{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}};
        int[] answer = solution(3, 3, queries);

        for (int a : answer) {
            System.out.println(a);
        }
    }
}
