public class L1_문자열_내_p와_y의_개수 {
    boolean solution(String s) {
        s = s.toLowerCase();

        int p = 0;
        int y = 0;

        for (char c : s.toCharArray()) {
            if(c == 'p') p += 1;
            else if(c == 'y') y += 1;
        }

        if(p == y) return true;
        return false;
    }
}
