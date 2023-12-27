import java.util.*;
class Solution {
    public String solution(String X, String Y) {
        StringBuilder answer = new StringBuilder();
        
        int[] arrX = new int[10];
        int[] arrY = new int[10];
        
        countOfNumber(arrX, X);
        countOfNumber(arrY, Y);
        
        for(int i = arrX.length - 1; i >= 0; i--){
            while(arrX[i] > 0 && arrY[i] > 0){
                arrX[i] -= 1;
                arrY[i] -= 1;
                
                answer.append(i);
            }
        }
        
        if(answer.toString().length() == 0) return "-1";
        else if(answer.toString().startsWith("0")) return "0";
        else return answer.toString();
        
    
    }
    
    public void countOfNumber(int[] arr, String str){
        for(char c : str.toCharArray()){
             arr[c - '0'] += 1;
        }
    }
}