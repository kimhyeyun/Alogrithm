public class 가장_가까운_같은_글자 {

    public int[] solution(String s) {
        int[] answer = new int[s.length()];

        answer[0] = -1;

        for (int i = 1; i < s.length(); i++) {
            int me = s.charAt(i);

            boolean flag = true;
            for (int j = i - 1; j >= 0; j--) {
                if (me == s.charAt(j)) {
                    answer[i] = i - j;
                    flag = false;
                    break;
                }
            }

            if(flag) answer[i] = -1;
        }

        return answer;
    }

}
