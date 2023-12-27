public class 문자열_나누기 {
    public static int solution(String s) {
        int answer = 0;

        char x = s.charAt(0);
        int same = 1, diff = 0;

        int index = 1;
        while (true) {
            if (index == s.length() && index > 0) {
                answer += 1;
                break;
            }

            if (s.charAt(index++) == x) same += 1;
            else diff += 1;

            System.out.println(same + " " + diff);
            if (same == diff) {
                answer += 1;
                s = s.substring(index);
                if (s.length() > 0) {
                    x = s.charAt(0);
                    index = 1;
                    same = 1;
                    diff = 0;
                }else break;
                System.out.println(s);
                System.out.println(x);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("banana"));
    }
}
