package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_10814_나이순_정렬 {

    public static class member implements Comparable<member>{
        int id;
        String name;
        int age;

        public member(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(member o) {
            int result = this.age - o.age;
            result = result == 0 ? this.id - o.id : result;

            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer;

        List<member> members = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(stringTokenizer.nextToken());
            String name = stringTokenizer.nextToken();

            members.add(new member(i, name, age));
        }

        Collections.sort(members);

        for(member m : members){
            stringBuilder.append(m.age + " " + m.name).append("\n");
        }

        System.out.println(stringBuilder);
    }
}
