import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_12018_Yonsei_TOTO {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] mileages = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            List<Integer> tmp = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            if (p < l) {
                mileages[i] = 1;
                continue;
            }

            for (int j = 0; j < p; j++) {
                tmp.add(Integer.parseInt(st.nextToken()));
            }

            tmp.sort(Collections.reverseOrder());

            mileages[i] = tmp.get(l - 1);
        }

        Arrays.sort(mileages);
        int sum = 0, answer = 0;
        for (int i = 0; i < mileages.length; i++) {
            sum += mileages[i];
            if (sum > M) break;
            answer += 1;
        }

        System.out.println(answer);
    }
}
