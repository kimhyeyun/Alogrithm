public class 최대공약수와최소공배수 {
    public static void main(String[] args) {
        int ans[] = solution(2, 5);

        for(int a :  ans){
            System.out.println(a);
        }
    }

    public static int[] solution(int n, int m) {
        int[] answer = new int[2];
        answer[0] = gcd(n, m);
        answer[1] = lcd(n, m);

        return answer;
    }

    public static int gcd(int n, int m){
        int a;

        while(m != 0){
            a = n%m;
            n = m;
            m = a;
        }

        return n;
    }

    public static int lcd(int n, int m){
        return (n*m)/gcd(n, m);
    }
}
