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

        int P = Integer.parseInt(br.readLine());
        while (P-- > 0) {
            st = new StringTokenizer(br.readLine());

            List<Integer> list = new ArrayList<>();
            int answer = 0;

            int T = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 20; i++) {
                int a = Integer.parseInt(st.nextToken());

                boolean isPut = false;
                if (list.isEmpty()) list.add(a);
                else {
                    for (int j = 0; j < list.size(); j++) {
                        if (a < list.get(j)) {
                            answer += list.size() - j;
                            list.add(j, a);
                            isPut = true;
                            break;
                        }
                    }
                    if(!isPut) list.add(a);
                }

            }

            sb.append(T + " " + answer).append("\n");
        }
        System.out.println(sb);
    }
}
