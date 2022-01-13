package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1406_에디터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        String str = br.readLine();
        int N = Integer.parseInt(br.readLine());

//        List<Character> list = new ArrayList<>();
/*        LinkedList<Character> list = new LinkedList<>();
        for (char c : str.toCharArray())
            list.add(c);

//        int cursor = list.size();

        *//*while(N-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());
            String input = stringTokenizer.nextToken();

            if(input.equals("L")){
                if(cursor == 0) continue;
                cursor--;
            }else if(input.equals("D")){
                if(cursor == list.size()) continue;
                cursor++;
            }else if(input.equals("B")){
                if(cursor == 0) continue;
                list.remove(cursor-1);
                cursor--;
            }else{
                char x = stringTokenizer.nextToken().charAt(0);
                list.add(cursor, x);
                cursor++;
            }
        }
        for(char l : list){
            System.out.print(l);
        }
        System.out.println();
    }*//*   // 시간 초과

        ListIterator<Character> cursor = list.listIterator();
        while(cursor.hasNext()){
            cursor.next();
        }

        while(N-- > 0){
            String input = br.readLine();
            char c = input.charAt(0);

            if(c == 'L'){
                if(cursor.hasPrevious())
                    cursor.previous();
            }else if(c == 'D'){
                if(cursor.hasNext())
                    cursor.next();
            }else if(c == 'B'){
                if(cursor.hasPrevious()){
                    cursor.previous();
                    cursor.remove();
                }
            }else{
                char x = input.charAt(2);
                cursor.add(x);
            }
        }
        for(char l : list){
            sb.append(l);
        }
        System.out.println(sb);

        */

        // stack 이용
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        for(char c : str.toCharArray())
            left.push(c);

        while(N-- > 0){
            String input = br.readLine();
            char c = input.charAt(0);

            switch (c){
                case 'L':
                    if(!left.empty())
                        right.push(left.pop());
                    break;
                case 'D':
                    if(!right.isEmpty())
                        left.push(right.pop());
                    break;
                case 'B':
                    if(!left.empty())
                        left.pop();
                    break;
                case 'P':
                    char x = input.charAt(2);
                    left.push(x);
                    break;
                default:
                    break;
            }
        }

        while(!left.isEmpty())
            right.push(left.pop());
        while(!right.isEmpty())
            sb.append(right.pop());

        System.out.println(sb);
    }
}
