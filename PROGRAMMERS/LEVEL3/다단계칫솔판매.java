import java.util.HashMap;
import java.util.Map;

public class 다단계칫솔판매 {
/*     public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        int price = 100;
        Map<String, String> parentMap = new HashMap<>();    // 본인 - 부모
        Map<String, Integer> indexMap = new HashMap<>();    // 본인 자신의 index -> enroll 순으로 answer 받아야함

        for(int i = 0 ; i <enroll.length ;i++){
            parentMap.put(enroll[i], referral[i]);
            indexMap.put(enroll[i], i);
        }

        for(int i = 0 ; i < seller.length ;i++){
            String curSeller = seller[i];
            int profit = price * amount[i];

            while(!curSeller.equals("-")){
                int profitForParent = profit / 10;
                int profitForMe = profit - profitForParent;

                answer[indexMap.get(curSeller)] += profitForMe;
                
                curSeller = parentMap.get(curSeller);
                profit /= 10;

                if(profitForParent < 1)
                    break;
            }

        }

        return answer;
    } */
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        int price = 100;
        Map<String, Integer> indexMap = new HashMap<>();    // 본인 자신의 index -> enroll 순으로 answer 받아야함

        for(int i = 0 ; i <enroll.length ;i++){
            indexMap.put(enroll[i], i);
        }

        for(int i = 0 ; i < seller.length ;i++){
            String curSeller = seller[i];
            int profit = price * amount[i];

            while(!curSeller.equals("-")){
                int profitForParent = profit / 10;
                int profitForMe = profit - profitForParent;

                answer[indexMap.get(curSeller)] += profitForMe;
                
                curSeller = referral[indexMap.get(curSeller)];
                profit /= 10;

                if(profitForParent < 1)
                    break;
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        int[] answer = solution(enroll, referral, seller, amount);

        for(int a : answer)
            System.out.println(a);
    }
}
