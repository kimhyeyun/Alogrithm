import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class code_tree_싸움땅 {
    static int n, m, k;
    static List<Integer>[][] map;
    static Player[] players;
    static int[][] isPlayer;

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        players = new Player[m];
        map = new List[n][n];
        isPlayer = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            Arrays.fill(isPlayer[i], -1);

            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<>();

                int x = Integer.parseInt(st.nextToken());
                if (x != 0) map[i][j].add(x);
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            players[i] = new Player(i, x, y, d, s);

            isPlayer[x][y] = i;
        }

        while (k-- > 0) {
            for (int i = 0; i < m; i++) {
                int[] next = findNext(players[i]);

                if (isPlayer[next[0]][next[1]] == -1) movePlayer(players[i], next);
                else fightPlayers(next, players[i]);

            }
        }

        for (int i = 0; i < m; i++) {
            System.out.print(players[i].point + " ");
        }
        System.out.println();
    }

    private static int[] findNext(Player player) {
        int nx = player.x + dx[player.d];
        int ny = player.y + dy[player.d];

        if (!isRange(nx, ny)) {
            player.d = player.d < 2 ? player.d + 2 : player.d - 2;

            nx = player.x + dx[player.d];
            ny = player.y + dy[player.d];
        }

        return new int[]{nx, ny};
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static void movePlayer(Player player, int[] pos) {
        map[pos[0]][pos[1]].add(player.gun);

        Collections.sort(map[pos[0]][pos[1]]);
        player.gun = map[pos[0]][pos[1]].get(map[pos[0]][pos[1]].size() - 1);
        map[pos[0]][pos[1]].remove(map[pos[0]][pos[1]].size() - 1);

        isPlayer[player.x][player.y] = -1;
        isPlayer[pos[0]][pos[1]] = player.index;

        player.x = pos[0];
        player.y = pos[1];
    }

    private static void fightPlayers(int[] pos, Player player) {
        int opponent = isPlayer[pos[0]][pos[1]];
        Player op = players[opponent];

        int opOfAbility = op.s + op.gun;
        int myAbility = player.s + player.gun;
        int point = Math.abs(myAbility - opOfAbility);

        if (opOfAbility < myAbility || (opOfAbility == myAbility && op.s < player.s)) {
            player.point += point;

            map[pos[0]][pos[1]].add(op.gun);
            op.gun = 0;
            isPlayer[player.x][player.y] = -1;

            losePlayer(pos, op);
            movePlayer(player, pos);

            isPlayer[op.x][op.y] = op.index;
        } else if (opOfAbility > myAbility || (opOfAbility == myAbility && player.s < op.s)) {
            op.point += point;

            map[pos[0]][pos[1]].add(player.gun);
            player.gun = 0;
            isPlayer[op.x][op.y] = -1;

            losePlayer(pos, player);
            movePlayer(op, pos);

            isPlayer[player.x][player.y] = player.index;
        }
    }

    private static void losePlayer(int[] pos, Player player) {

        for (int d = 0; d < 4; d++) {
            int nd = (player.d + d) % 4;

            int nx = pos[0] + dx[nd];
            int ny = pos[1] + dy[nd];

            if (nx < 0 || ny < 0 || n <= nx || n <= ny || (isPlayer[nx][ny] != -1 && isPlayer[nx][ny] != player.index)) continue;

            player.d = nd;
            movePlayer(player, new int[]{nx, ny});
            break;
        }
    }

    private static class Player {
        int index;
        int x, y, d, s;
        int point, gun;

        public Player(int index, int x, int y, int d, int s) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
            this.point = 0;
            this.gun = 0;
        }
    }
}
