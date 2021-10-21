import java.util.ArrayList;

public class Main_P_9주차_전력망을둘로나누기 {

    static int N, towerNum = 0;
    static int[] towerCnt = new int[2];
    static boolean[] isVisited;
    static ArrayList<Integer> wiresList[];

    public static int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        N = n;

        int idx = 0;
        while (true) {

            wiresList = new ArrayList[N + 1];
            for (int i = 0; i < N + 1; i++) {
                wiresList[i] = new ArrayList<>();
            }
            isVisited = new boolean[N + 1];
            
            for (int i = 0; i < wires.length; i++) {
                if (idx == i)
                    continue;

                wiresList[wires[i][0]].add(wires[i][1]);
                wiresList[wires[i][1]].add(wires[i][0]);
            }

            int cntIdx = 0;
            for (int j = 1; j <= N; j++) {
                towerNum = 1;
                if (!isVisited[j]) {
                    isVisited[j] = true;
                    DFS(j);
                    towerCnt[cntIdx++] = towerNum;
                }
            }

            answer = Math.min(answer, Math.abs(towerCnt[0] - towerCnt[1]));
            idx++;

            if (idx > N)
                break;
        }

        return answer;
    }

    private static void DFS(int n) {
        for (int t : wiresList[n]) {
            if (!isVisited[t]) {
                isVisited[t] = true;
                towerNum++;
                DFS(t);
            }
        }

        return;
    }

    public static void main(String[] args) {
        int[][] wires = { { 1, 3 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 7, 8 }, { 7, 9 } };
        System.out.println(solution(9, wires));
    }
}
