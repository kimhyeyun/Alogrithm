import java.util.ArrayList;

public class 모의고사 {
    public static void main(String[] args) {
        int a[] = {1,3,2,4,2};
        int ans[]  = solution(a);

        for(int i=0;i<ans.length;i++)
            System.out.println(ans[i]);
    }

    public static int[] solution(int[] answers) {
        ArrayList<Integer> ans = new ArrayList<>();

        int[] one = {1,2,3,4,5};
        int[] two = {2,1,2,3,2,4,2,5};
        int[] three = {3,3,1,1,2,2,4,4,5,5};

        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;

        for(int i=0;i<answers.length;i++){
            if(answers[i] == one[i%5])
                cnt1++;
            if(answers[i] == two[i%8])
                cnt2++;
            if(answers[i] == three[i%10])
                cnt3++;
        }

        int max = cnt1;
        if(max < cnt2){
            max = cnt2;
        }
        if(max < cnt3){
            max = cnt3;
        }

        if(max == cnt1)
            ans.add(1);
        if(max == cnt2)
            ans.add(2);
        if(max == cnt3)
            ans.add(3);

        int[] answer = new int[ans.size()];
        for(int i=0;i<ans.size();i++){
            answer[i] = ans.get(i);
        }

        return answer;
    }
}
