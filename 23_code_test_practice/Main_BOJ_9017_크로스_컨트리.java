import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_9017_크로스_컨트리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            Map<Integer, Integer> countOfTeam = new HashMap<>();

            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());

                countOfTeam.put(arr[i], countOfTeam.getOrDefault(arr[i], 0) + 1);
            }

            Map<Integer, Integer> scoreOfTeam = new HashMap<>();
            Map<Integer, Integer> fifthOfTeam = new HashMap<>();
            Map<Integer, Integer> countOfFourOfTeam = new HashMap<>();

            int score = 1;
            for (int i = 0; i < N; i++) {
                if(countOfTeam.get(arr[i]) != 6) continue;
                if (countOfFourOfTeam.containsKey(arr[i]) && countOfFourOfTeam.get(arr[i]) == 4) {
                    score += 1;
                    if(!fifthOfTeam.containsKey(arr[i])) fifthOfTeam.put(arr[i], i + 1);

                    continue;
                }

                countOfFourOfTeam.put(arr[i], countOfFourOfTeam.getOrDefault(arr[i], 0) + 1);
                scoreOfTeam.put(arr[i], scoreOfTeam.getOrDefault(arr[i], 0) + score);
                score += 1;
            }



            List<Integer> keySet = new ArrayList<>(scoreOfTeam.keySet());
            keySet.sort((o1, o2) -> {
                if (Objects.equals(scoreOfTeam.get(o1), scoreOfTeam.get(o2))) {
                    return fifthOfTeam.get(o1) - fifthOfTeam.get(o2);
                }
                return scoreOfTeam.get(o1) - scoreOfTeam.get(o2);
            });

            System.out.println(keySet.get(0));
        }
    }
}
