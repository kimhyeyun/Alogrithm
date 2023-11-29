import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_17615_볼_모으기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int answer = Integer.MAX_VALUE;

        int count = 0;
        boolean isMoved = false;
        for (int i = 0; i < str.length(); i++) {
            if (isMoved && str.charAt(i) == 'R') {
                count += 1;
                continue;
            }

            if(str.charAt(i) == 'B') isMoved = true;
        }
        answer = Math.min(answer, count);

        count = 0;
        isMoved = false;
        for (int i = 0; i < str.length(); i++) {
            if (isMoved && str.charAt(i) == 'B') {
                count += 1;
                continue;
            }

            if(str.charAt(i) == 'R') isMoved = true;
        }
        answer = Math.min(answer, count);

        count = 0;
        isMoved = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (isMoved && str.charAt(i) == 'R') {
                count += 1;
                continue;
            }

            if(str.charAt(i) == 'B') isMoved = true;
        }
        answer = Math.min(answer, count);

        count = 0;
        isMoved = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (isMoved && str.charAt(i) == 'B') {
                count += 1;
                continue;
            }

            if (str.charAt(i) == 'R') isMoved = true;
        }
        answer = Math.min(answer, count);

        System.out.println(answer);
    }
}
