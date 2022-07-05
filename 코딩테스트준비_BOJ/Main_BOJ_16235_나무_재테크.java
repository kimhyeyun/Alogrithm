import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_16235_나무_재테크 {
    static int N, M, K;
    static int[][] additionalValueOfNutrients;
    static Ground[][] grounds;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Ground {
        int nutrient;
        PriorityQueue<Integer> trees;

        public Ground() {
            this.nutrient = 5;
            this.trees = new PriorityQueue<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        grounds = new Ground[N][N];
        additionalValueOfNutrients = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grounds[i][j] = new Ground();
            }
        }

        for (int i = 0; i < N; i++) {
            additionalValueOfNutrients[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int age = Integer.parseInt(stringTokenizer.nextToken());

            grounds[x][y].trees.add(age);
        }

        int treeCnt = 0;
        while (K-- > 0) {
            treeCnt = onYearFlow();

            if(treeCnt == 0) break;
        }

        System.out.println(treeCnt);
    }

    private static int onYearFlow() {
        int[][] breeding = SpringAndSummer();
        fall(breeding);
        winter();

        return countAliveTrees();
    }

    private static int countAliveTrees() {
        int alive = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                alive += grounds[i][j].trees.size();
            }
        }
        return alive;
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grounds[i][j].nutrient += additionalValueOfNutrients[i][j];
            }
        }
    }

    private static void fall(int[][] breeding) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(breeding[i][j] == 0) continue;

                for (int k = 0; k < 8; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    int newTreeCnt = breeding[i][j];

                    if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;

                    while (newTreeCnt-- > 0) {
                        grounds[nx][ny].trees.add(1);
                    }
                }
            }
        }
    }

    private static int[][] SpringAndSummer() {
        int[][] breeding = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                PriorityQueue<Integer> survivingTree = new PriorityQueue<>();
                Ground ground = grounds[i][j];

                int breedingCnt = 0;

                while (!ground.trees.isEmpty() && ground.nutrient - ground.trees.peek() >= 0) {
                    int age = ground.trees.poll();

                    ground.nutrient -= age;
                    survivingTree.add(age + 1);

                    if ((age + 1) % 5 == 0) {
                        breedingCnt += 1;
                    }
                }

                int diedTree = 0;
                while (!ground.trees.isEmpty()) {
                    diedTree += (int) Math.floor(ground.trees.poll() / 2);
                }

                ground.trees = survivingTree;
                ground.nutrient += diedTree;
                breeding[i][j] = breedingCnt;
            }
        }
        return breeding;
    }
}
