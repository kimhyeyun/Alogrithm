public class 이상한문자만들기 {
    public static void main(String[] args) {
        System.out.println(solution("try hello world"));
    }


    public static String solution(String s) {
        String answer = "";

        char[] arr = s.toCharArray();
        // 단어 (공백을 기준)별로 짝/홀수 인덱스 판단
        int idx = 0;

        for(int i = 0;i<arr.length; i++){

            if(idx%2 == 0){
                // 짝수라면 대문자로
                if('a' <= arr[i] && arr[i] <= 'z'){
                    arr[i] -= 32;
                }
                idx++;
            }
            else{
                // 홀수라면 소문자로
                if('A' <= arr[i] && arr[i] <= 'Z'){
                    arr[i] += 32;
                }
                idx++;
            }

            if(arr[i] == ' ')
                idx = 0;
        }

        answer = new String(arr);

        return answer;
    }
}
