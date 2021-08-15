public class 점프와순간이동 {

    // 내풀이인데 이유는 모름....
    public static int solution(int n) {

        String binaryStr = Integer.toBinaryString(n);
        binaryStr = binaryStr.replace("0", "");

        return binaryStr.length();
    }

    // 다른 플이 -> 이게 정석 풀이
    public static int OtherSolution(int n){
        int answer = 0;

        while(n != 0){
            if(n%2 == 0){
                n /= 2;
            }
            else{
                n--;
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(5000));
    }

}