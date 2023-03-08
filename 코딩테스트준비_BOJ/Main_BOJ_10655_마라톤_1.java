import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main_BOJ_10655_마라톤_1 {
    static int N;
    static List<int[]> points;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        points = new ArrayList<>();
        dist = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        int sumDist = 0;
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Math.abs(points.get(i)[0] - points.get(i + 1)[0]) + Math.abs(points.get(i)[1] - points.get(i + 1)[1]);
            sumDist += dist[i];
        }

        int maxSkipDist = Integer.MIN_VALUE;
        for (int i = 0; i < N - 2; i++) {
            int skip = Math.abs(points.get(i)[0] - points.get(i + 2)[0]) + Math.abs(points.get(i)[1] - points.get(i + 2)[1]);
            maxSkipDist = Math.max(maxSkipDist, dist[i] + dist[i + 1] - skip);
        }
        System.out.println(sumDist - maxSkipDist);
    }
}
