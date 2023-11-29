import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_11723_집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Set<Integer> set = new HashSet<>();

        int M = Integer.parseInt(br.readLine());

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            if (command.equals("add")) {
                int x = Integer.parseInt(st.nextToken());
                set.add(x);
            } else if (command.equals("remove")) {
                int x = Integer.parseInt(st.nextToken());
                if(!set.contains(x)) continue;
                set.remove(x);
            } else if (command.equals("check")) {
                int x = Integer.parseInt(st.nextToken());
                if(set.contains(x)) sb.append(1).append("\n");
                else  sb.append(0).append("\n");
            } else if (command.equals("toggle")) {
                int x = Integer.parseInt(st.nextToken());
                if(set.contains(x)) set.remove(x);
                else set.add(x);
            } else if (command.equals("all")) {
                set.clear();
                for (int i = 1; i <= 20; i++) {
                    set.add(i);
                }
            } else {
                set.clear();
            }
        }

        System.out.println(sb);
    }
}
