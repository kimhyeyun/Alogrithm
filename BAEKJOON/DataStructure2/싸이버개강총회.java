package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 싸이버개강총회 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        stringTokenizer = new StringTokenizer(br.readLine(), " ");
        
        int startTime = toMinute(stringTokenizer.nextToken());
        int endTime = toMinute(stringTokenizer.nextToken());
        int finishTime = toMinute(stringTokenizer.nextToken());

        String str = "";
        Map<String, Integer> chat = new HashMap<>();
        int answer = 0;

        while((str = br.readLine()) != null){
            
            stringTokenizer = new StringTokenizer(str, " ");
            int chatTime = toMinute(stringTokenizer.nextToken());
            String nickName = stringTokenizer.nextToken();

            if(chatTime <= startTime){
                chat.put(nickName, chatTime);
            }
            else if(endTime <= chatTime && chatTime <= finishTime ){
                if(chat.containsKey(nickName)){
                    chat.remove(nickName);
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private static int toMinute(String str) {
        String[] time = str.split(":");
        int h = Integer.parseInt(time[0]);
        int m = Integer.parseInt(time[1]);

        return h * 60 + m;
    }
}
