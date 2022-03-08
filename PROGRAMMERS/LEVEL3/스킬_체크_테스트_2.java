public class 스킬_체크_테스트_2 {
    public static int solution(int[] a) {
        int answer = 0;
        if(a.length == 1) return 1;

        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];

        int l = a[0];
        int r = a[a.length - 1];
        for (int i = 1; i < a.length; i++) {
            leftMin[i] = l;
            if(a[i] < l) l = a[i];
        }
        for (int i = a.length - 1; i >= 0; i--) {
            rightMin[i] = r;
            if(a[i] < r) r = a[i];
        }

        answer = 2;
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] > leftMin[i] && a[i] > rightMin[i]) continue;
            answer += 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] a = {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};
        System.out.println(solution(a));
    }
}
