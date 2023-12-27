import java.util.*;
class Solution {
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
}