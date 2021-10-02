package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class 부품대여장 {
    static int[] month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    static class timebyPart{
        String part;
        int time;

        timebyPart(String part, int time){
            this.part = part;
            this.time = time;
        }
    }

    static class finebyId implements Comparable<finebyId>{
        String id;
        int fine;

        finebyId(String id, int fine){
            this.id = id;
            this.fine = fine;
        }
        @Override
        public int compareTo(finebyId o) {
            return id.compareTo(o.id);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " /:");

        // Map<String, Map<timebyPart>> map = new HashMap<>();
        List<finebyId> fees = new LinkedList<>();

        int N = Integer.parseInt(stringTokenizer.nextToken());

        int rentTime = Integer.parseInt(stringTokenizer.nextToken()) * 1440;;
        rentTime += Integer.parseInt(stringTokenizer.nextToken()) * 60;
        rentTime += Integer.parseInt(stringTokenizer.nextToken());

        int fine = Integer.parseInt(stringTokenizer.nextToken());


        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine(), " -:");
            stringTokenizer.nextToken();
            
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int day = Integer.parseInt(stringTokenizer.nextToken());
            int hour = Integer.parseInt(stringTokenizer.nextToken());
            int minute = Integer.parseInt(stringTokenizer.nextToken());
            String part = stringTokenizer.nextToken();
            String id = stringTokenizer.nextToken();

            int totalTime = minute + hour*60 + (day + month[m])*1440;

/*             if(map.containsKey(id)){
                TreeSet<timebyPart> tmp = map.get(id);

                if(){
                    int dif = totalTime - tmp.time;

                    if(dif > rentTime){
                        int fineEach = (dif - rentTime) * fine;
                        fees.add(new finebyId(id, fineEach));
                    }

                    map.remove(id);
                }
            } */
        }



        
    }
}

// 잠정 중단;;;;;