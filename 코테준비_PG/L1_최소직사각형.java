import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class L1_최소직사각형 {
    public static int solution(int[][] sizes) {
        List<Integer> shortPart = new ArrayList<>();
        List<Integer> longPart = new ArrayList<>();

        for (int[] size : sizes) {
            shortPart.add(Math.min(size[0], size[1]));
            longPart.add(Math.max(size[0], size[1]));
        }

        Collections.sort(shortPart);
        Collections.sort(longPart);

        return (shortPart.get(shortPart.size() - 1) * longPart.get(longPart.size() - 1));
    }
}
