public class L1_자연수_뒤집어_배열로_만들기 {
    public int[] solution(long n) {

        StringBuilder sb = new StringBuilder(String.valueOf(n));
        String str = sb.reverse().toString();

        int[] answer = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            answer[i] = (int) str.charAt(i) - '0';
        }

        return answer;
    }
}
