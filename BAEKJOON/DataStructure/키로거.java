import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class 키로거 {
    // ** LinkedList 사용 (int idx 를 두어서 위치에 insert, remove) -> 테케는 모두 통과했으나 시간초과 발생 ** //
    // iterator 이용 -> idx 사용 x -> 시간초과 x
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){

            String input = br.readLine();
            LinkedList<Character> list = new LinkedList<>();
            ListIterator<Character> iterator = list.listIterator();

            for(int i = 0 ; i < input.length(); i++){
                // 왼쪽으로 이동
                if(input.charAt(i) == '<'){
                    if(iterator.hasPrevious())
                        iterator.previous();
                }
                // 오른쪽으로 이동
                else if(input.charAt(i) == '>'){
                    if(iterator.hasNext())
                        iterator.next();
                }
                // backspace
                else if(input.charAt(i) == '-'){
                    if(iterator.hasPrevious()){
                        iterator.previous();
                        iterator.remove();
                    }
                }
                // 숫자나 문자가 입력
                else{
                    iterator.add(input.charAt(i));
                }
            }

            for(char c: list){
                sb.append(c);
            }
            
            sb.append("\n");
        }

        System.out.println(sb);
    }
 
  /*   public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            String input = br.readLine();

            Stack<Character> stack1 = new Stack<>();
            Stack<Character> stack2 = new Stack<>();

            for(int i = 0; i < input.length(); i++){
                // 왼쪽으로 커서 이동
                if(input.charAt(i) == '<'){
                    if(!stack1.isEmpty()){
                        stack2.push(stack1.pop());
                    }
                }
                // 오른쪽으로 커서 이동
                else if(input.charAt(i) == '>'){
                    if(!stack2.isEmpty()){
                        stack1.push(stack2.pop());
                    }
                }
                // backspace
                else if(input.charAt(i) == '-'){
                    if(!stack1.isEmpty()){
                        stack1.pop();
                    }
                }
                else{
                    stack1.add(input.charAt(i));
                }
            }

            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }

            while(!stack2.isEmpty()){
                sb.append(stack2.pop());
            }
            sb.append("\n");

        }
        System.out.println(sb);
    } */
}
