import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_12871_무한문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();
        String S = s;
        String T = t;

        if (s.length() != t.length()) {

            int len = lcd(s.length(), t.length());

            while (S.length() != len) {
                S += s;
            }

            while (T.length() != len) {
                T += t;
            }
        }

        if (S.equals(T))
            System.out.println(1);
        else
            System.out.println(0);
    }

    private static int lcd(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    private static int gcd(int a, int b) {
        int tmp;
        while (b > 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }

        return a;
    }
}
