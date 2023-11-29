import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_2831_댄스_파티 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1, st2;

        int N = Integer.parseInt(br.readLine());

        List<Integer>[] women = new List[2];
        List<Integer>[] men = new List[2];

        for (int i = 0; i < 2; i++) {
            women[i] = new ArrayList<>();
            men[i] = new ArrayList<>();
        }

        st1 = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st1.nextToken());

            if(height < 0) men[0].add(height * -1);
            else men[1].add(height);

            height = Integer.parseInt(st2.nextToken());

            if(height < 0) women[0].add(height * -1);
            else women[1].add(height);
        }

        for (int i = 0; i < 2; i++) {
            Collections.sort(men[i]);
            Collections.sort(women[i]);
        }

        int answer = 0;

        answer += findPair(true, men[0], women[1]);
        answer += findPair(false, men[1], women[0]);

        System.out.println(answer);
    }

    private static int findPair(boolean isMenTaller, List<Integer> men, List<Integer> women) {
        int result = 0;

        for (int i = 0, j = 0; i < men.size() && j < women.size(); ) {
            int tall = 0, small = 0;

            if (isMenTaller) {
                tall = men.get(i);
                small = women.get(j);
            } else {
                tall = women.get(j);
                small = men.get(i);
            }

            if (tall <= small) {
                if(isMenTaller) i += 1;
                else j += 1;

                continue;
            }

            result += 1;
            i += 1;
            j += 1;
        }

        return result;
    }
}
