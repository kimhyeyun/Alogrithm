import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_BOJ_5766_할아버지는유명해 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder sb = new StringBuilder();

        while(true){
            stringTokenizer = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stringTokenizer.nextToken());
            int M = Integer.parseInt(stringTokenizer.nextToken());

            if(N == 0 && M == 0)
                break;

            player players[] = new player[10001];
            for(int i = 0 ; i < 10001 ; i++){
                players[i] = new player(i, 0);
            }

            for(int i = 0 ; i < N ; i++){
                stringTokenizer = new StringTokenizer(br.readLine());
                for(int j= 0 ; j < M ; j++){
                    int playerNum = Integer.parseInt(stringTokenizer.nextToken());
                    players[playerNum].score++;
                }
            }

            Arrays.sort(players);

            int second = players[1].score;
            for(int i = 1; i < 10001 ; i++){
                if(players[i].score == second){
                    sb.append(players[i].num).append(" ");
                }
            }
          sb.append("\n");
        }

        System.out.println(sb);
    } 

    public static class player implements Comparable<player>{
        int num;
        int score;

        player(int num, int score){
            this.num = num;
            this.score = score;
        }

        @Override
        public int compareTo(player o) {
            // TODO Auto-generated method stub
            int result = o.score - this.score;

            if(result == 0)
                result = this.num - o.num;

            return result;
        }
    }
}
