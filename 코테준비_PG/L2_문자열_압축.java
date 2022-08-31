public class L2_문자열_압축 {
    public static int solution(String s) {
        int answer = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            String target = s.substring(0, i); // 타켓문자
            String cur = "";     //현재 문자열
            int cnt = 1;        //압축 카운트
            StringBuilder sb = new StringBuilder();

            for (int start = i; start <= s.length(); start += i) {
                if(start + i >= s.length()) cur = s.substring(start);
                else cur = s.substring(start, start + i);

                if(cur.equals(target)) cnt += 1;
                else if (cnt == 1) {
                    sb.append(target);
                    target = cur;
                } else {
                    sb.append(cnt).append(target);
                    target = cur;
                    cnt = 1;
                }
            }
            if(i != target.length()) sb.append(target);

            answer = Math.min(answer, sb.toString().length());
        }

        return answer;
    }
}
