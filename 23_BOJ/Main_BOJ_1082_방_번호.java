import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1082_방_번호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        int[] answer = new int[100];
        int cost = 0;
        int index = 0;

        int minIndex = findMin(0, N, arr);
        if (minIndex == 0) {
            minIndex = findMin(1, N, arr);
            if (arr[minIndex] <= M) {
                answer[index++] = minIndex;
                cost += arr[minIndex];
                minIndex = 0;
            } else {
                System.out.println(0);
                return;
            }
        }

        while (cost + arr[minIndex] <= M) {
            answer[index++] = minIndex;
            cost += arr[minIndex];
        }

        for (int i = 0; i < index; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (cost - arr[answer[i]] + arr[j] <= M) {
                    cost = cost - arr[answer[i]] + arr[j];
                    answer[i] = j;
                    break;
                }
            }
        }

        for (int i = 0; i < index; i++) {
            System.out.print(answer[i]);
        }
    }

    private static int findMin(int start, int N, int[] arr) {
        int index = 0, min = 100;
        for (int i = start; i < N; i++) {
            if(min > arr[i]){
                min = arr[i];
                index = i;
            }
        }
        return index;
    }
}
