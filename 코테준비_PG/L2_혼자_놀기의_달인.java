public class L2_혼자_놀기의_달인 {
    static boolean[] isOpened;
    static int[] cnt = new int[2];
    public static int solution(int[] cards) {
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < cards.length; i++) {
            cnt[0] = 0;
            isOpened = new boolean[cards.length];
            openBox(cards, i, 1);
            for (int j = 0; j < cards.length; j++) {
                cnt[1] = 0;
                openBox(cards, j, 2);
                answer = Math.max(cnt[0]*cnt[1], answer);
            }
        }
        return answer;
    }

    private static void openBox(int[] cards, int idx, int num) {
        if(isOpened[idx]) return;

        isOpened[idx] = true;
        cnt[num - 1] += 1;
        openBox(cards, cards[idx] - 1, num);

        return;
    }

    public static void main(String[] args) {
        int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};

        System.out.println(solution(cards));

    }
}
