package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_10825_국영수 {

    static class student implements Comparable<student>{
        String name;
        int korean;
        int math;
        int english;

        public student(String name, int korean, int math, int english) {
            this.name = name;
            this.korean = korean;
            this.math = math;
            this.english = english;
        }

        @Override
        public int compareTo(student o) {
            int result = o.korean - this.korean;
            if(result == 0)
                result = this.english - o.english;
            if(result == 0)
                result = o.math - this.math;
            if(result == 0)
                result = this.name.compareTo(o.name);
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer;

        List<student> students = new ArrayList<>();
        int N = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            String name = stringTokenizer.nextToken();
            int korean = Integer.parseInt(stringTokenizer.nextToken());
            int english = Integer.parseInt(stringTokenizer.nextToken());
            int math = Integer.parseInt(stringTokenizer.nextToken());

            students.add(new student(name, korean, math, english));
        }

        Collections.sort(students);

        for(student student : students){
            stringBuilder.append(student.name).append("\n");
        }
        System.out.println(stringBuilder);
    }
}
