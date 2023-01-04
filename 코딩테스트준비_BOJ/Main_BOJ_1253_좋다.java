import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1253_좋다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());

        int[] number = new int[N];
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(stringTokenizer.nextToken());
            number[i] = n;
        }

        Arrays.sort(number);
        int answer = 0;

        for (int i = 0; i < number.length; i++) {
            int findNumber = number[i];

            int left = 0;
            int right = number.length - 1;
            int sum = 0;

            while (left < right) {
                sum = number[left] + number[right];

                if (sum == findNumber) {
                    if (i == left) {
                        left += 1;
                    } else if (right == i) {
                        right -= 1;
                    } else {
                        answer += 1;
                        break;
                    }
                }

                if (number[left] + number[right] > findNumber) {
                    right -= 1;
                } else if (number[left] + number[right] < findNumber) {
                    left += 1;
                }
            }
        }
        System.out.println(answer);
    }
}

