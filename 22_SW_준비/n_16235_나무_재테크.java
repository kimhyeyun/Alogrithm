import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n_16235_나무_재테크 {
    static class ground {
        int nutrient;
        PriorityQueue<Integer> trees;

        public ground() {
            this.nutrient = 5;
            this.trees = new PriorityQueue<>();
        }
    }
    static int N, M, K;
    static int[][] additionalOfNutrients;
    static ground[][] grounds;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grounds = new ground[N][N];
        additionalOfNutrients = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                additionalOfNutrients[i][j] = Integer.parseInt(st.nextToken());
                grounds[i][j] = new ground();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            grounds[x][y].trees.add(z);
        }

        while (K-- > 0) {
            int[][] breeding = springAndSummer();
            fall(breeding);
            winter();
        }

        System.out.println(countAliveTree());
    }

    private static int countAliveTree() {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += grounds[i][j].trees.size();
            }
        }
        return answer;
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grounds[i][j].nutrient += additionalOfNutrients[i][j];
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

                    int newTree = breeding[i][j];
                    while(newTree-- > 0) grounds[nx][ny].trees.add(1);
                }
            }
        }
    }

    private static int[][] springAndSummer() {
        int[][] breeding = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                PriorityQueue<Integer> surviveTrees = new PriorityQueue<>();
                ground g = grounds[i][j];

                int breedingCount = 0;

                while (!g.trees.isEmpty() && g.nutrient - g.trees.peek() >= 0) {
                    int age = g.trees.poll();
                    g.nutrient -= age;
                    surviveTrees.add(age + 1);

                    if ((age + 1) % 5 == 0) breedingCount += 1;
                }
                int died = 0;
                while (!g.trees.isEmpty()) {
                    died += (int) Math.floor(g.trees.poll() / 2);
                }

                g.trees = surviveTrees;
                g.nutrient += died;

                breeding[i][j] = breedingCount;
             }
        }
        return breeding;
    }
}
