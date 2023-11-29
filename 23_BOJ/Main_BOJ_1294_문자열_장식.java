import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1294_문자열_장식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] strings = new String[N];
        int len = 0;

        for (int i = 0; i < N; i++) {
            strings[i] = br.readLine();
            len += strings[i].length();
        }

        int[] indexes = new int[N];
        StringBuilder sb = new StringBuilder();

        while (len-- > 0) {
            char min = 'Z' + 1;
            int selectedStrIndex = 0;

            for (int i = 0; i < N; i++) {
                int index = indexes[i];

                if(index == strings[i].length()) continue;
                if(strings[i].charAt(index) < min) {
                    min = strings[i].charAt(index);
                    selectedStrIndex = i;
                } else if (strings[i].charAt(index) == min) {
                    int remainLenA = strings[selectedStrIndex].length() - indexes[selectedStrIndex];
                    int remainLenB = strings[i].length() - indexes[i];

                    int loopLen = Math.min(remainLenA, remainLenB);
                    boolean isNeedLenCompare = false;

                    for (int a = indexes[selectedStrIndex], b = indexes[i]; loopLen-- > 0; a++, b++) {
                        char charA = strings[selectedStrIndex].charAt(a);
                        char charB = strings[i].charAt(b);
                        isNeedLenCompare = true;

                        if(charA == charB) continue;
                        if(charA > charB) selectedStrIndex = i;

                        isNeedLenCompare = false;
                        break;
                    }

                    if(isNeedLenCompare && remainLenB > remainLenA) selectedStrIndex = i;
                }
            }

            indexes[selectedStrIndex] += 1;
            sb.append(min);
        }
        System.out.println(sb.toString());

    }
}
