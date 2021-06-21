import java.util.LinkedList;
import java.util.Queue;

class 키패드누르기 {
    public static void main(String[] args) {
        
    }
    public String solution(int[] numbers, String hand) {
         String answer = "";

        int button[] = new int[2];

        button[0] = 10; //왼손 *
        button[1] = 11; //오른손 #

        for(int i=0;i<numbers.length;i++){
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7){
                answer += "L";
                button[0] = numbers[i]; //왼손 위치 변경
            }
            else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9){
                answer += "R";
                button[1] = numbers[i]; //오른손 위치 변경
            }
            else{
                // pair lp = sch(button[0]);
                // pair rp = sch(button[1]);
                // pair end = sch(numbers[i]);

                // int l = Math.abs(lp.x - end.x)+ Math.abs(lp.y - end.y);
                // int r = Math.abs(rp.x - end.x)+ Math.abs(rp.y - end.y);

                int l = BFS(button[0], numbers[i]);
                int r = BFS(button[1], numbers[i]);
                
                if(l < r){
                    answer += "L";
                    button[0] = numbers[i];
                }
                else if(r < l){
                    answer += "R";
                    button[1] = numbers[i];
                }
                else{
                    if(hand.equals("left")){
                        answer += "L";
                        button[0] = numbers[i];
                    }
                    else{
                        answer += "R";
                        button[1] = numbers[i];
                    }
                }
            }
        }

        return answer;
    }

    public static pair sch(int n){
        pair p = new pair(0, 0);
        switch(n){
            case 0:
                p = new pair(3, 1);
                break;
            case 1:
                p = new pair(0, 0);
                break;
            case 2:
               p = new pair(0, 1);
                break;
            case 3:
                p = new pair(0,2);
                break;
            case 4:
                p = new pair(1,0);
                break;
            case 5:
                p = new pair(1, 1);
                break;
            case 6:
                p = new pair(1,2);
                break;
            case 7:
                p = new pair(2,0);
                break;
            case 8:
                p = new pair(2, 1);
                break;
            case 9:
                p = new pair(2,2);
                break;
            case 10:
                p = new pair(3, 0);
                break;
            case 11:
                p = new pair(3, 2);
                break;
        }
        return p;
    }

    public static int BFS(int start, int end){
        int[][] pad = new int[4][3];
        int dx[] = {-1,0,0,1};
        int dy[] = {0,-1,1,0};
        Queue<pair> q  = new LinkedList<>();
        int ex = 0,ey = 0;
        switch(start){
            case 0:
                q.add(new pair(3, 1));
                break;
            case 1:
                q.add(new pair(0,0));
                break;
            case 2:
                q.add(new pair(0,1));
                break;
            case 3:
                q.add(new pair(0,2));
                break;
            case 4:
                q.add(new pair(1,0));
                break;
            case 5:
                q.add(new pair(1,1));;
                break;
            case 6:
                q.add(new pair(1,2));
                break;
            case 7:
                q.add(new pair(2,0));
                break;
            case 8:
                q.add(new pair(2,1));
                break;
            case 9:
                q.add(new pair(2,2));
                break;
            case 10:
                q.add(new pair(3,0));
                break;
            case 11:
                q.add(new pair(3,2));
                break;
        }
        switch(end){
            case 0:
                ex = 3;
                ey = 1;
                break;
            case 1:
                ex = 0;
                ey = 0;
                break;
            case 2:
                ex = 0;
                ey = 1;
                break;
            case 3:
                ex = 0;
                ey = 2;
                break;
            case 4:
                ex = 1;
                ey = 0;
                break;
            case 5:
                ex = 1;
                ey = 1;
                break;
            case 6:
                ex = 1;
                ey = 2;
                break;
            case 7:
                ex = 2;
                ey = 0;
                break;
            case 8:
                ex = 2;
                ey = 1;
                break;
            case 9:
                ex = 2;
                ey = 2;
                break;
            case 10:
                ex = 3;
                ey = 0;
                break;
            case 11:
                ex = 3;
                ey = 2;
                break;
        }

        while(!q.isEmpty()){
            pair p = q.poll();
            int x = p.x;
            int y = p.y;
            

            for(int i=0;i<4;i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(0 <= nx && nx < 4 && 0 <= ny && ny < 3){
                    if(pad[nx][ny] == 0 || pad[nx][ny] > pad[x][y]+1){
                        pad[nx][ny] = pad[x][y]+1;
                        q.add(new pair(nx,ny));
                    }
                }
            }
        }
       return pad[ex][ey];

    }

    static public class pair{
        int x,y ;
        pair(int x,int y){
            this.x = x;
            this.y = y;
        }
    };
}