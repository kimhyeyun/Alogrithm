public class 점_찍기 {

    public long solution(int k, int d) {
        long answer = 0;

        for (int x = 0; x <= d; x += k) {
            int yMaxDist = yPossibleDist(x, d);
            answer += yPossibleCount(yMaxDist, k);
        }
        return answer;
    }

    private long yPossibleCount(int possible, int k) {
        int y = possible / k;
        return y + 1;
    }

    private int yPossibleDist(int x, int d) {
        long xx = (long) Math.pow(x, 2);
        long dd = (long) Math.pow(d, 2);

        int y = (int) Math.sqrt(dd - xx);

        return y;
    }

}
