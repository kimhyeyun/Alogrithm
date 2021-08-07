public class 주식가격 {
    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for(int i = 0; i < prices.length; i++){
            int p = prices[i];
            int cnt = 0;

            for(int j = i+1; j < prices.length; j++){
                if(prices[j] >= p)
                    cnt++;
                else{
                    cnt++;
                    break;
                }
            }
            answer[i] = cnt;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] p = {1,2,3,2,3};
        int[] ans = solution(p);

        for(int a : ans){
            System.out.println(a);
        }
    }
}
