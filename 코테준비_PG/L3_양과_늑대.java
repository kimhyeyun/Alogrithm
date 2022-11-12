import java.util.ArrayList;
import java.util.List;

public class L3_양과_늑대 {
    static List<Integer>[] adj;
    static int N, answer;
    public static int solution(int[] info, int[][] edges) {
        answer = Integer.MIN_VALUE;

        N = info.length;
        adj = new List[N + 1];

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);
        DFS(0, 0, 0, info, list);

        return answer;
    }

    private static void DFS(int idx, int lambCount, int wolfCount, int[] info, List<Integer> children) {
        if (info[idx] == 0) {
            lambCount += 1;
        } else {
            wolfCount += 1;
        }

        if (wolfCount >= lambCount) {
            return;
        }
        answer = Math.max(answer, lambCount);

        List<Integer> tmp = new ArrayList<>();
        tmp.addAll(children);
        tmp.addAll(adj[idx]);
        tmp.remove(Integer.valueOf(idx));

        for (int nextChild : tmp) {
            DFS(nextChild, lambCount, wolfCount, info, tmp);
        }

        return;
    }

    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};

        System.out.println(solution(info, edges));
    }
}
