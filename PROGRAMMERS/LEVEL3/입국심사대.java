import java.util.Arrays;

public class 입국심사대 {

    public static long solution(int n, int[] times) {
        long answer = 0;
        long max, human, min, avg;

        Arrays.sort(times);
        min = 1;
        max = times[times.length - 1] * (long) n;
        human = 0;

        while (min <= max) {
            avg = (max + min) / 2;
            for (var t : times)
                human += (avg / t);

            if (n <= human) {
                answer = avg;
                max = avg - 1;
            } else
                min = avg + 1;

            human = 0;
        }

        return answer;

    }

    public static void main(String[] args) {
        int[] times = { 7, 10 };
        System.out.println(solution(6, times));
    }
}
