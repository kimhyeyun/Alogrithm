public class Main_P_1주차_부족한금액계산하기 {
    public static long solution(int price, int money, int count) {
        long answer = -1;
        long sum = 0;

        for(int i = 1 ; i <= count; i++){
            sum += price * i;    
        }

        if(sum <= money)
            return 0;
            
        return sum - money;
    }
    public static void main(String[] args) {
        System.out.println(solution(3, 20, 4));
    }
}
