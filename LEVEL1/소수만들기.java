public class 소수만들기 {
    public static void main(String[] args) {
        int n[] = {1,2,7,6,4};
        System.out.println(solution(n));
    }

    public static int solution(int[] nums) {
        int answer = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        //소수 여부에 대한 flag
        boolean flag = false;

        // 중복되지 않게 세개 선택
        for(int i=0;i<nums.length;i++){
            one = nums[i];
            for(int j=i+1;j<nums.length;j++){
                two = nums[j];
                for(int k=j+1;k<nums.length;k++){
                    flag = false;
                    three = nums[k];
                    int sum = one+two+three;
                    //소수인지 확인
                    for(int f=2;f<sum;f++){
                        if(sum%f == 0){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        answer++;
                    }
                }
            }

        }

        return answer;
    }
    
}
