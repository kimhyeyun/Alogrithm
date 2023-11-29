import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_20188_등산_마니아 {
    static int N;
    static List<Integer>[] tree;
    static int[] subTree;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tree = new List[N + 1];
        subTree = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            tree[i] = new LinkedList<>();
            subTree[i] = 1;
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            tree[start].add(end);
            tree[end].add(start);
        }

        isVisited = new boolean[N + 1];
        isVisited[1] = true;

        DFS(1);

        long variety = 0;
        for (int i = 2; i <= N; i++) {
            int restNodeCount = N - subTree[i];
            variety += nC2(N) - nC2(restNodeCount);
        }
        System.out.println(variety);
    }

    private static long nC2(int n) {
        return (long) n * (n - 1) / 2;
    }

    private static int DFS(int cur) {
        for (int next : tree[cur]) {
            if(isVisited[next]) continue;

            isVisited[next] = true;
            subTree[cur] += DFS(next);
        }

        return subTree[cur];
    }
}
