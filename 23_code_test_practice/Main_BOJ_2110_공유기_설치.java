import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2110_공유기_설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] coords = new int[N];
        for (int i = 0; i < N; i++) {
            coords[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coords);

        int left = 1, right = coords[N - 1] - coords[0] + 1;
        while (left < right) {
            int mid = (left + right) / 2;

            if(canInstall(coords, mid) < C) right = mid;
            else left = mid + 1;
        }

        System.out.println(left - 1);
    }

    private static int canInstall(int[] coords, int mid) {
        int count = 1;
        int lastCoord = coords[0];

        for (int i = 1; i < coords.length; i++) {
            int now = coords[i];
            if (now - lastCoord >= mid) {
                count += 1;
                lastCoord = now;
            }
        }
        return count;
    }
}
