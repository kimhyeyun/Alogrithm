import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Main_BOJ_2109_순회강연{

    static class lecture implements Comparable<lecture>{
        int pay;
        int day;

        lecture(int pay, int day){
            this.pay = pay;
            this.day = day;
        }

        @Override
        public int compareTo(lecture o) {
            // TODO Auto-generated method stub
            int result = o.pay - this.pay;

            if(result == 0)
                result = this.day - o.day;

            return result;
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int n = Integer.parseInt(br.readLine());
        
        // d일 안에 강연을 하면됌!!
       /*  Map<Integer, Integer> univ = new HashMap<>();

        while(n-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());

            if(univ.containsKey(d)){
                int p1 = univ.get(d);
                if(p1 < p)
                    univ.put(d, p);
                else
                    continue;
            }
            
            else
                univ.put(d, p);
        }

        long answer = 0;
        Set<Integer> kSet = univ.keySet();
        for(int k : kSet){
            answer += univ.get(k);
        } 
        System.out.println(answer); */

        boolean[] days = new boolean[10001];
        List<lecture> list = new ArrayList<>();
        while(n-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());

            list.add(new lecture(p, d));
        }

        Collections.sort(list);

        int answer = 0;
        for(lecture l : list){
            // lecture를 금액 내림차순 || 날짜 오름차순으로 했기때문에, 역순으로 하지않으면 나중에 나오는 day가 작은 강연은 들어갈 수 없음
            for(int j = l.day ; j > 0 ; j--){
                if(!days[j]){
                    days[j] = true;
                    answer += l.pay;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}