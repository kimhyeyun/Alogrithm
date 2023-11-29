import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2590_색종이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] papers = new int[7];
        for (int i = 1; i <= 6; i++) {
            papers[i] = Integer.parseInt(br.readLine());
        }

        int answer = papers[6];

        while (papers[1] != 0 || papers[2] != 0 || papers[3] != 0 || papers[4] != 0 || papers[5] != 0) {
            while (papers[5] > 0) {
                int tmp = 36;
                papers[5] -= 1;
                tmp -= 25;

                if(papers[1] <= tmp) papers[1] = 0;
                else papers[1] -= tmp;

                answer += 1;
            }

            while (papers[4] > 0) {
                int tmp = 36;
                papers[4] -= 1;
                tmp -= 16;

                if (papers[2] > 5) {
                    papers[2] -= 5;
                    tmp -= 20;
                } else {
                    tmp -= (4 * papers[2]);
                    papers[2] = 0;
                }

                if(papers[1] <= tmp) papers[1] = 0;
                else papers[1] -= tmp;

                answer += 1;
            }

            while (papers[3] > 0) {
                int tmp = 36;

                if (papers[3] > 4) {
                    papers[3] -= 4;
                    tmp = 0;
                } else {
                    tmp -= (9 * papers[3]);
                    papers[3] = 0;
                }

                if (tmp == 27 && papers[2] > 5) {
                    papers[2] -= 5;
                    tmp -= 20;
                } else if (tmp == 27 && papers[2] <= 5) {
                    tmp -= (4 * papers[2]);
                    papers[2] = 0;
                }

                if (tmp == 18 && papers[2] > 3) {
                    papers[2] -= 3;
                    tmp -= 12;
                } else if (tmp == 18 && papers[2] <= 3) {
                    tmp -= (4 * papers[2]);
                    papers[2] = 0;
                }

                if (tmp == 9 && papers[2] >= 1) {
                    tmp -= 4;
                    papers[2] -= 1;
                }

                if(papers[1] <= tmp) papers[1] = 0;
                else papers[1] -= tmp;

                answer += 1;
            }

            while (papers[2] > 0) {
                int tmp = 36;

                if (papers[2] > 9) {
                    papers[2] -= 9;
                    tmp = 0;
                } else {
                    tmp -= (4 * papers[2]);
                    papers[2] = 0;
                }

                if(papers[1] <= tmp) papers[1] = 0;
                else papers[1] -= tmp;

                answer += 1;
            }
        }

        System.out.println(answer);
    }
}
