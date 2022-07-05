import java.util.*;

public class Programmers_신고_결과_받기 {
    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Integer> idIdx = new HashMap<>();
        Map<String, Set<String>> reportedId = new HashMap<>(); // 신고 당한 아이디 - 신고하는 사람

        int i = 0;
        for (String id : id_list) {
            idIdx.put(id, i++);
            reportedId.put(id, new HashSet<>());
        }

        for (String report_str : report) {
            String[] ids = report_str.split(" ");

            reportedId.get(ids[1]).add(ids[0]);
        }

        for (String key : reportedId.keySet()) {
            if (reportedId.get(key).size() >= k) {
                for (String id : reportedId.get(key)) {
                    answer[idIdx.get(id)] += 1;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};

        int[] answer = solution(id_list, report, 2);
        for (int i : answer) {
            System.out.println(i);
        }
    }
}
