public class L1_문자열_다루기_기본 {
       public static boolean solution(String s) {
                if(s.length() != 4 && s.length() != 6) return false;
                if (s.matches(".*[a-zA-Z].*")) return false;
                return true;
        }

        public static void main(String[] args) {
                System.out.println(solution("1234"));

        }
}
