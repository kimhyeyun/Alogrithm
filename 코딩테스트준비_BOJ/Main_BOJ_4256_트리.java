import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_4256_트리 {
    static int N;
    static int[] preOrder, inOrder;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            preOrder = new int[N];
            inOrder = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                preOrder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                inOrder[i] = Integer.parseInt(st.nextToken());
            }

            findPostOrder(0, 0, N - 1);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void findPostOrder(int rootIdx, int start, int end) {
        if(rootIdx >= N) return;

        int rootValue = preOrder[rootIdx];
        for (int idx = start; idx <= end; idx++) {
            if (rootValue == inOrder[idx]) {
                findPostOrder(rootIdx + 1, start, idx);
                findPostOrder(rootIdx + idx + 1 - start, idx + 1, end);
                sb.append(rootValue + " ");
                return;
            }
        }
    }

}
