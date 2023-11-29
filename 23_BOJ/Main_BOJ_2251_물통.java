import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2251_물통 {
    static int[] waters;
    static boolean[][] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        waters = new int[3];
        for (int i = 0; i < 3; i++) {
            waters[i] = Integer.parseInt(st.nextToken());
        }

        isVisited = new boolean[waters[0] + 1][waters[1] + 1];

        DFS(0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = waters[1]; i >= 0; i--) {
            if(isVisited[0][i]) sb.append(waters[2] - i).append(" ");
        }

        System.out.println(sb);

    }

    private static void DFS(int A, int B) {
        int C = waters[2] - A - B;

        int spaceA = waters[0] - A;
        int spaceB = waters[1] - B;
        int spaceC = waters[2] - C;

        if(isVisited[A][B]) return;
        isVisited[A][B] = true;

        if (A > 0) {
            int waterOfB = Math.min(spaceB, A);
            DFS(A - waterOfB, B + waterOfB);
            int waterOfC = Math.min(spaceC, A);
            DFS(A - waterOfC, B);
        }
        if (B > 0) {
            int waterOfA = Math.min(spaceA, B);
            DFS(A + waterOfA, B - waterOfA);
            int waterOfC = Math.min(spaceC, B);
            DFS(A, B - waterOfC);
        }
        if (C > 0) {
            int waterOfA = Math.min(spaceA, C);
            DFS(A + waterOfA, B);
            int waterOfB = Math.min(spaceB, C);
            DFS(A, B + waterOfB);
        }
    }
}
