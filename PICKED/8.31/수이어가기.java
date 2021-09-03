import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 수이어가기 {

    public static void main(String[] args) throws NumberFormatException, IOException {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Integer> result = new ArrayList<>();

        for(int i = N ; i >= 0 ; i--){
            List<Integer> tmp = new ArrayList<>();
            tmp.add(N);
            tmp.add(i);

            while(true){
                int first = tmp.get(tmp.size()-2);
                int second = tmp.get(tmp.size()-1);

                if(first - second < 0){
                    if(result.size() < tmp.size()){
                        result = tmp;
                    }
                    break;
                }

                tmp.add(first-second);
            }
        }

        sb.append(result.size()).append("\n");
        for(int a : result){
            sb.append(a + " ");
        }

        System.out.println(sb);
    }
}
