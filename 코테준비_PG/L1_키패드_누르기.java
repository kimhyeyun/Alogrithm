import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class L1_키패드_누르기 {
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static String solution(int[] numbers, String hand) {
        String ans = "";
        int[] fingers = new int[]{10, 11};

        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                fingers[0] = num;
                ans += "L";
            } else if (num == 3 || num == 6 || num == 9) {
                fingers[1] = num;
                ans += "R";
            } else {
                int left = BFS(fingers[0], num);
                int right = BFS(fingers[1], num);

                if(left < right){
                    fingers[0] = num;
                    ans += "L";
                } else if (right < left) {
                    fingers[1] = num;
                    ans += "R";
                } else {
                    if (hand.equals("right")) {
                        fingers[1] = num;
                        ans += "R";
                    } else {
                        fingers[0] = num;
                        ans += "L";
                    }
                }
            }
        }
        return ans;
    }

    private static int BFS(int finger, int num) {
        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[4][3];

        for (int i = 0; i < 4; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        int[] start = searchXY(finger);
        int[] end = searchXY(num);

        q.add(start);
        dist[start[0]][start[1]] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || 4 <= nx || 3 <= ny) continue;
                if(dist[nx][ny] <= dist[now[0]][now[1]] + 1) continue;

                dist[nx][ny] = dist[now[0]][now[1]] + 1;
                q.add(new int[]{nx, ny});
            }
        }

        return dist[end[0]][end[1]];
    }

    private static int[] searchXY(int num) {
        switch (num) {
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
