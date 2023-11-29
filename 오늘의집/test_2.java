import java.util.*;

public class test_2 {
    static class node {
        int x, y;
        List<Integer> list;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
            this.list = new ArrayList<>();
        }

        public node(int x, int y, List<Integer> list) {
            this.x = x;
            this.y = y;
            this.list = list;
        }
    }

    static int initx = 0, inity = 0;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static String[] strings = {"U", "D", "L", "R", "!"};
    public static String solution(String target){
        map = new char[][]{
                {'a', 'b', 'c', 'd', 'e'},
                {'f', 'g', 'h', 'i', 'j'},
                {'k', 'l', 'm', 'n', 'o'},
                {'p', 'q', 'r', 's', 't'},
                {'u', 'v', 'w', 'x', 'y'},
                {'z', ' ', ' ', ' ', ' '}
        };

        String answer = "";
        for (char c : target.toCharArray()) {
            List<Integer> ans = findTarget(initx, inity, c);
            for (int a : ans) {
                answer += strings[a];
            }
        }

        return answer;

    }

    private static List<Integer> findTarget(int x, int y, char target) {
        Queue<node> q = new LinkedList<>();
        int[][] dist = new int[6][5];
        for (int i = 0; i < 6; i++) {
            Arrays.fill(dist[i], -1);
        }

        q.add(new node(x, y));
        dist[x][y] = 0;
        while (!q.isEmpty()) {
            node now = q.poll();

            List<Integer> list = new ArrayList<>();
            for (int l : now.list) list.add(l);
            if (map[now.x][now.y] == target) {
                list.add(4);
                return list;
            }

            for (int d = 0; d < 4; d++) {
                list = new ArrayList<>();
                for (int l : now.list) list.add(l);

                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx < 0 || ny < 0 || 6 <= nx || 5 <= ny) continue;
                if(map[nx][ny] == ' ') continue;
                if (map[nx][ny] == target){
                    list.add(d);
                    list.add(4);

                    initx = nx;
                    inity = ny;
                    return list;
                }

                if (dist[nx][ny] == -1 || dist[nx][ny] > dist[now.x][now.y] + 1) {
                    dist[nx][ny] = dist[now.x][now.y];
                    list.add(d);

                    q.add(new node(nx, ny, list));
                }
            }
        }

        return new ArrayList<>();
    }


    public static void main(String[] args) {
        System.out.println(solution("code"));
    }
}
