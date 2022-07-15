import java.util.*;

public class L1_실패율 {
    public static int[] solution(int N, int[] stages) {
        double[] count = new double[N + 1];
        double totalPlayer = stages.length;

        for (int stage : stages) {
            if(stage == N + 1) continue;
            count[stage] += 1;
        }

        Map<Integer, Double> failure = new HashMap<>();

        for (int i = 1; i < N + 1; i++) {
            totalPlayer -= count[i - 1];
            if(totalPlayer <= 0) failure.put(i, 0.0);
            else failure.put(i, count[i] / totalPlayer);
        }

        List<Integer> keyList = new ArrayList<>(failure.keySet());
        Collections.sort(keyList, ((o1, o2) -> (failure.get(o2).compareTo(failure.get(o1)))));

        for (int key : keyList) {
            System.out.println(key + " " + failure.get(key));

        }


        return keyList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] stages = {4,4,4,4,4};
        solution(4, stages);
    }
}
