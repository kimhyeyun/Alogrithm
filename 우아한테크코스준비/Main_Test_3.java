import java.util.HashMap;
import java.util.Map;

public class Main_Test_3 {
    public static int solution(String[] ings, String[] menu, String[] sell) {
        int answer = 0;

        Map<Character, Integer> ingredient = new HashMap<>();
        Map<String, Integer> revenue = new HashMap<>();

        for(String i : ings){
            String[] s = i.split(" ");
            ingredient.put(i.charAt(0), Integer.parseInt(s[1]));
        }

        for(String m : menu){
            String[] s = m.split(" ");
            int iPrice = 0;
            for(char i : s[1].toCharArray()){
                iPrice += ingredient.get(i);
            }

            revenue.put(s[0], Integer.parseInt(s[2]) - iPrice);
        }

        for(String s : sell){
            String[] a = s.split(" ");
            int r = revenue.get(a[0]);

            answer += r * Integer.parseInt(a[1]);
        }


        return answer;
    }

    public static void main(String[] args) {
        String[] ings = {"r 10", "a 23", "t 124", "k 9"};
        String[] menu = {"PIZZA arraak 145", "HAMBURGER tkar 180", "BREAD kkk 30", "ICECREAM rar 50", "SHAVEDICE rar 45", "JUICE rra 55", "WATER a 20"};
        String[] sell = {"BREAD 5", "ICECREAM 100", "PIZZA 7", "JUICE 10", "WATER 1"};
        
        System.out.println(solution(ings, menu, sell));
    }
}
