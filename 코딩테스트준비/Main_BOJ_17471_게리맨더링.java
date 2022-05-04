import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_17471_게리맨더링 {
    static int N, min = Integer.MAX_VALUE;
    static int[] population, area;
    static List<Integer>[] list;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());

        population = new int[N + 1];
        list = new ArrayList[N + 1];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<>();
            population[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 1; i < N + 1; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = 0; j < num; j++) {
                list[i].add(Integer.parseInt(stringTokenizer.nextToken()));
            }
        }

        area = new int[N + 1];
        DFS(1);

        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    private static void DFS(int k) {
        if (k == N + 1) {
            int area1 = 0, area2 = 0;

            for (int i = 1; i < N + 1; i++) {
                if(area[i] == 1) area1 += population[i];
                else area2 += population[i];
            }

            isVisited = new boolean[N + 1];
            int link = 0;
            for (int i = 1; i < N + 1; i++) {
                if (!isVisited[i]) {
                    BFS(i, area[i]);
                    link += 1;
                }
            }

            if (link == 2) min = Math.min(min, Math.abs(area1 - area2));
            return;
        }

        area[k] = 1;
        DFS(k + 1);

        area[k] = 2;
        DFS(k + 1);
    }

    private static void BFS(int idx, int areaNum) {
        Queue<Integer> q = new LinkedList<>();
        isVisited[idx] = true;
        q.offer(idx);

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : list[now]) {
                if (area[next] == areaNum && !isVisited[next]) {
                    q.offer(next);
                    isVisited[next] = true;
                }
            }

        }
    }
}
