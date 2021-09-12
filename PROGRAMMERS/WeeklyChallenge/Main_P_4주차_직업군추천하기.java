import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_P_4주차_직업군추천하기 {
    public static String solution(String[] table, String[] languages, int[] preference) {
        
        String answer = "";
        List<String> occupation[] = new ArrayList[5];

        for(int i = 0 ; i < 5; i++){
            occupation[i] = new ArrayList<>();
        }

        Arrays.sort(table);
        for(int i = 0 ; i < table.length; i++){
            String[] l = table[i].split(" ");

            for(int j = 1 ; j < l.length ; j++){
                occupation[i].add(l[j]);
            }
        }

        int idx = 0;
        int max = 0;
        for(int i = 0 ; i < 5; i++){
            int sum = 0;
            for(int j = 0 ; j <languages.length ; j++){
                if(occupation[i].indexOf(languages[j]) == -1)
                    sum += 0;
                else{
                    sum += (5 - occupation[i].indexOf(languages[j])) * preference[j];
                }
            }
            if(max < sum){
                max = sum;
                idx = i;
            }
            System.out.println(sum);
        }

        switch(idx){
            case 0:
                answer = "CONTENTS";
                break;
            case 1:
                answer = "GAME";
                break;
            case 2:
                answer = "HARDWARE";
                break;
            case 3:
                answer = "PORTAL";
                break;
            case 4:
                answer = "SI";
                break;
        }


        return answer;
    }

    public static void main(String[] args) {
        String[] table = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
        String[] languages = {"JAVA", "JAVASCRIPT"};
        int[] preference = {7, 5};

        System.out.println(solution(table, languages, preference));
    }
}
