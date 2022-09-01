public class L2_타겟_넘버 {
    static boolean[] isChoose;
    static int answer;
    public static int solution(int[] numbers, int target) {

        isChoose = new boolean[numbers.length];
        answer = 0;

        DFS(0, 0, numbers, target);

        return answer;
    }

    private static void DFS(int idx, int sum, int[] numbers, int target) {
        if (idx == numbers.length) {
            if(sum == target) answer += 1;
            return;
        }

        DFS(idx + 1, sum + numbers[idx], numbers, target);
        DFS(idx + 1, sum - numbers[idx], numbers, target);
    }

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        System.out.println(solution(numbers, 3 ));
    }
}
