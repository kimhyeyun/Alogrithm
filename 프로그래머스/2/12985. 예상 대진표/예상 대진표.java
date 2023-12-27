class Solution
{
   
 public static int solution(int n, int a, int b){

            int round = 1;

            while(true){
                int max = Math.max(a, b);
                int min = Math.min(a, b);

                if(max - min == 1 && max%2 == 0){
                    return round;
                }

                round++;
                
                if(a%2 == 0){
                    a /= 2;
                }
                else{
                    a = (a+1)/2;
                }

                if(b%2 == 0){
                    b /= 2;
                }
                else{
                    b = (b+1)/2;
                }
            }


        }
}