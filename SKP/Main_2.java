import java.util.*;

public class Main_2 {
    public static int[] solution(int[] deposit) {
        int[] answer = {};
        Deque<Integer> list = new LinkedList<>();

        list.add(deposit[0]);
        for (int i = 1; i < deposit.length; i++) {
            int tmp = deposit[i];
            if (tmp < 0) {
                tmp *= -1;
                while (tmp != 0) {
                    int last = list.pollLast();
                    if (last < tmp)
                        tmp -= last;
                    else if (last > tmp) {
                        list.addLast(last - tmp);
                        tmp = 0;
                    } else {
                        tmp = 0;
                    }
                }

            } else list.add(tmp);
        }

        answer = new int[list.size()];
        int idx = 0;
        for (int l : list) {
            answer[idx++] = l;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] d = {500, 1000, 2000, -1000, -1500, 500};
        int[] ans = solution(d);

        for (int a : ans) {
            System.out.println(a);
        }
    }
}
