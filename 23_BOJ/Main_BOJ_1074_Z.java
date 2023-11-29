import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1074_Z {
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, N);

        recur(size, x, y);

        System.out.println(answer);
    }

    private static void recur(int size, int x, int y) {
        if(size == 1) return ;

        if(x < size / 2 && y < size / 2) recur(size / 2, x, y);
        else if (x < size / 2 && y >= size / 2) {
            answer += size * size / 4;
            recur(size / 2, x, y - size / 2);
        } else if (x >= size / 2 && y < size / 2) {
            answer += (size * size / 4) * 2;
            recur(size / 2, x - size / 2, y);
        } else {
            answer += (size * size / 4) * 3;
            recur(size / 2, x - size / 2, y - size / 2);
        }
    }
}
