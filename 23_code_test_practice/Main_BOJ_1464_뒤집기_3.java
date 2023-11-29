import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main_BOJ_1464_뒤집기_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();
        String temp = s.substring(0, 1);

        for (int i = 1; i < s.length(); i++) {
            if (temp.charAt(i - 1) < s.charAt(i)) temp = s.charAt(i) + temp;
            else temp = temp + s.charAt(i);
        }

        sb = new StringBuilder(temp).reverse();
        System.out.println(sb);
    }
}
