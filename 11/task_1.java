public class task_1 {
    public static void main(String[] args) {
        System.out.println(solution(53,1953786));
    }
    public static int solution(int A, int B) {
        // write your code in Java SE 8

        String a = String.valueOf(A);
        String b = String.valueOf(B);

        int idx = 0;
        boolean flag = false;
        for (int i = 0; i < a.length(); i++) {
            char aChar = a.charAt(i);
            for (int j = 0; j < b.length(); j++) {
                if (aChar == b.charAt(j)) {
                    idx = j;
                    flag = true;
                    break;
                }
            }
            if(!flag) break;
        }

        if(!flag) return -1;
        return idx - a.length() + 1;
    }
}
