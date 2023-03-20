import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_15686_치킨_배달 {
    static int N, M, answer;
    static List<int[]> chickens, home;
    static Stack<int[]> pickedChickens;
    static final int EMPTY = 0, HOUSE = 1, CHICKEN = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chickens = new LinkedList<>();
        home = new LinkedList<>();
        pickedChickens = new Stack<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if(x == HOUSE) home.add(new int[]{i, j});
                else if(x == CHICKEN) chickens.add(new int[]{i, j});
            }
        }

        answer = Integer.MAX_VALUE;

        select(0, 0);

        System.out.println(answer);
    }

    private static void select(int count, int index) {
        if (count == M) {
            calDist();
            return;
        }

        for (int i = index; i < chickens.size(); i++) {
            pickedChickens.add(chickens.get(i));
            select(count + 1, i + 1);
            pickedChickens.pop();
        }
    }

    private static void calDist() {
        int sum = 0;

        for (int i = 0; i < home.size(); i++) {
            int[] h = home.get(i);
            int tmp = Integer.MAX_VALUE;
            for (int j = 0; j < pickedChickens.size(); j++) {
                int[] c = pickedChickens.get(j);
                int dist = Math.abs(c[0] - h[0]) + Math.abs(c[1] - h[1]);

                tmp = Math.min(tmp, dist);
            }
            sum += tmp;
        }

        answer = Math.min(answer, sum);
    }
}
