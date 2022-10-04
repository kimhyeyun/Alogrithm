import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class n_15686_치킨_배달 {
    static int N, M, answer;
    static Stack<int[]> pickedChicken;
    static List<int[]> chickens, home;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = Integer.MAX_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chickens = new ArrayList<>();
        home = new ArrayList<>();
        pickedChicken = new Stack<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) home.add(new int[]{i, j});
                else if (x == 2) chickens.add(new int[]{i, j});
            }
        }
        solution(0, 0);

        System.out.println(answer);
    }

    private static void solution(int cnt, int idx) {
        if (cnt == M) {
            calculateDist();
            return;
        }

        for (int i = idx; i < chickens.size(); i++) {
            pickedChicken.add(chickens.get(i));
            solution(cnt + 1, i + 1);
            pickedChicken.pop();
        }
    }

    private static void calculateDist() {
        int sum = 0;
        for (int i = 0; i < home.size(); i++) {
            int tmp = Integer.MAX_VALUE;
            for (int j = 0; j < pickedChicken.size(); j++) {
                int dist = Math.abs(pickedChicken.get(j)[0] - home.get(i)[0]) + Math.abs(pickedChicken.get(j)[1] - home.get(i)[1]);
                tmp = Math.min(tmp, dist);
            }
            sum += tmp;
        }
        answer = Math.min(answer, sum);
    }

}
