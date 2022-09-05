import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class L2_튜플 {
    public static int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        s = s.replace("},{", "-");

        String[] str = s.split("-");

        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        List<Integer> answer = new ArrayList<>();
        for (String a : str) {
            String[] t = a.split(",");

            for (int i = 0; i < t.length; i++) {
                int num = Integer.parseInt(t[i]);

                if(!answer.contains(num)) answer.add(num);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        System.out.println(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"));
    }
}
