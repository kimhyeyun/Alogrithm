public class 소수찾기 {
    public static void main(String[] args) {
        System.out.println(solution(10));
    }

    public static int solution(int n) {
        int answer = 0;


        // 처음 시도한 코드 -> 시간초과 O(n)
       /*  for(int i = 3;i <= n;i++){
            boolean flag = true;
            for(int j = 2; j<i; j++){
                if(i % j == 0){
                    flag = false;
                    break;
                }
            }
            if(flag)
                answer++;
        } */


        // 에라토노소의 체 사용
        int[] arr = new int[n+1];

        for(int i = 2; i <= n; i++){
            arr[i] = i;
        }

        for(int i = 2;i<=n;i++){
            if(arr[i] == 0)
                continue;
            
            for(int j = i*2; j <= n; j += i){
                if(arr[j] == 0)
                    continue;
                else
                    arr[j] = 0;
            }
        }

        for(int i = 2;i <= n; i++){
            if(arr[i] != 0)
                answer++;
        }
        return answer;
    }
}
