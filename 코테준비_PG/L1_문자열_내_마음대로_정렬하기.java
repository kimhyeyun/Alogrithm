import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class L1_문자열_내_마음대로_정렬하기 {
    class str implements Comparable<str>{
        String s;
        int n;

        public str(String s, int n) {
            this.s = s;
            this.n = n;
        }

        @Override
        public int compareTo(str o) {
            int result = this.s.substring(n, n + 1).compareTo(o.s.substring(n, n + 1));
            if(result == 0) result = this.s.compareTo(o.s);

            return result;
        }
    }
    public String[] solution(String[] strings, int n) {
        List<str> list = new ArrayList<>();

        for (String s : strings) {
            list.add(new str(s, n));
        }

        Collections.sort(list);
        String[] answer = new String[list.size()];
        for (int i = 0;i< list.size();i++) {
            answer[i] = list.get(i).s;
        }

        return answer;
    }
}
