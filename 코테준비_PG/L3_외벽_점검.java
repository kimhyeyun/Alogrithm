public class L3_외벽_점검 {
    static int[][] weakCase;
    static int answer;
    public static int solution(int n, int[] weak, int[] dist) {
        answer = dist.length + 1;

        weakCase = new int[weak.length][weak.length];

        makeWeakCase(n, weak);
        makeDistCase(new boolean[dist.length], new int[dist.length], dist, 0);
        return answer;
    }

    private static void makeDistCase(boolean[] isPicked, int[] distCase, int[] dist, int idx) {
        if (idx == dist.length) {

        }

        for (int i = 0; i < dist.length; i++) {

        }
    }

    private static void makeWeakCase(int n, int[] weak) {
        int[] weakTmp = weak.clone();
        weakCase[0] = weakTmp.clone();

        for (int i = 1; i < weak.length; i++) {
            int tmp = weakTmp[0];
            for (int j = 1; j < weak.length; j++) {
                weakTmp[j - 1] = weakTmp[j];
            }
            weakTmp[weak.length - 1] = tmp + n;
            weakCase[i] = weakTmp.clone();
        }
    }
}
