import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_P_6주차_복사정렬하기 {

    static class player implements Comparable<player>{
        double winRate;
        int winNum;
        int weight;
        int num;

        player(double winRate, int winNum, int weight, int num){
            this.winRate = winRate;
            this.winNum = winNum;
            this.weight = weight;
            this.num = num;
        }

        @Override
        public int compareTo(player o) {
            // TODO Auto-generated method stub
            int result = (int)((o.winRate - this.winRate)*1000000);

            if(result == 0)
                result = o.winNum - this.winNum;
            
            if(result == 0)
                result = o.weight - this.weight;
            
            if(result == 0)
                result = this.num - o.num;
            
            return result;
        }
        

    @Override
    public String toString() {
        return "{" + this.winRate + " " + this.winNum + " " + this.weight + " " + this.num +
            "}";
    }
        
    }
    
    public static int[] solution(int[] weights, String[] head2head) {
        int boxerNum= weights.length;
        int[] answer = new int[boxerNum];
        List<player> plList = new ArrayList<>();

        for(int i = 0 ; i < head2head.length ; i++){
            double win = 0, cnt = 0;
            int heavyWin = 0;
            for(int j = 0 ; j <head2head[i].length(); j++){
                if(i == j || head2head[i].charAt(j) == 'N')
                    continue;
                if(head2head[i].charAt(j) == 'W'){
                    win++;
                    if(weights[i] < weights[j])
                        heavyWin++;
                }
                cnt++;
            }
            plList.add(new player(win/cnt, heavyWin, weights[i], i+1));
        }

        Collections.sort(plList);
        int idx = 0;
        for(player p : plList)
            answer[idx++] = p.num;

        return answer;
    }
 
    public static void main(String[] args) {
        int[] weights = {50,82,75,120};
        String[] head2head = {"NLWL","WNLL","LWNW","WWLN"};

        System.out.println(solution(weights, head2head));
    }
}
