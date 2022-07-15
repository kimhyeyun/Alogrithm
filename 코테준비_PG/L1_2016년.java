public class L1_2016년 {
    public static String solution(int a, int b) {
        int[] dayOfMonth = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int sum = b;
        for (int i = 1; i < a; i++) {
            sum += dayOfMonth[i];
        }
        System.out.println(sum%7);
        switch (sum % 7) {
            case 1:
                return "FRI";
            case 2:
                return "SAT";
            case 3:
                return "SUN";
            case 4:
                return "MON";
            case 5:
                return "TUE";
            case 6:
                return "WED";
        }
        return "THU";
    }

    public static void main(String[] args) {
        solution(5, 24);
    }
}
