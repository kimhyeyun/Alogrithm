public class L1_하샤드_수 {
    public boolean solution(int x) {
        int sum = 0, num = x;
        while (num != 0) {
            sum += (num % 10);
            num /= 10;
        }

        if(x % sum == 0) return true;
        else return false;
    }
}
