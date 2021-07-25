import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 문자열내마음대로정렬 {
    public static void main(String[] args) {
        String[] s = {"sun", "bed", "car"};
        String[] a = solution(s, 1);

        for(String st : a){
            System.out.println(st);
        }
    }
    public static String[] solution(String[] strings, int n) {
        String[] answer = {};

        ArrayList<my> list = new ArrayList<>();

        for(String s : strings){
            list.add(new my(s, n));
        }

        Collections.sort(list);

        answer = new String[list.size()];
        int i =0;
        for(my m : list){
            answer[i++] = m.s;
        }
        return answer;
    }

    // 비교함수
   public static class my implements Comparable<my>{
       String s;
       int n;

       public my(String s, int n){
           this.s = s;
           this.n = n;
       }

       public String gets(){
           return s;
       }

       @Override
       public int compareTo(my o) {
           // TODO Auto-generated method stub
           // 같으면 앞에서 부터 비교
           if((int)s.charAt(n) == (int)o.gets().charAt(n)){
                return s.compareTo(o.gets());
           }
           // n번째 char 비교
           else{
               return s.substring(n,n+1).compareTo(o.gets().substring(n,n+1));
           }
       }
   }

}
