import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14890_경사로 {
    static int N, L;
    static int[][] map;
    static boolean[] stairs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        L = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][N];
        stairs = new boolean[N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int[] sero = new int[N];
            if (isPossible(map[i])) {
                cnt += 1;
            }

            for (int j = 0; j < N; j++) {
                sero[j] = map[j][i];
            }
            if(isPossible(sero)) cnt += 1;
        }
        System.out.println(cnt);
    }

    private static boolean isPossible(int[] arr) {
        stairs = new boolean[N];
        int idx = 0;

        while (idx < N - 1) {
            if (arr[idx] == arr[idx + 1]) {
                idx += 1;
            }else if(Math.abs(arr[idx+1] - arr[idx]) > 1) return false;
            else if (arr[idx + 1] > arr[idx]) {
                if (idx - L + 1 >= 0) {
                    for (int j = idx - L + 1; j <= idx; j++) {
                        if (arr[j] != arr[idx] || stairs[j]) return false;
                    }
                    idx += 1;
                } else return false;
            } else {
                if (idx + L + 1 > N) return false;
                else{
                    for (int j = idx + 1; j <= idx + L; j++) {
                        stairs[j] = true;
                        if(arr[j] != arr[idx] - 1) return false;
                    }
                    idx += L;
                }
            }
        }
        return true;
    }
}
