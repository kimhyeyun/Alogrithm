import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_14658_하늘에서_별똥별이_빗발친다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<int[]> stars = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars.add(new int[]{x, y});
        }

        int answer = Integer.MIN_VALUE;
        for (int[] star1 : stars) {
            for (int[] star2 : stars) {
                answer = Math.max(answer, countBoundOfStar(star1[0], star2[1], L, stars));
            }
        }

        System.out.println(K - answer);
    }

    private static int countBoundOfStar(int x, int y, int L, List<int[]> stars) {
        int count = 0;
        for (int[] star : stars) {
            if(x <= star[0] && star[0] <= x+L && y <= star[1] && star[1] <= y + L) count += 1;
        }
        return count;
    }
}
