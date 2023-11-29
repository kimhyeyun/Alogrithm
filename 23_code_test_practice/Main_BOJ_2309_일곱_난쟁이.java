import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_2309_일곱_난쟁이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] height = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            height[i] = Integer.parseInt(br.readLine());
            sum += height[i];
        }

        Arrays.sort(height);

        sum -= 100;
        boolean flag = false;
        int index1 = -1, index2 = -1;
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                int n = height[i] + height[j];
                if (n == sum) {
                    index1 = i;
                    index2 = j;
                    flag = true;
                }
            }
            if (flag) break;
        }

        for (int i = 0; i < 9; i++) {
            if (i == index1 || i == index2) continue;
            System.out.println(height[i]);
        }
    }
}
