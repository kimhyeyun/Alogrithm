import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1244_스위치_켜고_끄기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] switches = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int gender = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());

            if (gender == 1) {
                for (int j = index; j <= N; j += index) {
                    switches[j] = switches[j] == 1 ? 0 : 1;
                }
            } else {
                int left = index - 1;
                int right = index + 1;
                while (true) {
                    if(left <= 0 || right > N) break;
                    if (switches[left] == switches[right]) {
                        left -= 1;
                        right += 1;
                    } else break;
                }

                left += 1;
                right -= 1;

                for (int j = left; j <= right; j++) {
                    switches[j] = switches[j] == 1 ? 0 : 1;
                }
            }
        }


        for (int i = 1; i <= N; i++) {
            System.out.print(switches[i] + " ");
            if(i % 20 == 0) System.out.println();
        }
    }
}
