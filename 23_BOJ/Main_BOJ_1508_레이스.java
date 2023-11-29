import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1508_레이스 {

    static int N, M, K;
    static int[] positions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        positions = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch());

    }

    private static String binarySearch() {
        String answer = "";

        int left = 1, right = N;
        while (left <= right) {
            int mid = (left + right) / 2;
            String referees = setReferees(mid);

            if(referees.equals("-1")) right = mid - 1;
            else {
                left = mid + 1;
                answer = referees;
            }
        }
        return answer;
    }

    private static String setReferees(int dist) {
        StringBuilder sb = new StringBuilder();

        int count = 1;
        int lastIndex = positions[0];

        sb.append("1");

        for (int i = 1; i < K; i++) {
            int curIndex = positions[i];
            if(curIndex - lastIndex < dist) sb.append("0");
            else {
                sb.append("1");
                lastIndex = curIndex;
                count += 1;
            }

            if (count == M) {
                sb.append("0".repeat(K - i - 1));
                break;
            }
        }
        return count == M ? sb.toString() : "-1";

    }
}
