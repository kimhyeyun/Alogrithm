public class L1_다트_게임 {
    public static int solution(String dartResult) {
        int[] scoreOfRound = new int[3];
        int round = 0;

        for (int i = 0; i < dartResult.length(); i++) {
            if ('0' <= dartResult.charAt(i) && dartResult.charAt(i) <= '9') {
                if (dartResult.charAt(i) == '1' && dartResult.charAt(i + 1) == '0') {
                    scoreOfRound[round] = 10;
                    i += 1;
                } else {
                    scoreOfRound[round] = (int) dartResult.charAt(i) - '0';
                }
                round += 1;
            } else if (dartResult.charAt(i) == 'S') {
                scoreOfRound[round - 1] = (int) Math.pow(scoreOfRound[round - 1], 1);
            } else if (dartResult.charAt(i) == 'D') {
                scoreOfRound[round - 1] = (int) Math.pow(scoreOfRound[round - 1], 2);
            } else if (dartResult.charAt(i) == 'T') {
                scoreOfRound[round - 1] = (int) Math.pow(scoreOfRound[round - 1], 3);
            } else if (dartResult.charAt(i) == '*') {
                if (round == 1) {
                    scoreOfRound[round - 1] *= 2;
                } else {
                    scoreOfRound[round - 1] *= 2;
                    scoreOfRound[round - 2] *= 2;
                }
            } else if (dartResult.charAt(i) == '#') {
                scoreOfRound[round - 1] *= -1;
            }
        }

        int answer = scoreOfRound[0] + scoreOfRound[1] + scoreOfRound[2];

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("1D2S0T"));
    }
}
