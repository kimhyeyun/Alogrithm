public class 금과은운반하기 {
    public static long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = Long.MAX_VALUE;

        long start = 0;
        long end = Long.MAX_VALUE;

        while(start <= end){
            long mid = (start + end) / 2;

            long goldCarry = 0;
            long silverCarry = 0;
            long addCarry = 0;
            
            for(int i = 0 ; i < s.length; i++){
                long nowGold = (long)g[i];
                long nowSilver = (long)s[i];
                long nowWeight = (long)w[i];
                long nowTime = (long)t[i];

                long moveCount = mid / (nowTime*2);
                if(mid % (nowTime*2) >= t[i]) // 편도로 한번 더 갈 수 있다면
                    moveCount++;

                goldCarry += (nowGold < moveCount * nowWeight) ? nowGold : moveCount * nowWeight; 
            }
        }


        return answer;
    }
}
