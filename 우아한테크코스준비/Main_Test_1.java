public class Main_Test_1 {
    public static int[] solution(int[] arr) {
        int[] answer = new int[3];

        int[] cnt = new int[3];

        for(int a : arr){
            cnt[a-1]++;
        }

        int max = Math.max(cnt[0], Math.max(cnt[1], cnt[2]));

        for(int i = 0 ; i < 3; i++){
            answer[i] = max - cnt[i];
        }

        return answer;
    }

   public static void main(String[] args) {
        int[] arr = {2,1,3,1,2,1};
        int[] answer = solution(arr);

    }
}
