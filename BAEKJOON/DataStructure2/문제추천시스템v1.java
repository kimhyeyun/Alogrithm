package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 문제추천시스템v1{

    public static class Problem implements Comparable<Problem>{
        int idx;
        int level;

        Problem(int idx, int level){
            this.idx = idx;
            this.level = level;
        }

        // 난이도 순으로 정렬 -> 문제번호로 정렬
        @Override
        public int compareTo(Problem o) {
            // TODO Auto-generated method stub
            if(o.level - level == 0){
                return o.idx - idx;
            }
            else{
                return o.level - level;
            }
        }

    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        TreeSet<Problem> problems = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(stringTokenizer.nextToken());
            int l = Integer.parseInt(stringTokenizer.nextToken());
            problems.add(new Problem(p,l));
            map.put(p, l);
        }

        int M = Integer.parseInt(br.readLine());
        for(int i = 0 ;i < M; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            String input = stringTokenizer.nextToken();

            if(input.equals("add")){
                int p = Integer.parseInt(stringTokenizer.nextToken());
                int l = Integer.parseInt(stringTokenizer.nextToken());
                problems.add(new Problem(p,l));
                map.put(p, l);
            }
            else if(input.equals("recommend")){
                if(Integer.parseInt(stringTokenizer.nextToken()) == 1){
                    // System.out.println(problems.first().idx);
                    sb.append(problems.first().idx).append("\n");
                }
                else{
                    // System.out.println(problems.last().idx);
                    sb.append(problems.last().idx).append("\n");
                }
            }
            else if(input.equals("solved")){
                int p = Integer.parseInt(stringTokenizer.nextToken());
                problems.remove(new Problem(p, map.get(p)));
            }
        }

        System.out.println(sb);
    }
}