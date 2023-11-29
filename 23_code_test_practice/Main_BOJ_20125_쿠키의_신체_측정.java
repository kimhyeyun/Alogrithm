import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_20125_쿠키의_신체_측정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        char[][] cookie = new char[N][N];
        int[] head = null;

        for (int i = 0; i < N; i++) {
            cookie[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (cookie[i][j] == '*' && head == null) {
                    head = new int[]{i, j};
                }
            }
        }

        int[] heart = new int[]{head[0] + 1, head[1]};

        // 왼쪽 팔
        int leftArm = -1;
        for (int j = 0; j < heart[1]; j++) {
            if (cookie[heart[0]][j] == '*'){
                leftArm = j;
                break;
            }
        }

        // 오른쪽 팔
        int rightArm = -1;
        for (int j = heart[1] + 1; j < N; j++) {
            if (cookie[heart[0]][j] == '_') {
                rightArm = j - 1;
                break;
            }
        }

        // 허리
        int waist = -1;
        for (int i = heart[0]; i < N; i++) {
            if (cookie[i][heart[1]] == '_') {
                waist = i - 1;
                break;
            }
        }

        // 왼쪽 다리
        int leftLeg = -1;
        for (int i = waist + 1; i < N; i++) {
            if (cookie[i][heart[1] - 1] == '_') {
                leftLeg = i - 1;
                break;
            }
        }

        // 오른쪽 다리
        int rightLeg = -1;
        for (int i = waist + 1; i < N; i++) {
            if (cookie[i][heart[1] + 1] == '_') {
                rightLeg = i - 1;
                break;
            }
        }

        sb.append(heart[0] + 1).append(" ").append(heart[1] + 1).append("\n");

        sb.append(heart[1] - leftArm).append(" ");

        if(rightArm == -1) rightArm = N - 1;
        sb.append(rightArm - heart[1]).append(" ");

        sb.append(waist - heart[0]).append(" ");

        if(leftLeg == -1) leftLeg = N - 1;
        sb.append(leftLeg - waist).append(" ");

        if(rightLeg == -1) rightLeg = N - 1;
        sb.append(rightLeg - waist).append(" ");


        System.out.println(sb);

    }

}
