import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 흑묵자흑 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = br.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);

        String[] input = br.readLine().split(" ");
        // System.out.println("Hello Goorm! Your input is " + input);

        List<Integer> list = new ArrayList<>();
        int idx = 0;

        for (int i = 0; i < input.length; i++) {
            list.add(Integer.parseInt(input[i]));
        }

        Collections.sort(list);

        int tmp = N - K;
        if (N <= K) {
            System.out.println(1);
            return;
        } else if (tmp % (K - 1) == 0) {
            System.out.println(tmp / (K - 1) + 1);
        } else {
            System.out.println(tmp / (K - 1) + 2);
        }

/*
        boolean flag = false;
        if(idx <= K - 1) flag = true;

        int ans = 0;
        if (flag) {
            int start = 0;
            int dest = K-1;
            ans += 1;

            while (dest < N) {
                ans += 1;
                start = dest;
                dest = start + K - 1;
            }
        }
        else{
            if (idx - K + 1 <= K) {
                int start = K;
                int dest = start + K - 1;
                ans += 2;
                while (dest < N) {
                    ans += 1;
                    start = dest;
                    dest = start + K - 1;
                }
            }else{
                int start = idx - K + 1;
                int dest = idx;
                ans += 1;
                while (dest < N) {
                    ans += 1;
                    start = dest;
                    dest = start + K - 1;
                }

                dest = idx - K + 1;
                start = dest - K + 1;
                while (start > 0) {
                    ans += 1;
                    dest = start;
                    start = dest - K + 1;
                }
            }
        }

        System.out.println(ans);*/
    }
}
