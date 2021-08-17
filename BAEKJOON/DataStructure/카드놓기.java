import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class 카드놓기 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int passGrp[] = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = N-1; i > -1; i--){
            passGrp[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> card = new LinkedList<>();
        int num = 1;

        for(int a : passGrp){
            switch(a){
                case 1:
                    card.addFirst(num);
                    break;
                case 2:
                    int top = card.pollFirst();
                    card.addFirst(num);
                    card.addFirst(top);
                    break;
                case 3:
                    card.addLast(num);
                    break;
            }
            num++;
        }

        for(int a : card){
            sb.append(a + " ");
        }

        System.out.println(sb);
    }
}
