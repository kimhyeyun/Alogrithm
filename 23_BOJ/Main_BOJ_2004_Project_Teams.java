import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_2004_Project_Teams {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] weights = new int[2 * n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 2 * n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weights);

        int min = Integer.MAX_VALUE;
        int left = 0, right = 2 * n - 1;
        while (left < right) {
            min = Math.min(min, weights[left] + weights[right]);
            left += 1;
            right -= 1;
        }

        System.out.println(min);
    }

}