import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_14658_하늘에서_별똥별이_빗발친다 {
    static List<int[]> stars;
    static int N, M, L, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stars = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars.add(new int[]{x, y});
        }

        int res = Integer.MIN_VALUE;
        for (int[] star1 : stars) {
            for (int[] star2 : stars) {
                res = Math.max(res, countBoundStars(star1[0], star2[1]));
            }
        }
        System.out.println(K - res);
    }

    private static int countBoundStars(int x, int y) {
        int res = 0;
        for (int[] star : stars) {
            if (x <= star[0] && star[0] <= x + L && y <= star[1] && star[1] <= y + L) {
                res += 1;
            }
        }
        return res;
    }
}
