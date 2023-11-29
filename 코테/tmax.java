import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class tmax {
    public int solution(int[] preferences, int[] coffee) {
        Arrays.sort(preferences);
        Arrays.sort(coffee);

        int answer = 0;
        for (int i = 0; i < preferences.length; i++) {
            if (preferences[i] >= coffee[i]) {
                continue;
            }

            answer += coffee[i] - preferences[i];
        }
        return answer;
    }

    public class Money {
        int idx;
        int amount;

        public Money(int idx, int amount) {
            this.idx = idx;
            this.amount = amount;
        }
    }
    public int[] solution(int[] balance, int[][] transaction, int[] abnormal) {
        int n = balance.length;
        Stack<Money>[] monies = new Stack[n + 1];

        for (int i = 1; i <= n; i++) {
            monies[i] = new Stack<>();
            monies[i].add(new Money(i, balance[i - 1]));
        }

        for (int[] t : transaction) {
            int from = t[0];
            int to = t[1];
            int amount = t[2];

            while (amount > 0) {
                if (monies[from].peek().amount > amount) {
                    monies[from].peek().amount -= amount;
                    monies[to].add(new Money(monies[from].peek().idx, amount));
                    amount = 0;
                } else if (monies[from].peek().amount == amount) {
                    monies[to].add(new Money(monies[from].peek().idx, amount));
                    monies[from].pop();
                    amount = 0;
                } else {
                    monies[to].add(new Money(monies[from].peek().idx, monies[from].peek().amount));
                    amount -= monies[from].peek().amount;
                    monies[from].pop();
                }
            }
        }

        List<Integer> abnor = Arrays.stream(abnormal).boxed().collect(Collectors.toList());
        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            while (!monies[i].isEmpty()) {
                Money money = monies[i].pop();
                if (abnor.contains(money.idx)) {
                    continue;
                }
                sum += money.amount;
            }

            answer.add(sum);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    void test() {
        assertArrayEquals(new int[]{0, 20, 15, 55}, solution(new int[]{30, 30, 30, 30}, new int[][]{{1, 2, 10}, {2, 3, 20}, {3, 4, 5}, {3, 4, 30}}, new int[]{1}));
    }
}
