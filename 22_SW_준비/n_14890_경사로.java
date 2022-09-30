import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_14890_경사로 {
    static int N, L, answer;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = 0;
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            int[] col = new int[N];
            if (isPossible(map[i])) answer += 1;
            for (int j = 0; j < N; j++) {
                col[j] = map[j][i];
            }
            if (isPossible(col)) answer += 1;

        }

        System.out.println(answer);
    }

    private static boolean isPossible(int[] arr) {
        boolean[] isSet = new boolean[N];
        for (int i = 1; i < N; i++) {
            if(arr[i - 1] == arr[i]) continue;
            if(Math.abs(arr[i-1] - arr[i]) > 1) return false;

            if (arr[i] > arr[i - 1]) {
                // 오르막길
                for (int j = i - 1; j >= i - L; j--) {
                    if(j < 0) return false;
                    if (isSet[j]) return false;
                    if (arr[j] != arr[i - 1]) return false;

                    isSet[j] = true;
                }
            } else {
                // 내리막길
                for (int j = i; j < i + L; j++) {
                    if(j >= N) return false;
                    if(isSet[j]) return false;
                    if(arr[j] != arr[i]) return false;

                    isSet[j] = true;
                }
            }
        }
        return true;
    }
}
