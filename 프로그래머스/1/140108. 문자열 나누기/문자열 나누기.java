class Solution {
    public int solution(String s) {
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

            if (same == diff) {
                answer += 1;
                s = s.substring(index);
                if (s.length() > 0) {
                    x = s.charAt(0);
                    index = 1;
                    same = 1;
                    diff = 0;
                }else break;
            }
        }

        return answer;
    }
}