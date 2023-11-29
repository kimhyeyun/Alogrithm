import java.util.*;

public class solution_1 {
    //success
    public int solution(int[] winnum, int bonus, int[] usernum) {
        int count = 0;
        boolean isBonus = false;

        List<Integer> win = new ArrayList<>();
        for (int i = 0; i < winnum.length; i++) {
            win.add(winnum[i]);
        }

        for (int user : usernum) {
            if (win.contains(user)) count += 1;
            else if (user == bonus) isBonus = true;
        }

        if (count == 6) return 1;
        else if (count == 5 && isBonus) return 2;
        else if (count == 5) return 3;
        else if (count == 4) return 4;
        else if (count == 3) return 5;
        return -1;
    }

    public static boolean solution(String s, String cipher) {
        Map<Character, Character> encode = new HashMap<>();//s->cipher
        Map<Character, Character> decode = new HashMap<>();//cipher -> s
        int size = s.length();

        for (int i = 0; i < size; i++) {
            if (encode.containsKey(s.charAt(i)) && encode.get(s.charAt(i)) != cipher.charAt(i)) {
                return false;
            }
            if (decode.containsKey(cipher.charAt(i)) && decode.get(cipher.charAt(i)) != s.charAt(i)) {
                return false;
            }
            if (encode.containsKey(s.charAt(i)) && decode.containsKey(cipher.charAt(i))) continue;

            encode.put(s.charAt(i), cipher.charAt(i));
            decode.put(cipher.charAt(i), s.charAt(i));
        }

//
//        int size = encode.size();
//        int distinctSize = (int) encode.values().stream().distinct().count();
//
//        if(size == distinctSize) return true;
//        return false;

        return true;

    }

    //success
    public static int[] solution(int[] numbers, int[] start, int[] finish) {
        int[] dp = new int[numbers.length + 1];

        dp[0] = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            dp[i] = dp[i - 1] + numbers[i];
        }

        int[] answer = new int[start.length];
        for (int i = 0; i < start.length; i++) {
            if (start[i] == 0) {
                answer[i] = dp[finish[i]];
            } else {
                answer[i] = (dp[finish[i]] - dp[start[i] - 1]);
            }
        }

        return answer;
    }

    public static int solution(int[] a, int[] b, int duration, int distance) {
        if (a[1] < b[0]) return -1;
        if (b[1] < a[0]) return -1;

        if (a[0] == b[0] && a[1] == b[1]) {
            int dist = distance / 2;
            int totalTime = a[0] + dist + duration + dist;
            if (totalTime <= a[1]) {
                return a[0] + dist;
            } else {
                return -1;
            }
        }

        int aStartTime = a[0];
        int bStartTime = b[0];
        int minIdx = 0;
        if (aStartTime > bStartTime) {
            minIdx = 1;
        }

        for (int i = 0; i <= distance; i++) {
            int ADestTime = aStartTime + i;
            int BDestTime = bStartTime + distance - i;

            if (minIdx == 0) {
                ADestTime = aStartTime + distance - i;
                BDestTime = bStartTime + i;
            }
            while (ADestTime <= a[1]/2 || BDestTime <= b[1]/2) {
                if (ADestTime == BDestTime) {
                    int totalATime = ADestTime + duration + i;
                    int totalBTime = BDestTime + duration + distance - i;

                    if (minIdx == 0) {
                        totalATime = ADestTime + duration + distance - i;
                        totalBTime = BDestTime + duration + i;
                    }

                    if (totalATime <= a[1] && totalBTime <= b[1]) {
                        return ADestTime;
                    }
                } else if (BDestTime < aStartTime) {
                    bStartTime += 1;
                    break;
                }
            }
//            if (minIdx == 0) {
//                aStartTime += 1;
//            } else {
//                bStartTime += 1;
//            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        System.out.println(solution(new int[]{1, 4}, new int[]{1, 4}, 1, 2));

        Thread_Task thread_task1 = new Thread_Task();
        Thread_Task thread_task2 = new Thread_Task();
        Thread_Task thread_task3 = new Thread_Task();
        Thread_Task thread_task4 = new Thread_Task();
        Thread_Task thread_task5 = new Thread_Task();

        for (int i = 0; i < 100; i++) {
            thread_task1.run();
        }


    }

    static class Thread_Task extends Thread {
        int j = 0;

        public void run() {
            j += 1;
            System.out.println(j);
        }
    }

}
