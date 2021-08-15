import java.util.LinkedList;
import java.util.List;

public class 캐시 {
    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> list = new LinkedList<>();
        
        for(String city : cities){
            city = city.toUpperCase();
            
            if(list.contains(city)){
                answer += 1;
                list.remove(city);
                list.add(city);
            }
            else{
                answer += 5;
                list.add(city);
                if(list.size() > cacheSize)
                    list.remove(0);
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        String[] s = {"Jeju", "Pangyo", "NewYork", "newyork"};
        System.out.println(solution(2, s));
    }
}
