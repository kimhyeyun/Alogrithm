import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class 괄호제거 {

    public static class pair{
        int left;
        int right;

        pair(int left, int right){
            this.left = left;
            this.right = right;
        }
    }

    static ArrayList<pair> pairs;   // 괄호 쌍 인덱스 리스트
    static int N;                   // 괄호 쌍의 갯수  
    static String exprStr;
    static char[] expr;
    static HashSet<String> hashSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수식 문자열을 char 배열로 받기
        String exprStr = br.readLine().trim();
        expr = new char[exprStr.length()];
        
        for(int i = 0; i < exprStr.length();i++){
            expr[i] = exprStr.charAt(i);
        }

        // 괄호쌍의 인덱스 찾기
        pairs = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < expr.length; i++){
            char curChar = expr[i];
            if(curChar == '('){
                stack.push(i);
            }
            else if(curChar == ')'){
                pair p = new pair(stack.pop(), i);
                pairs.add(p);
            }
        }

        hashSet = new HashSet<>();
        N = pairs.size();

        removePair(0, expr);

        List removedExprList = new ArrayList(hashSet);
        Collections.sort(removedExprList);

        for(int i = 0 ; i < removedExprList.size(); i++){
            if(!removedExprList.get(i).equals(exprStr))
                sb.append(removedExprList.get(i)).append("\n");
        }

        System.out.println(sb);
    }

    private static void removePair(int idx, char[] expr) {
        if(idx >= N){
            String removeExpr = new String(expr);
            removeExpr = removeExpr.replaceAll(" ", "");
            hashSet.add(removeExpr);
        }
        else{
            pair curPair = pairs.get(idx);

            // curPair 삭제
            expr[curPair.left] = ' ';
            expr[curPair.right] = ' ';
            removePair(idx+1, expr);

            // curPair 삭제 안함
            expr[curPair.left] = '(';
            expr[curPair.right] = ')';
            removePair(idx+1, expr);
        }
    }
}
