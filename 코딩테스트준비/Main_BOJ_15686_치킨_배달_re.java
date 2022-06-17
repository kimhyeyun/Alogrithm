import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_15686_치킨_배달_re {
    static int N, M, ans = Integer.MAX_VALUE;
    static List<int[]> house, chickens;
    static Stack<int[]> selChickens;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        house = new ArrayList<>();
        chickens = new ArrayList<>();
        selChickens = new Stack<>();

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int a = Integer.parseInt(stringTokenizer.nextToken());
                if(a == 1) house.add(new int[]{i, j});
                else if(a == 2) chickens.add(new int[]{i, j});
            }
        }

        select(0, 0);

        System.out.println(ans);
    }

    private static void select(int cnt, int start) {
        if (cnt == M) {
            checkDist();
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            selChickens.push(chickens.get(i));
            select(cnt + 1, i + 1);
            selChickens.pop();
        }
    }

    private static void checkDist() {
        int sum = 0;

        for (int[] home : house) {
            int tmp = Integer.MAX_VALUE;
            for (int[] chicken : selChickens) {
                tmp = Math.min(tmp, calDist(home, chicken));
            }
            sum += tmp;

            if(sum > ans) return;
        }
        ans = sum < ans ? sum : ans;
    }

    private static int calDist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
