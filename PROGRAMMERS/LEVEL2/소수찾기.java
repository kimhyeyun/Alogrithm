import java.util.Arrays;

public class 소수찾기 {
    static boolean isPrime[];

    public static int solution(String numbers){
        int answer = 0;

        char[] arr = numbers.toCharArray();
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder(String.valueOf(arr));
        sb.reverse();

        numbers = sb.toString();

        int maxNum = Integer.parseInt(numbers);

        Eratos(maxNum);

        for(int i = 2; i <= maxNum; i++){
            if(isPrime[i] && isInclude(i, numbers)){
                answer++;
            }
        }

        return answer;
    }
    
    private static boolean isInclude(int num, String str) {

        String numStr = String.valueOf(num);
        char[] strArr = str.toCharArray();

        boolean flag = false;

        for(int i = 0; i < numStr.length(); i++){
            flag = false;
            
            for(int j = 0; j < strArr.length; j++){
                if(numStr.charAt(i) == strArr[j]){
                    strArr[j] = ' ';
                    flag = true;
                    break;
                }
            }
            
            if(!flag){
                return false;
            }
        }

        return true;
    }

    private static void Eratos(int maxNum) {
        isPrime = new boolean[maxNum+1];

        isPrime[0] = false;
        isPrime[1] = false;

        for(int i = 2; i <= maxNum; i++){
            isPrime[i] = true;
        }

        for(int i = 2; i * i <= maxNum; i++){
            if(isPrime[i]){
                for(int j = i * i; j <= maxNum; j+=i){
                    isPrime[j] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution("011"));
    }
}
