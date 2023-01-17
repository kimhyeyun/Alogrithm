import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17615_볼_모으기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String balls = br.readLine();
        int answer = Integer.MAX_VALUE;

        // 1. 왼쪽 - 빨간 공
        int count = 0;
        boolean isMove = false;
        for (int i = 0; i < balls.length(); i++) {
            if (isMove && balls.charAt(i) == 'R') {
                count += 1;
                continue;
            }

            if (balls.charAt(i) == 'B') {
                isMove = true;
            }
        }
        answer = Math.min(answer, count);

        //2. 왼쪽 - 파란 공
        count = 0;
        isMove = false;
        for (int i = 0; i < balls.length(); i++) {
            if (isMove && balls.charAt(i) == 'B') {
                count += 1;
                continue;
            }
            if (balls.charAt(i) == 'R') {
                isMove = true;
            }
        }
        answer = Math.min(answer, count);

        //3. 오른쪽 - 빨간 공
        count = 0;
        isMove = false;
        for (int i = balls.length() - 1; i >= 0; i--) {
            if (isMove && balls.charAt(i) == 'R') {
                count += 1;
                continue;
            }
            if (balls.charAt(i) == 'B') {
                isMove = true;
            }
        }
        answer = Math.min(answer, count);

        //4. 오른쪽 - 파란 공
        count = 0;
        isMove = false;
        for (int i = balls.length() - 1; i >= 0; i--) {
            if (isMove && balls.charAt(i) == 'B') {
                count += 1;
                continue;
            }

            if (balls.charAt(i) == 'R') {
                isMove = true;
            }
        }
        answer = Math.min(answer, count);

        System.out.println(answer);
    }
}
