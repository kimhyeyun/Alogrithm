import java.util.ArrayList;
import java.util.Collections;

public class 뉴스클러스터링 {

    public static int solution(String str1, String str2) {
        int answer = 0;
        ArrayList<String> set1 = new ArrayList<>();
        ArrayList<String> set2 = new ArrayList<>();
        ArrayList<String> union = new ArrayList<>();
        ArrayList<String> inter = new ArrayList<>();

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for(int i = 0;i < str1.length()-1; i++){
            char first = str1.charAt(i);
            char second = str1.charAt(i+1);

            if('a' <= first && first <= 'z' && 'a' <= second && second <= 'z'){
                set1.add(first+""+second);
            }
        }

        for(int i = 0;i < str2.length()-1; i++){
            char first = str2.charAt(i);
            char second = str2.charAt(i+1);

            if('a' <= first && first <= 'z' && 'a' <= second && second <= 'z'){
                set2.add(first+""+second);
            }
        }

        Collections.sort(set1);
        Collections.sort(set2);

        for(String s : set1){
            if(set2.remove(s))
                inter.add(s);

            union.add(s);
        }

        for(String s : set2){
            union.add(s);
        }

        double jakard = 1;

        if(inter.size() == 0 && union.size() == 0){
            jakard = 1;
        }
        else{
            jakard = (double)inter.size()/(double)union.size();
        }

        answer = (int)(jakard*65536);
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("FRANCE","french"));
    }
}
