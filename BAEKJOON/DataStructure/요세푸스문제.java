import java.util.ArrayList;
import java.util.Scanner;

public class 요세푸스문제{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        ArrayList<Integer> table = new ArrayList<>();

        for(int i = 1; i <= N; i++){
            table.add(i);
        }
        
        System.out.print("<");

        int idx = 0;
        while(table.size() != 1){
            for(int i = 0; i < K-1; i++){
                idx++;

                if(idx == table.size()){
                    idx = 0;
                }
            }

            System.out.print(table.get(idx) + ", ");

            table.remove(idx);
            N--;

            if(idx == table.size())
                idx = 0;
        }

        System.out.println(table.get(idx) + ">");
    }
    
}