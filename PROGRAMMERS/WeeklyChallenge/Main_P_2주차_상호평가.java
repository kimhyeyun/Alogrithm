public class Main_P_2주차_상호평가 {
    public static String solution(int[][] scores) {
        String answer = "";

        int rowLen = scores.length;
        int colLen = scores[0].length;

        for(int col = 0 ; col < colLen; col++){
            boolean bestFlag = true;
            boolean lowestFlag = true;
            int myScore = scores[col][col];
            int sum = 0;
            int cnt = 0;
            for(int row = 0; row < rowLen ; row++){
                if(col == row)
                    continue;
                
                if(myScore <= scores[row][col])
                    bestFlag = false;
                if(myScore >= scores[row][col])
                    lowestFlag = false;
                
                sum += scores[row][col];
                cnt++;
            }

            if(!bestFlag && !lowestFlag){
                sum += myScore;
                cnt++;
            }

            if(sum/cnt >= 90)
                answer += "A";
            else if(sum/cnt >= 80)
                answer += "B";
            else if(sum/cnt >= 70)
                answer += "C";
            else if(sum/cnt >= 50)
                answer += "D";
            else 
                answer += "F";
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] scores = {{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}};
        // int[][] scores = {{50, 90}, {50, 87}};

        System.out.println(solution(scores));
    }
}
