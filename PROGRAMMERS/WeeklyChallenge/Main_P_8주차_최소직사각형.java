import java.util.ArrayList;
import java.util.Collections;

public class Main_P_8주차_최소직사각형 {
    public static int solution(int[][] sizes) {
        int answer = 0;

        ArrayList<Integer> longLength = new ArrayList<>();
        ArrayList<Integer> shortLength = new ArrayList<>();

        for(int[] s : sizes){
            longLength.add(Math.max(s[0], s[1]));
            shortLength.add(Math.min(s[0], s[1]));
        }

        Collections.sort(longLength);
        Collections.sort(shortLength);

        return longLength.get(longLength.size()-1) * shortLength.get(shortLength.size() - 1);
    }

    public static void main(String[] args) {
        int[][] sizes = {{14,4}, {19,6}, {6,16}, {18,7}, {7,11}};
        System.out.println(solution(sizes));
    }
}
