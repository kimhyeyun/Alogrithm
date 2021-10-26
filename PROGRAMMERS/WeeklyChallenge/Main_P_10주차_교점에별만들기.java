import java.util.ArrayList;
import java.util.List;

public class Main_P_10주차_교점에별만들기 {

    static class coordinate{
        int x;
        int y;

        coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int startX = Integer.MAX_VALUE;
    static int startY = Integer.MAX_VALUE;
    static int endX = Integer.MIN_VALUE;
    static int endY = Integer.MIN_VALUE;

    public static String[] solution(int[][] line) {
        String[] answer = {};

        List<coordinate> cList = new ArrayList<>();
        
        for(int i = 0 ; i < line.length ; i++){
            for(int j = i+1; j < line.length ; j++){
                long a = line[i][0];
                long b = line[i][1];
                long e = line[i][2];

                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];

                long denominator = (a*d) - (b*c);
                if(denominator == 0) continue;

                long integerVerification1 = (b*f) - (e*d);
                long integerVerification2 = (e*c) - (a*f);
                if(integerVerification1 % denominator != 0 || integerVerification2 % denominator != 0)  continue;

                coordinate coord = new coordinate((int)(((b*f) - (e*d))/denominator), (int)(((e*c) - (a*f))/denominator));
                if(!cList.contains(coord))
                    cList.add(coord);

                startX = Math.min(startX, coord.x);
                startY = Math.min(startY, coord.y);
                endX = Math.max(endX, coord.x);
                endY = Math.max(endY, coord.y);
            }
        }

        // System.out.println(startX + " " + startY + " " + endX + " " + endY);

        ArrayList<String> board = new ArrayList<>();
        for(int i = startY; i <= endY; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = startX; j <= endX ; j++)
                sb.append(".");

            board.add(sb.toString());
        }

        for(coordinate c : cList){
            int x = Math.abs(c.x - startX);
            int y = Math.abs(c.y - startY);

            // System.out.println(x + " " + y);

            StringBuilder sb = new StringBuilder(board.get(y));
            board.remove(y);
            sb.setCharAt(x, '*');

            board.add(y, sb.toString());

        }
       
        answer = new String[board.size()];
        for(int i = 0 ; i < board.size() ; i++){
            answer[i] = board.get(board.size()-i-1);
        }
            

        return answer;
    }

    public static void main(String[] args) {
        int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0,-1,1}, {5,-8,-12},{5,8,12}};
        // int[][] line = {{0,1,-1}, {1,0,-1}, {1,0,1}};
        String[] answer = solution(line);
        
        for(String a : answer)
            System.out.println(a);
    }
}
