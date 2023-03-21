import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_16235_나무_재테크 {
    static class Ground {
        int nutrient;
        PriorityQueue<Integer> trees;

        public Ground() {
            nutrient = 5;
            trees = new PriorityQueue<>();
        }
    }
    static int N, M, K;
    static int[][] additionalNutrients;
    static Ground[][] ground;
    static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ground = new Ground[N][N];
        additionalNutrients = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                additionalNutrients[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = new Ground();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            ground[x][y].trees.add(z);
        }

        while (K-- > 0) {
            int[][] breeding = SpringAndSummer();
            Autumn(breeding);
            Winter();
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += ground[i][j].trees.size();
            }
        }

        System.out.println(answer);
    }

    private static void Winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j].nutrient += additionalNutrients[i][j];
            }
        }
    }

    private static void Autumn(int[][] breeding) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(breeding[i][j] == 0) continue;

                for (int d = 0; d < 8; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;

                    int newTreeCount = breeding[i][j];
                    while(newTreeCount-- > 0) ground[nx][ny].trees.add(1);
                }
            }
        }
    }

    private static int[][] SpringAndSummer() {
        int[][] breeding = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                PriorityQueue<Integer> surviveTrees = new PriorityQueue<>();
                Ground now = ground[i][j];

                int breedingCount = 0;

                while (!now.trees.isEmpty() && now.nutrient >= now.trees.peek()) {
                    int age = now.trees.poll();
                    now.nutrient -= age;
                    surviveTrees.add(age + 1);

                    if((age +  1) % 5 == 0) breedingCount += 1;
                }

                int died = 0;
                while (!now.trees.isEmpty()) {
                    died += Math.floor(now.trees.poll() / 2);
                }

                now.trees = surviveTrees;
                now.nutrient += died;

                breeding[i][j] = breedingCount;
            }
        }
        return breeding;
    }
}
