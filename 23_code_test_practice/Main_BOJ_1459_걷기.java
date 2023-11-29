import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1459_걷기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        long W = Long.parseLong(st.nextToken());
        long S = Long.parseLong(st.nextToken());

        long temp1, temp2, temp3;

        temp1 = (X + Y) * W;

        temp2 = 0;
        if((X + Y) % 2 == 0) temp2 = Math.max(X, Y) * S;
        else temp2 = (Math.max(X, Y) - 1) * S + W;

        temp3 = Math.min(X, Y) * S + Math.abs(X - Y) * W;

        System.out.println(Math.min(temp1, Math.min(temp2, temp3)));
    }
}
