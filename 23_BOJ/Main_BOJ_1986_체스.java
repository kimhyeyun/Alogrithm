import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1986_체스 {
    static int n, m;
    static int Q_count, K_count, P_count;
    static List<int[]> QList, KList;
    static int[][] board;
    static boolean[][] isNotSafed;
    static final int Q = 1, K = 2, P = 3;
    static final int[] Qdx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] Qdy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static final int[] Kdx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static final int[] Kdy = {-1, 1, -2, 2, -2, 2, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        isNotSafed = new boolean[n][m];

        QList = new LinkedList<>();
        KList = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        Q_count = Integer.parseInt(st.nextToken());
        for (int i = 0; i < Q_count; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            board[x][y] = Q;
            isNotSafed[x][y] = true;

            QList.add(new int[]{x, y});
        }

        st = new StringTokenizer(br.readLine());
        K_count = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K_count; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            board[x][y] = K;
            isNotSafed[x][y] = true;

            KList.add(new int[]{x, y});

        }

        st = new StringTokenizer(br.readLine());
        P_count = Integer.parseInt(st.nextToken());
        for (int i = 0; i < P_count; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            board[x][y] = P;
            isNotSafed[x][y] = true;
        }

        for (int[] queen : QList) {
            for (int d = 0; d < 8; d++) {
                int nx = queen[0];
                int ny = queen[1];

                while (true) {
                    nx += Qdx[d];
                    ny += Qdy[d];

                    if(nx < 0 || ny < 0 || n <= nx || m <= ny) break;
                    if(board[nx][ny] != 0) break;

                    isNotSafed[nx][ny] = true;
                }
            }
        }

        for (int[] knight : KList) {
            for (int d = 0; d < 8; d++) {
                int nx = knight[0] + Kdx[d];
                int ny = knight[1] + Kdy[d];

                if(nx < 0 || ny < 0 || n <= nx || m <= ny) continue;
                if(board[nx][ny] != 0) continue;

                isNotSafed[nx][ny] = true;
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!isNotSafed[i][j]) answer += 1;
            }
        }

        System.out.println(answer);
    }
}
