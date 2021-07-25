public class 타켓넘버 {
    static int answer;
    public static void main(String[] args) {
        int[] n = {1,1,1,1,1};
        System.out.println(solution(n,3));
    }

    public static int solution(int[] numbers, int target) {
        answer = 0;

        DFS(numbers, target, 0, 0);

        return answer;
    }

    private static void DFS(int[] numbers, int target, int sum, int idx) {
        if(idx == numbers.length){
            if(sum == target){
                answer++;
            }
            return;
        }
        DFS(numbers, target, sum + numbers[idx], idx+1);
        DFS(numbers, target, sum - numbers[idx], idx+1);
    }
}
