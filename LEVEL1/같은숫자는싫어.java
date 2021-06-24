import java.util.ArrayList;

public class 같은숫자는싫어 {
    public static void main(String[] args) {
        int[] arr = {4,4,4,3,3};
        int[] a = solution(arr);

        for(int i=0;i<a.length;i++)
            System.out.println(a[i]);
    }

    public static int[] solution(int []arr) {
        int[] answer = {};
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr[0]);

        for(int i=1;i<arr.length;i++){
            if(arr[i] == list.get(list.size()-1))
                continue;
            else
                list.add(arr[i]);
        }

        answer = new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}
