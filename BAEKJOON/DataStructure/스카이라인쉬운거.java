import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 스카이라인쉬운거 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer stringTokenizer;
        Stack<Integer> stack = new Stack<>();
        int[] arr = new int[N+1];
        int answer = 0;

        for(int i = 0; i < N; i++){
            stringTokenizer = new StringTokenizer(br.readLine());    
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            arr[i] = y;
        }

        for(int i = 0; i <= N; i++){
            while(!stack.isEmpty() && stack.peek() > arr[i]){
                answer++;
                stack.pop();
            }

            if(!stack.isEmpty() && stack.peek() == arr[i])
                continue;

            stack.push(arr[i]);
        }

        System.out.println(answer);
    }
}
