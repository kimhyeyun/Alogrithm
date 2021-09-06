import java.util.Arrays;

public class 최댓값과최솟값 {
    public static String solution(String s) {
        String answer = "";

        String[] arr = s.split(" ");
        int[] arrInt = new int[arr.length];
        int idx = 0;
        for(String a : arr){
            arrInt[idx++] = Integer.parseInt(a);
        }

        Arrays.sort(arrInt);

        answer += (arrInt[0] + " " + arrInt[arrInt.length-1]);

        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution("-1 -2 -3 -4"));
    }
}
