import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 당근훔쳐먹기{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int T = Integer.parseInt(stringTokenizer.nextToken());

        List<carrot> carrots = new LinkedList<>();

        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            
            long w = Integer.parseInt(stringTokenizer.nextToken());
            long p = Integer.parseInt(stringTokenizer.nextToken());

            carrots.add(new carrot(w,p));
        }

        Collections.sort(carrots);

        long sum = 0;
        int day = T-1;

        for(carrot c : carrots){
            sum += c.p*day + c.w;
            day--;    
        }

        System.out.println(sum);
    }

    public static class carrot implements Comparable<carrot>{
        long w;
        long p;

        carrot(long w, long p){
            this.w = w;
            this.p = p;
        }

        @Override
        public int compareTo(carrot o) {
            if(o.p - p == 0)
                return (int) (o.w - w);
            else
                return (int) (o.p - p);
        }

    }
}