public class L1_가운데_글자_가져오기 {
    public static String solution(String s) {
        int len = s.length();

        if (len % 2 == 0) {
            return s.substring(len / 2 - 1, len / 2 + 1);
        }

        return s.substring(len / 2, len / 2 + 1);
    }

    public static void main(String[] args) {
        System.out.println(solution("qwer"));
    }
}
