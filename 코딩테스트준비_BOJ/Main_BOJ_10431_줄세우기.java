import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_10431_줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();
            int answer = 0;

            for (int i = 0; i < 20; i++) {
                boolean isPut = false;
                int x = Integer.parseInt(st.nextToken());

                if (list.isEmpty()) {
                    list.add(x);
                } else {
                    for (int j = 0; j < list.size(); j++) {
                        if (x < list.get(j)) {
                            answer += list.size() - j;
                            list.add(j, x);
                            isPut = true;
                            break;
                        }
                    }
                    if(!isPut) list.add(x);
                }
            }
            sb.append(t + " " + answer).append("\n");
        }
        System.out.println(sb);
    }
}
