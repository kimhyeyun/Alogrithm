import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2138_전구와_스위치 {

    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] original1 = br.readLine().toCharArray();
        char[] target = br.readLine().toCharArray();

        char[] original2 = new char[N];
        System.arraycopy(original1, 0, original2, 0, N);

        answer = Integer.MAX_VALUE;

        DFS(1, 0, original1, target);

        pushSwitch(0, original2);
        DFS(1, 1, original2, target);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void DFS(int index, int count, char[] original, char[] target) {
        if (index == original.length) {
            if (original[index - 1] == target[index - 1]) answer = Math.min(answer, count);
            return;
        }

        if (original[index - 1] == target[index - 1]) DFS(index + 1, count, original, target);
        else {
            pushSwitch(index, original);
            DFS(index + 1, count + 1, original, target);
        }
    }

    private static void pushSwitch(int index, char[] original) {
        if (index == 0) {
            original[index] = original[index] == '1' ? '0' : '1';
            original[index + 1] = original[index + 1] == '1' ? '0' : '1';
        } else if (index == original.length - 1) {
            original[index] = original[index] == '1' ? '0' : '1';
            original[index - 1] = original[index - 1] == '1' ? '0' : '1';
        } else {
            original[index - 1] = original[index - 1] == '1' ? '0' : '1';
            original[index] = original[index] == '1' ? '0' : '1';
            original[index + 1] = original[index + 1] == '1' ? '0' : '1';
        }
    }
}
