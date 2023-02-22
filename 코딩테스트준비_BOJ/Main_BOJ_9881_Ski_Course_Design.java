import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_BOJ_9881_Ski_Course_Design {
    static int N, answer;
    static List<Hill> hills;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        hills = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            hills.add(new Hill(Integer.parseInt(br.readLine())));
        }
        Collections.sort(hills);

        answer = Integer.MAX_VALUE;
        for (int i = hills.get(0).height; i < hills.get(N - 1).height; i++) {
            if(i + 17 > hills.get(N-1).height) break;
            answer = Math.min(answer, calc(i, i + 17));
        }
        if(answer == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(answer);
    }

    private static int calc(int min, int max) {
        int ret = 0;
        for (int i = 0; i < N; i++) {
            hills.get(i).mass = 0;
            if(hills.get(i).height < min) hills.get(i).mass = min - hills.get(i).height;
            if(hills.get(i).height > max) hills.get(i).mass = hills.get(i).height - max;

            ret += Math.pow(hills.get(i).mass, 2);
        }
        return ret;
    }

    private static class Hill implements Comparable<Hill> {
        int height, mass;

        public Hill(int height) {
            this.height = height;
            this.mass = 0;
        }

        @Override
        public int compareTo(Hill o) {
            return this.height - o.height;
        }
    }
}