public class L1_나머지가_1이_되는_수_찾기 {
    public int solution(int n) {
        int ans = 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 1){
                ans = i;
                break;
            }
        }
        return ans;
    }
    public static void main(String[] args) {

    }
}
