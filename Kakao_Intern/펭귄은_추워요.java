import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 펭귄은_추워요 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        Long[] penguins = new Long[3];
        for (int i = 0; i < 3; i++) {
            penguins[i] = Long.parseLong(stringTokenizer.nextToken());
        }

        Arrays.sort(penguins);

        long min = penguins[0];
        long center = penguins[1];
        long max = penguins[2];

        long minAns = 0;
        long fromMax, fromMin;
        while (true) {
            fromMax = max - center - 1;
            fromMin = center - min - 1;

            if(fromMax == 0 && fromMin == 0) break;

            if (fromMax == 1 || fromMin == 1) {
                minAns += 1;
                break;
            }

            if(fromMax == 0){
                long tmp = center - 2;
                max = center;
                center = tmp;
            } else if (fromMin == 0) {
                long tmp = center + 2;
                min = center;
                center = tmp;
            }
            else {
                if (fromMax < fromMin) {
                    long tmp = center + 2;
                    min = center;
                    center = tmp;
                } else {
                    long tmp = center - 2;
                    max = center;
                    center = tmp;
                }
            }
            minAns += 1;
        }

        min = penguins[0];
        center = penguins[1];
        max = penguins[2];


        long maxAns = 0;
        while (true) {
            fromMax = max - center - 1;
            fromMin = center - min - 1;

            if (fromMax == 0 && fromMin == 0) break;

            if (fromMax < fromMin) {
                long tmp = center - 1;
                max = center;
                center = tmp;
            } else {
                long tmp = center + 1;
                min = center;
                center = tmp;
            }
            maxAns += 1;
        }

        System.out.println(minAns);
        System.out.println(maxAns);

    }


}
