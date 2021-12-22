package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_11720_숫자의_합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String str = br.readLine();
        char[] strToArray = str.toCharArray();

        int ans = 0;
        for(int i = 0 ; i < N ; i++){
            ans += (strToArray[i] - '0');
        }

        System.out.println(ans);
    }
}
