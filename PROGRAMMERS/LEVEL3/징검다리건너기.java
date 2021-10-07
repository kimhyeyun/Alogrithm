public class 징검다리건너기 {
    public static int solution(int[] stones, int k) {
        int answer = 0;

        int min = 1;
        int max = 200000000;

        while(min <= max){
            int mid = (min+max)/2;

            if(canCrossStone(stones, k, mid)){
                min = mid + 1;
                answer = Math.max(answer, mid);
            }
            else
                max = mid - 1;
        }

        return answer;
    }

    private static boolean canCrossStone(int[] stones, int k, int mid) {

        int skip = 0;
        for(int s : stones){
            if(s - mid < 0)
                skip++;
            else
                skip = 0;
                
            if(skip == k)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        System.out.println(solution(stones, 3));
    }
}
