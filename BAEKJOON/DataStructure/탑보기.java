import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class 탑보기 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 1; i <= N; i++){
            map.put(i, Integer.parseInt(stringTokenizer.nextToken()));
        }

        Set<Integer> ksIntegers = map.keySet();

        for(int a  : ksIntegers){
            System.out.println(a + " : " + map.get(a));
        }
    }
}
  /* 우선 포기;;; 실ㄹ패ㅐㅐㅐ */