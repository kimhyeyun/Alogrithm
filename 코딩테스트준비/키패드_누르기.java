import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 키패드_누르기 {
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static String solution(int[] numbers, String hand) {
        String answer = "";

        int[] fingers = new int[2];
        fingers[0] = 10;
        fingers[1] = 11;

        for (int number : numbers) {
            if (number == 1 || number == 4 || number == 7) {
                fingers[0] = number;
                answer += "L";
            } else if (number == 3 || number == 6 || number == 9) {
                fingers[1] = number;
                answer += "R";
            } else {
                int left = BFS(fingers[0], number);
                int right = BFS(fingers[1], number);

                if (left < right) {
                    fingers[0] = number;
                    answer += "L";
                } else if (right < left) {
                    fingers[1] = number;
                    answer += "R";
                } else {
                    if (hand.equals("right")) {
                        fingers[1] = number;
                        answer += "R";
                    } else {
                        fingers[0] = number;
                        answer += "L";
                    }
                }
            }
        }

        return answer;
    }

    private static int BFS(int start, int end) {
        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[4][3];
        for (int i = 0; i < 4; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        int[] s = searchPair(start);
        int[] e = searchPair(end);

        q.add(s);
        dist[s[0]][s[1]] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || 3 < nx || 2 < ny) continue;
                if(dist[nx][ny] <= dist[now[0]][now[1]] + 1) continue;

                q.add(new int[]{nx, ny});
                dist[nx][ny] = dist[now[0]][now[1]] + 1;
            }
        }
        return dist[e[0]][e[1]];
    }

    private static int[] searchPair(int n) {
        switch (n) {
            case 0:
                return new int[]{3, 1};
            case 1:
                return new int[]{0, 0};
            case 2:
                return new int[]{0, 1};
            case 3:
                return new int[]{0, 2};
            case 4:
                return new int[]{1, 0};
            case 5:
                return new int[]{1, 1};
            case 6:
                return new int[]{1, 2};
            case 7:
                return new int[]{2, 0};
            case 8:
                return new int[]{2, 1};
            case 9:
                return new int[]{2, 2};
            case 10:
                return new int[]{3, 0};
            case 11:
                return new int[]{3, 2};
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        System.out.println(solution(numbers, "left"));
    }
}
