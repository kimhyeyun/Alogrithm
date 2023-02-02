import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_19637_IF문_좀_대신_써줘 {
    static class Title {
        String name;
        int max;

        public Title(String name, int max) {
            this.name = name;
            this.max = max;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        Title[] titles = new Title[N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            String n = stringTokenizer.nextToken();
            int m = Integer.parseInt(stringTokenizer.nextToken());

            titles[i] = new Title(n, m);
        }

        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(br.readLine());

            int left = 0;
            int right = N - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (titles[mid].max < x) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            sb.append(titles[left].name).append("\n");
        }

        System.out.println(sb);
    }
}
