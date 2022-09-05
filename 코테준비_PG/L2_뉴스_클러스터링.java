import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class L2_뉴스_클러스터링 {
    public int solution(String str1, String str2) {
        List<String> set1 = new ArrayList<>();
        List<String> set2 = new ArrayList<>();

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for (int i = 0; i < str1.length() - 1; i++) {
            char first = str1.charAt(i);
            char second = str1.charAt(i + 1);
            if('z' < first || first < 'a' || 'z' < second || second < 'a') continue;
            set1.add(first + "" + second);
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            char first = str2.charAt(i);
            char second = str2.charAt(i + 1);
            if('z' < first || first < 'a' || 'z' < second || second < 'a') continue;
            set2.add(first + "" + second);
        }

        Collections.sort(set1);
        Collections.sort(set2);

        List<String> union = new ArrayList<>();
        List<String> inter = new ArrayList<>();

        for (String s : set1) {
            if(set2.remove(s)) inter.add(s);
            union.add(s);
        }

        for (String s : set2) union.add(s);

        double jakard = 1;
        if(inter.size() == 0  && union.size() == 0) jakard = 1;
        else jakard = (double) inter.size() / (double) union.size();

        int answer = (int) jakard * 65536;

        return answer;
    }
}
