import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_19637_IF문_좀_대신_써줘 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Title> titles = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String title = st.nextToken();
            int maxOfPower = Integer.parseInt(st.nextToken());

            titles.add(new Title(title, maxOfPower));
        }


        for (int i = 0; i < M; i++) {
            int power = Integer.parseInt(br.readLine());

            int left = 0;
            int right = N - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if(titles.get(mid).maxOfPower < power) left = mid + 1;
                else right = mid - 1;
            }
            sb.append(titles.get(left).title).append("\n");
        }
        System.out.println(sb);
    }

    private static class Title {
        String title;
        int maxOfPower;

        public Title(String title, int maxOfPower) {
            this.title = title;
            this.maxOfPower = maxOfPower;
        }
    }
}
