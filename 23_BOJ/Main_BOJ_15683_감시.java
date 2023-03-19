import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_15683_감시 {
    static int N, M, answer;
    static int[][] officeRoom;
    static List<int[]> cctvs;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = Integer.MAX_VALUE;

        officeRoom = new int[N][M];
        cctvs = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                officeRoom[i][j] = Integer.parseInt(st.nextToken());
                if(1 <= officeRoom[i][j] && officeRoom[i][j] <= 5) cctvs.add(new int[]{i, j, officeRoom[i][j]});
            }
        }

        setCCTV(0, 0, officeRoom);

        System.out.println(answer);
    }

    private static void setCCTV(int count, int index, int[][] prevRoom) {
        if (count == cctvs.size()) {
            answer = Math.min(answer, calMinArea(prevRoom));
            return;
        }

        int[][] tmp = new int[N][M];
        for (int i = index; i < cctvs.size(); i++) {
            int[] now = cctvs.get(i);

            if (now[2] == 1) {
                for (int d = 0; d < 4; d++) {
                    for (int k = 0; k < N; k++) {
                        tmp[k] = prevRoom[k].clone();
                    }

                    checkCctvDir(now[0], now[1], d, tmp);
                    setCCTV(count + 1, i + 1, tmp);
                }
            } else if (now[2] == 2) {
                for (int d = 0; d < 2; d++) {
                    for (int k = 0; k < N; k++) {
                        tmp[k] = prevRoom[k].clone();
                    }

                    checkCctvDir(now[0], now[1], d, tmp);
                    checkCctvDir(now[0], now[1], d + 2, tmp);
                    setCCTV(count + 1, i + 1, tmp);
                }
            } else if (now[2] == 3) {
                for (int d = 0; d < 4; d++) {
                    for (int k = 0; k < N; k++) {
                        tmp[k] = prevRoom[k].clone();
                    }

                    int nd = d + 1 == 4 ? 0 : d + 1;

                    checkCctvDir(now[0], now[1], d, tmp);
                    checkCctvDir(now[0], now[1], nd, tmp);
                    setCCTV(count + 1, i + 1, tmp);
                }
            } else if (now[2] == 4) {
                for (int d = 0; d < 4; d++) {
                    for (int k = 0; k < N; k++) {
                        tmp[k] = prevRoom[k].clone();
                    }

                    int nd = d + 1 == 4 ? 0 : d + 1;
                    int nnd = nd + 1 == 4 ? 0 : nd + 1;

                    checkCctvDir(now[0], now[1], d, tmp);
                    checkCctvDir(now[0], now[1], nd, tmp);
                    checkCctvDir(now[0], now[1], nnd, tmp);
                    setCCTV(count + 1, i + 1, tmp);
                }
            } else {
                for (int k = 0; k < N; k++) {
                    tmp[k] = prevRoom[k].clone();
                }

                checkCctvDir(now[0], now[1], 0, tmp);
                checkCctvDir(now[0], now[1], 1, tmp);
                checkCctvDir(now[0], now[1], 2, tmp);
                checkCctvDir(now[0], now[1], 3, tmp);
                setCCTV(count + 1, i + 1, tmp);
            }
        }
    }

    private static int calMinArea(int[][] room) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(room[i][j] == 0) count += 1;
            }
        }
        return count;
    }

    private static void checkCctvDir(int x, int y, int dir, int[][] room) {
        room[x][y] = -1;

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny || room[nx][ny] == 6) return;

            room[nx][ny] = -1;

            x = nx;
            y = ny;
        }
    }
}
