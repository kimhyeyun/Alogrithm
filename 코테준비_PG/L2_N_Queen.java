import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L2_N_Queen {
    static boolean[] isVisited;
    static int[][] map;
    static int answer;
    static LinkedList<int[]> list;
    public static int solution(int n) {
        answer = 0;
        map = new int[n][n];
        isVisited = new boolean[n];

        for (int i = 0; i < n; i++) {
            list = new LinkedList<>();
            list.add(new int[]{0, i});
            map[0][i] = 1;
            isVisited[i] = true;
            DFS(1, n);
            isVisited[i] = false;
            map[0][i] = 0;
        }
        return answer;
    }

    private static void DFS(int cnt, int n) {
        if (cnt == n) {
            answer += 1;
            return;
        }

        for (int i = 0; i < n; i++) {
            if(isVisited[i]) continue;

            map[cnt][i] = 1;
            list.add(new int[]{cnt, i});

            if (isCheck()) {
                isVisited[i] = true;
                DFS(cnt + 1, n);
                isVisited[i] = false;
            }

            list.removeLast();
            map[cnt][i] = 0;
        }
    }

    private static boolean isCheck() {
        for (int i = 0; i < list.size(); i++) {
            int[] now = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                int[] next = list.get(j);
                if (Math.abs(next[0] - now[0]) == Math.abs(next[1] - now[1])) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(4));
    }
}
