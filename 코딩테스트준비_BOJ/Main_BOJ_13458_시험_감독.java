import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_13458_시험_감독 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        int[] rooms = new int[N];
        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            rooms[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(stringTokenizer.nextToken());
        int C = Integer.parseInt(stringTokenizer.nextToken());

        long ans = N;
        for (int i = 0; i < N; i++) {
            rooms[i] -= B;

            if (rooms[i] > 0) {
                if (rooms[i] <= C) {
                    ans += 1;
                } else {
                    if(rooms[i]%C != 0) ans += (rooms[i] / C + 1);
                    else ans += (rooms[i] / C);
                }
            }
        }

        System.out.println(ans);


    }
}
