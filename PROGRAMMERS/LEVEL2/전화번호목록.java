import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class 전화번호목록 {

    public static boolean solution(String[] phone_book) {
        // 이중포문 -> 시간초과
       /*  boolean answer = true;

        Arrays.sort(phone_book, new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                // TODO Auto-generated method stub
                return o1.length()-o2.length();
            }
        });

        for(int i =0 ;i < phone_book.length-1;i++){
            for(int j = i+1;j<phone_book.length;j++){
                if(phone_book[i].equals(phone_book[ㅓ].substring(0, phone_book[i].length()))){
                    return false;
                }
            }
        }


        return answer; */


        // Hash 사용
        HashMap<String, Integer> hash = new HashMap<>();

        for(int i = 0;i < phone_book.length;i++){
            hash.put(phone_book[i], i);
        }

        for(int i = 0;i < phone_book.length;i++){
            for(int j = 1; j < phone_book[i].length();j++){
                if(hash.containsKey(phone_book[i].substring(0,j))){
                    return false;
                }
            }
        }
        return true;

    }
    public static void main(String[] args) {
        String[] p = {"12","123","1235","567","88"};

        System.out.println(solution(p));
    }
}
