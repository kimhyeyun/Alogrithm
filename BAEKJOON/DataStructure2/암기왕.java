package DataStructure2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 암기왕 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

   /*      while(testCase-- > 0){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer sTokenizer = new StringTokenizer(br.readLine());
            
            List<Integer> note1 = new LinkedList<>(); 
            List<Integer> note2 = new LinkedList<>(); 
            // HashSet<Integer> hashSet = new HashSet<>(); 시간초과

            while(sTokenizer.hasMoreTokens()){
                note1.add(Integer.parseInt(sTokenizer.nextToken()));
            }
            Collections.sort(note1);
            
            int M = Integer.parseInt(br.readLine());
            sTokenizer = new StringTokenizer(br.readLine());
            while(sTokenizer.hasMoreTokens()){
                note2.add(Integer.parseInt(sTokenizer.nextToken()));
            }

             while(sTokenizer.hasMoreTokens()){
                if(hashSet.contains(Integer.parseInt(sTokenizer.nextToken()))
                    sb.append(1).append("\n");
                else
                    sb.append(0).append("\n");
            } 

            int low = 0;
            int high = note1.size()-1;
            int mid;

            for(int i = 0 ; i < note2.size() ; i++){
                boolean flag = true;

                while(low <= high){
                    mid = (low + high)/2;

                    if(note2.get(i) == note1.get(mid)){
                        sb.append(1).append("\n");
                        flag = false;
                        break;
                    }

                    if(note2.get(i) < note1.get(mid)){
                        high = mid - 1;
                    }
                    else{
                        low = mid + 1;
                    }
                }

                low = 0;
                high = note1.size()-1;

                if(flag)
                    sb.append(0).append("\n");
            }
        }  */   // 시간 초과

        while(testCase-- > 0){
            HashMap<Integer, Boolean> map = new HashMap<>();

            int N = Integer.parseInt(br.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

            for(int i = 0 ; i < N ; i++){
                map.put(Integer.parseInt(stringTokenizer.nextToken()), true);
            }

            int M = Integer.parseInt(br.readLine());
            stringTokenizer = new StringTokenizer(br.readLine());

            for(int i = 0 ; i < M ; i++){
                if(map.containsKey(Integer.parseInt(stringTokenizer.nextToken())))
                    sb.append(1).append("\n");
                else
                    sb.append(0).append("\n");
            }
        }

        System.out.print(sb);

    }
}
