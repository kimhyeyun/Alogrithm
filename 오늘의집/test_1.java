
public class test_1 {
    public static int solution(int[] arr){
        int mod = 109;

        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[i];
                if(sum % 2 == 1) ans += 1;
            }
        }

        if(ans > 109)
            ans = (ans % mod) + 7;

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5};
        System.out.println(solution(arr));
    }

}
