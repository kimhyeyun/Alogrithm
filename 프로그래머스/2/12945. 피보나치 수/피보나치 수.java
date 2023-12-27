class Solution {
    static int[] fNum;
    static int mod = 1234567;

    public static int solution(int n) {
        int answer = 0;
        
        fNum = new int[n+1];
        fNum[0] = 0;
        fNum[1] = 1;

        for(int i = 2; i <= n; i++){
            fNum[i] = (fNum[i-2] + fNum[i-1]) % mod;
        }

        answer = fNum[n];

        return answer;
    }
}