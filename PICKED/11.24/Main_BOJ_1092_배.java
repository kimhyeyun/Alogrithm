import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1092_배 {

    static int N, M;
    static List<Integer> cranes, boxes;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        cranes = new ArrayList<>();
        boxes = new ArrayList<>();

        N = Integer.parseInt(br.readLine());

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ;i++){
            cranes.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        M = Integer.parseInt(br.readLine());

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++){
            boxes.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        Collections.sort(cranes, Collections.reverseOrder());
        Collections.sort(boxes, Collections.reverseOrder());

        if(cranes.get(0) < boxes.get(0))
            System.out.println(-1);
        else{
            int time = 0;
            while(!boxes.isEmpty()){
                int idx = 0;
                for(int i = 0 ; i < cranes.size() ; i++){
                    if(idx == boxes.size()) break;
                    else if(cranes.get(i) >= boxes.get(idx))
                        boxes.remove(idx);
                    else{
                        idx++;
                        i--;
                    }
                }
                time++;
            }
            System.out.println(time);
        }

    }
}
