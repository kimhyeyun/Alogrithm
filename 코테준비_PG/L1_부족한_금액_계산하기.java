public class L1_부족한_금액_계산하기 {
    public static long solution(int price, int money, int count) {
        long ans = 0L;

        for (int i = 1; i <= count; i++) {
            ans += (price * i);
        }

        if(ans <= money) return 0;
        return ans - money;
    }
}
