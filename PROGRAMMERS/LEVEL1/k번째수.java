import java.util.Collections;
import java.util.Vector;

public class k번째수 {
    public static void main(String[] args) {

    }

    public int[] solution(int[] array, int[][] commands) {

        int[] answer = new int[commands.length];
        Vector<Integer> v = new Vector<>();

        for (int c = 0; c < commands.length; c++) {
            int i = commands[c][0];
            int j = commands[c][1];

            for (int a = i - 1; a < j; a++) {
                v.add(array[a]);
            }

            Collections.sort(v);
            answer[c] = v.get(commands[c][2] - 1);
            v.clear();
        }
        return answer;

    }
}
