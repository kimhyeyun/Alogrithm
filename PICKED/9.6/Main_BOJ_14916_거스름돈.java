import java.util.Scanner;

public class Main_BOJ_14916_거스름돈 {

    // Greedy 알고리즘
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int twoCnt = 0;

        // 5의 배수라면 5로 나눈 몫이 최소 개수
        if(n % 5 == 0){
            System.out.println(n/5);
        }

        // 5의 배수가 아니라면
        else{
            while(true){
                // 2씩 빼면서 2원의 갯수 증가
                n -= 2;
                twoCnt++;

                // 2원을 뺀 나머지가 5의 배수라면 
                if(n % 5 == 0){
                    System.out.println(n/5 + twoCnt);
                    break;
                }

                // n이 0이되면 2원으로만 가능
                if(n == 0){
                    System.out.println(twoCnt);
                    break;
                }

                // 0보다 작아지면 2원과 5원으로 거슬러줄수 없음
                if(n < 0){
                    System.out.println(-1);
                    break;
                }
            }
        }
        
    }

    
}
