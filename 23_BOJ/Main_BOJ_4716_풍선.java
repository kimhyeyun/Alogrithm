import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_4716_풍선 {

    private static class TeamInfo implements Comparable<TeamInfo> {
        int balloon;
        int a, b, diff;
        char pb;

        public TeamInfo(int balloon, int a, int b, int diff, char pb) {
            this.balloon = balloon;
            this.a = a;
            this.b = b;
            this.diff = diff;
            this.pb = pb;
        }

        @Override
        public int compareTo(TeamInfo o) {
            return o.diff - this.diff;
        }
    }

    static int N, A, B;
    static TeamInfo[] teams;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            if(N == 0 && A == 0 && B == 0) break;

            int answer = 0;
            teams = new TeamInfo[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int balloon = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(a <= b) teams[i] = new TeamInfo(balloon, a, b, b - a, 'A');
                else teams[i] = new TeamInfo(balloon, a, b, a - b, 'B');
            }

            Arrays.sort(teams);

            for (int i = 0; i < N; i++) {
                if (teams[i].pb == 'B') {
                    if (B >= teams[i].balloon) {
                        answer += (teams[i].b * teams[i].balloon);
                        B -= teams[i].balloon;
                    } else {
                        int leftA = teams[i].balloon - B;
                        answer += (B * teams[i].b) + (leftA * teams[i].a);
                        A -= leftA;
                        B = 0;
                    }
                } else {
                    if (A >= teams[i].balloon) {
                        answer += (teams[i].a * teams[i].balloon);
                        A -= teams[i].balloon;
                    } else {
                        int leftB = teams[i].balloon - A;
                        answer += (A * teams[i].a) + (leftB * teams[i].b);
                        B -= leftB;
                        A = 0;
                    }
                }
            }

            System.out.println(answer);
        }
    }
}
