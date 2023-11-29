import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1700_멀티탭_스켐줄링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[K];
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] isUsed = new boolean[K + 1];
        int index = 0, count = 0;

        while (count < N && index < K) {
            if (!isUsed[arr[index]]) {
                isUsed[arr[index]] = true;
                count += 1;
            }
            index += 1;
        }

        int answer = 0;
        while (index < K) {
            if (!isUsed[arr[index]]) {
                List<Integer> list = new ArrayList<>();
                for (int i = index; i < K; i++) {
                    if (isUsed[arr[i]] && !list.contains(arr[i])) list.add(arr[i]);
                }

                if (list.size() == N) {
                    int remove = list.get(list.size() - 1);
                    isUsed[remove] = false;
                } else {
                    for (int j = 1; j <= K; j++) {
                        if (isUsed[j] && !list.contains(j)) {
                            isUsed[j] = false;
                            break;
                        }
                    }
                }

                isUsed[arr[index]] = true;
                answer += 1;
            }
            index += 1;
        }

        System.out.println(answer);
    }
}
