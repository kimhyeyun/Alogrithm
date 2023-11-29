import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1508_레이스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] possibleLocation = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            possibleLocation[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(N, M, K, possibleLocation));


    }

    private static String binarySearch(int n, int m, int k, int[] possibleLocation) {
        String answer = "";

        int left = 0, right = n;
        while (left <= right) {
            int mid = (left + right) / 2;
            String referees = setReferees(m, k, possibleLocation, mid);

            if(referees.equals("-1")) right = mid - 1;
            else {
                left = mid + 1;
                answer = referees;
            }
        }
        return answer;
    }

    private static String setReferees(int m, int k, int[] possibleLocation, int dist) {
        StringBuilder sb = new StringBuilder();

        int count = 1;
        int lastIndex = possibleLocation[0];

        sb.append("1");

        for (int i = 1; i < k; i++) {
            int cur = possibleLocation[i];
            if (cur - lastIndex < dist) sb.append("0");
            else {
                sb.append("1");
                lastIndex = cur;
                count += 1;
            }

            if (count == m) {
                sb.append("0".repeat(k - i - 1));
                break;
            }
        }
        return count == m ? sb.toString() : "-1";
    }
}
