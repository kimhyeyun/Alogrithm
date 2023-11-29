import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_4716_풍선 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (N == 0 && A == 0 && B == 0) break;

            Team[] teams = new Team[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int c = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (a <= b) teams[i] = new Team(c, a, b, b - a, 'A');
                else teams[i] = new Team(c, a, b, a - b, 'B');
            }

            Arrays.sort(teams);

            int answer = 0;
            for (int i = 0; i < N; i++) {
                if (teams[i].closerRoom == 'A') {
                    if (teams[i].countOfBalloons <= A) {
                        answer += teams[i].distOfA * teams[i].countOfBalloons;
                        A -= teams[i].countOfBalloons;
                    } else {
                        answer += teams[i].distOfA * A;
                        teams[i].countOfBalloons -= A;
                        A = 0;
                        answer += teams[i].distOfB * teams[i].countOfBalloons;
                        B -= teams[i].countOfBalloons;
                    }
                } else {
                    if (teams[i].countOfBalloons <= B) {
                        answer += teams[i].distOfB * teams[i].countOfBalloons;
                        B -= teams[i].countOfBalloons;
                    } else {
                        answer += teams[i].distOfB * B;
                        teams[i].countOfBalloons -= B;
                        B = 0;
                        answer += teams[i].distOfA * teams[i].countOfBalloons;
                        A -= teams[i].countOfBalloons;
                    }
                }
            }
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static class Team implements Comparable<Team>{
        int countOfBalloons;
        int distOfA, distOfB, diff;
        char closerRoom;

        public Team(int countOfBalloons, int distOfA, int distOfB, int diff, char closerRoom) {
            this.countOfBalloons = countOfBalloons;
            this.distOfA = distOfA;
            this.distOfB = distOfB;
            this.diff = diff;
            this.closerRoom = closerRoom;
        }

        @Override
        public int compareTo(Team o) {
            return o.diff - this.diff;
        }
    }
}
