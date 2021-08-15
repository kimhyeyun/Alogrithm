import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class 탑 {
    public static void main(String[] args) throws IOException {
        // 시간초과
       /*  Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        List<Integer> height = new ArrayList<>();

        for(int i = 0; i < N; i++){
            height.add(sc.nextInt());
        }

        for(int i = N-1; i >= 0; i--){
            boolean flag = false;
            int h = height.get(i);

            for(int j = i-1; j >= 0; j--){
                if(h <= height.get(j)){
                    flag = true;
                    System.out.print(i+1 + " ");
                    break;
                }
            }
            if(!flag)
                System.out.print(0 + " ");
        }
 */

        class Tower{
            int height;
            int location;

            Tower(int height, int location){
                this.height = height;
                this.location = location;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Tower> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        for(int i = 1; i <= N; i++){
            int curTop = Integer.parseInt(st.nextToken());

            while(!stack.empty()){
                if(stack.peek().height >= curTop){
                    System.out.print(stack.peek().location + " ");
                    break;
                }
                stack.pop();
            }

            if(stack.isEmpty())
                System.out.print(0 + " ");

            stack.push(new Tower(curTop, i));
        }

    }
}
