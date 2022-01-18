package 문자열;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main_BOJ_11656_접미사_배열 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();
        List<String> list = new ArrayList<>();

        for(int i = 0 ; i < str.length(); i++){
            list.add(str.substring(i, str.length()));
        }

        Collections.sort(list);
        for (String l : list) {
            System.out.println(l);
        }

    }

}
