import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L1_신고_결과_받기 {
    public static int[] solution(String[] id_list, String[] report, int k){
        Map<String, Integer> userIdx = new HashMap<>();
        Map<String, List<String>> reportedUser = new HashMap<>();

        int[] answer = new int[id_list.length];

        for (int i = 0; i < id_list.length; i++) {
            userIdx.put(id_list[i], i);
        }

        List<String> list;
        for (String r : report) {
            String[] users = r.split(" ");

            if(reportedUser.containsKey(users[1])) list = reportedUser.get(users[1]);
            else list = new ArrayList<>();

            if(list.contains(users[0])) continue;

            list.add(users[0]);
            reportedUser.put(users[1], list);
        }

        for (String user : userIdx.keySet()) {
            if (reportedUser.containsKey(user)) {
                list = reportedUser.get(user);
                if (list.size() >= k) {
                    for (String l : list) {
                        answer[userIdx.get(l)] += 1;
                    }
                }
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};

        int[] ans = solution(id_list, report, 2);

        for (int a : ans) {
            System.out.println(a);
        }
    }
}
