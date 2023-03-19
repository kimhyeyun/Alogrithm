import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14890_경사로 {
    static int N, L;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if(isPossible(map[i])) answer += 1;

            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = map[j][i];
            }
            if(isPossible(arr)) answer += 1;
        }
        System.out.println(answer);
    }

    private static boolean isPossible(int[] arr) {
        boolean[] isSet = new boolean[N];

        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[i - 1]) continue;
            if(Math.abs(arr[i] - arr[i-1]) > 1) return false;

            if (arr[i] > arr[i - 1]) {
                // 오르막길
                for (int j = i - 1; j >= i - L; j--) {
                    if (j < 0) return false;
                    if (arr[j] != arr[i - 1]) return false;
                    if (isSet[j]) return false;

                    isSet[j] = true;
                }
            } else {
                // 내리막길
                for (int j = i; j < i + L; j++) {
                    if(j >= N) return false;
                    if(arr[j] != arr[i]) return false;
                    if(isSet[j]) return false;

                    isSet[j] = true;
                }
            }
        }
        return true;
    }
}
