public class 비밀지도 {
    public static void main(String[] args) {
        int[] arr1 = {9,20,28,18,11};
        int[] arr2 = {30,1,21,17,28};

        String[] ans = solution(5, arr1, arr2);
        for(int i=0;i<ans.length;i++){
            System.out.println(ans[i]);
        }
    }

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for(int i=0;i<n;i++){
            // 초기화
            int[] map1 = new int[n];
            int[] map2 = new int[n];
    
            // 각 행별로 넣을 값
            String str = "";
            // 지도1의 숫자
            int num1 = arr1[i];
            // 지도2의 숫자
            int num2 = arr2[i];

            // 2진수로 변환
            int idx = 0;
            while(num1 != 0){
                map1[idx] = num1%2;
                num1 /= 2;
                idx++;
            }
            
            idx = 0;
            while(num2 != 0){
                map2[idx] = num2%2;
                num2 /= 2;
                idx++;
            }

            // 뒤에서 부터 "#" " " 넣기
            for(int j = n-1;j >= 0;j--){
                if(map1[j] == 0 && map2[j] == 0){
                    str += " ";
                }
                else{
                    str += "#";
                }
            }
            answer[i] = str;
        }
        return answer;
    }
}
