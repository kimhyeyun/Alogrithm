import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_16235_나무_재테크_re {
    public static class Ground{
        int nutrients;
        PriorityQueue<Integer> trees;

        public Ground() {
            this.nutrients = 5;
            this.trees = new PriorityQueue<>();
        }
    }

    static int N, M, K, ans;
    static Ground[][] ground;
    static int[][] additionalNutrients;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        additionalNutrients = new int[N][N];
        ground = new Ground[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ground[i][j] = new Ground();
                additionalNutrients[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int age = Integer.parseInt(stringTokenizer.nextToken());

            ground[x][y].trees.add(age);
        }

        while (K-- > 0) {
            ans = 0;
            int[][] breeding = SpringAndSummer();
            fall(breeding);
            winter();
            countAliveTrees();

            if(ans == 0) break;
        }
        System.out.println(ans);
    }

    private static void countAliveTrees() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += ground[i][j].trees.size();
            }
        }
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j].nutrients += additionalNutrients[i][j];
            }
        }
    }

    private static void fall(int[][] breeding) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(breeding[i][j] == 0) continue;

                for (int d = 0; d < 8; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;

                    int babyTree = breeding[i][j];
                    while (babyTree-- > 0) {
                        ground[nx][ny].trees.add(1);
                    }
                }
            }
        }
    }

    private static int[][] SpringAndSummer() {
        int[][] breeding = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                PriorityQueue<Integer> surviveTrees = new PriorityQueue<>();
                Ground g = ground[i][j];

                int breedingCnt = 0;

                while (!g.trees.isEmpty() && g.nutrients - g.trees.peek() >= 0) {
                    int age = g.trees.poll();
                    g.nutrients -= age;
                    surviveTrees.add(age + 1);

                    if((age+1) % 5 == 0) breedingCnt += 1;
                }

                int diedTree = 0;
                while(!g.trees.isEmpty()) diedTree += (int) Math.floor(g.trees.poll() / 2);

                g.trees = surviveTrees;
                g.nutrients += diedTree;
                breeding[i][j] = breedingCnt;
            }
        }
        return breeding;
    }


}
