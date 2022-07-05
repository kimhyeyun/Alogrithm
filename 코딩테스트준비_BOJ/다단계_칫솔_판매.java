import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 다단계_칫솔_판매 {

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount){
        Map<String, String> parentOfSeller = new HashMap<>();
        Map<String, Integer> sellerIdx = new HashMap<>();
        int[] priceOfSeller = new int[enroll.length];
        int price = 100;

        for (int i = 0; i < enroll.length; i++) {
            sellerIdx.put(enroll[i], i);
            parentOfSeller.put(enroll[i], referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            String cur = seller[i];
            int profit = price * amount[i];

            while (!cur.equals("-")) {
                int profitForParent = profit / 10;
                int profitForMe = profit - profitForParent;

                priceOfSeller[sellerIdx.get(cur)] += profitForMe;

                cur = referral[sellerIdx.get(cur)];
                profit = profitForParent;

                if(profit < 1) break;
            }
        }

        return priceOfSeller;


    }

}
