public class 양궁대회 {

    private int[] lion, answer;
    private int max = Integer.MIN_VALUE;
    
    public int[] solution(int n, int[] info) {
        lion = new int[11];
        answer = new int[11];

        DFS(info, 0, n);

        if(max == Integer.MIN_VALUE) return new int[]{-1};
        return answer;
    }

    private void DFS(int[] apeach, int count, int n) {
        if (count == n) {
            int pointOfApeach = 0, pointOfLion = 0;

            for (int i = 0; i < 11; i++) {
                if(apeach[i] == 0 && lion[i] == 0) continue;
                if(apeach[i] < lion[i]) pointOfLion += 10 - i;
                else pointOfApeach += 10 - i;
            }

            if (pointOfLion > pointOfApeach) {
                if (max <= pointOfLion - pointOfApeach) {
                    max = pointOfLion - pointOfApeach;
                    System.arraycopy(lion, 0, answer, 0, 11);
                }
            }
            return;
        }

        for (int i = 0; i < 11 && lion[i] <= apeach[i]; i++) {
            lion[i] += 1;
            DFS(apeach, count + 1, n);
            lion[i] -= 1;
        }
    }
}
