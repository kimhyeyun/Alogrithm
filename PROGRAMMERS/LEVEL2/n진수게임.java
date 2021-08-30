public class n진수게임 {

    public static String solution(int n, int t, int m, int p) {
        String answer = "";
        int cnt = 1;

        int i = 0;
        String toN = "";
        while(toN.length() < t*m){
            toN += Integer.toString(i, n);
            i++;
        }
        
        for(int l = 0 ; l < t; l++){
            answer += toN.charAt(p-1);
            p += m;
        }
        
        return answer.toUpperCase();
    }
    public static void main(String[] args) {
        System.out.println(solution(2,4,2,1));
    }
}
