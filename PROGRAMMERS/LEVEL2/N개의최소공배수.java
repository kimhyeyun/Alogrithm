public class N개의최소공배수 {
    public static int solution(int[] arr) {
        int answer = 0;

        int l = arr[0];
        for(int i = 1; i < arr.length ; i++){
            l = lcm(l, arr[i]);
        }

        answer = l;
        return answer;
    }
    private static int lcm(int l, int i) {
        return (l*i)/gcd(l, i);
    }
    private static int gcd(int l, int i) {
        int a;

        while(i != 0){
            a = l%i;
            l = i;
            i = a;
        }

        return l;
    }
    public static void main(String[] args) {
        int[] arr = {2,6,8,14};
        System.out.println(solution(arr));
    }
}
