public class year2016 {
    public static void main(String[] args) {
        System.out.println(solution(5, 24));
    }

    public static String solution(int a, int b) {
        String answer = "";

        // 1월 1일은 금요일
        String[] day = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
        int[] month = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int n = b;
        for(int i=1;i<a;i++)
            n += month[i];
            
        answer = day[n%7];

        return answer;
    }
}
