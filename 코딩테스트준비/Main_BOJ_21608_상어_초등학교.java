import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_21608_상어_초등학교 {
    static class Student{
        int x, y;
        int[] friends;

        public Student(int x, int y, int[] friends) {
            this.x = x;
            this.y = y;
            this.friends = friends;
        }
    }

    static int N;
    static int[][] classRoom, nearEmpty;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static Map<Integer, Student> studentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        classRoom = new int[N][N];
        studentList = new HashMap<>();

        findNearEmptySeat();

        for (int i = 0; i < N * N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int f1 = Integer.parseInt(stringTokenizer.nextToken());
            int f2 = Integer.parseInt(stringTokenizer.nextToken());
            int f3 = Integer.parseInt(stringTokenizer.nextToken());
            int f4 = Integer.parseInt(stringTokenizer.nextToken());

            findSeat(n, new int[]{f1, f2, f3, f4});
        }

        int ans = 0;

        for (int i = 1; i <= N * N; i++) {
            Student student = studentList.get(i);
            int cnt = 0;
            for (int friend : student.friends) {
                if (Math.abs(studentList.get(friend).x - student.x) + Math.abs(studentList.get(friend).y - student.y) == 1) {
                    cnt += 1;
                }
            }

            if(cnt == 1) ans += 1;
            else if(cnt == 2) ans += 10;
            else if(cnt == 3) ans += 100;
            else if(cnt == 4) ans += 1000;
        }

        System.out.println(ans);
    }

    private static void findSeat(int n, int[] friends) {
        int[][] nearFriends = new int[N][N];
        for (int friend : friends) {
            if (studentList.containsKey(friend)) {
                Student student = studentList.get(friend);
                int x = student.x;
                int y = student.y;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if(nx < 0 || ny < 0 || N <= nx || N <= ny || classRoom[nx][ny] != 0) continue;

                    nearFriends[nx][ny] += 1;
                }
            }
        }

        int emptyMax = -1, nearFriendMax = -1, choiceX = -1, choiceY = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(classRoom[i][j] != 0) continue;
                if (nearFriendMax < nearFriends[i][j]) {
                    choiceX = i;
                    choiceY = j;
                    nearFriendMax = nearFriends[i][j];
                    emptyMax = nearEmpty[i][j];
                } else if (nearFriendMax == nearFriends[i][j] && emptyMax < nearEmpty[i][j]) {
                    emptyMax = nearEmpty[i][j];
                    choiceX = i;
                    choiceY = j;
                }
            }
        }

        classRoom[choiceX][choiceY] = n;
        studentList.put(n, new Student(choiceX, choiceY, friends));

        for (int i = 0; i < 4; i++) {
            int nx = choiceX + dx[i];
            int ny = choiceY + dy[i];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny || classRoom[nx][ny] != 0) continue;

            nearEmpty[nx][ny] -= 1;
        }
    }

    private static void findNearEmptySeat() {
        nearEmpty = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nearEmpty[i][j] = 4;
                if(i == 0 || i == N - 1) nearEmpty[i][j] -= 1;
                if(j == 0 || j == N - 1) nearEmpty[i][j] -= 1;
            }
        }
    }
}
