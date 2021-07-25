public class 수박수박수박수박수 {
    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    public static String solution(int n) {
        String answer = "";

        for (int i = 1; i <= n; i++) {
            switch (i % 2) {
                case 0:
                    answer += '박';
                    break;
                case 1:
                    answer += '수';
                    break;
            }
        }

        return answer;
    }

    // 다른 사람 풀이
    public String another(int n) {
        String text = "";
        for (int i = 0; i < 5000; i++) {
            text += "수박";
        }

        String answer = text.substring(0,n);
        return answer;
    }
}
