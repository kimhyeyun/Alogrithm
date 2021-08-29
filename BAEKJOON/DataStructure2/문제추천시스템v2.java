package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 문제추천시스템v2{

    public static class Problem implements Comparable<Problem>{

        int idx;
        int level;
        int group;

        Problem(int idx, int level, int group){
            this.idx = idx;
            this.level = level;
            this.group = group;
        }

        @Override
        public int compareTo(Problem o) {
            // TODO Auto-generated method stub
            if(o.level - level == 0)
                return o.idx - idx;
            else
                return o.level - level;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        TreeSet<Problem> problems = new TreeSet<>();
        Map<Integer, TreeSet<Problem>> groupAlgo = new HashMap<>();
        Map<Integer, Integer> levelMap = new HashMap<>();
        Map<Integer, Integer> groupMap = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            stringTokenizer = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(stringTokenizer.nextToken());
            int level = Integer.parseInt(stringTokenizer.nextToken());
            int group = Integer.parseInt(stringTokenizer.nextToken());

            problems.add(new Problem(idx, level, group));

            if(groupAlgo.containsKey(group)){
                TreeSet<Problem> tmp = groupAlgo.get(group);
                tmp.add(new Problem(idx, level, group));
                groupAlgo.replace(group, tmp);
            }
            else{
                TreeSet<Problem> tmp = new TreeSet<>();
                tmp.add(new Problem(idx, level, group));
                groupAlgo.put(group, tmp);
            }

            levelMap.put(idx, level);
            groupMap.put(idx, group);
        }

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            String input = stringTokenizer.nextToken();

            if(input.equals("recommend")){
                int group = Integer.parseInt(stringTokenizer.nextToken());
                int x = Integer.parseInt(stringTokenizer.nextToken());

                if(x == 1){
                    sb.append(groupAlgo.get(group).first().idx).append("\n");
                }
                else{
                    sb.append(groupAlgo.get(group).last().idx).append("\n");
                }
            }

            else if(input.equals("recommend2")){
                int x = Integer.parseInt(stringTokenizer.nextToken());
                
                if(x == 1){
                    sb.append(problems.first().idx).append("\n");
                }
                else{
                    sb.append(problems.last().idx).append("\n");
                }
            }

            else if(input.equals("recommend3")){
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int level = Integer.parseInt(stringTokenizer.nextToken());

                if(x == 1){
                    if(problems.floor(new Problem(0, level, 0)) == null)
                        sb.append(-1).append("\n");
                    else
                        sb.append(problems.floor(new Problem(0, level, 0)).idx).append("\n");
                }
                else{
                    if(problems.ceiling(new Problem(0, level, 0)) == null)
                        sb.append(-1).append("\n");
                    else
                        sb.append(problems.ceiling(new Problem(0, level, 0)).idx).append("\n");
                }
            }

            else if(input.equals("add")){
                int idx = Integer.parseInt(stringTokenizer.nextToken());
                int level = Integer.parseInt(stringTokenizer.nextToken());
                int group = Integer.parseInt(stringTokenizer.nextToken());

                problems.add(new Problem(idx, level, group));

                if(groupAlgo.containsKey(group)){
                    TreeSet<Problem> tmp = groupAlgo.get(group);
                    tmp.add(new Problem(idx, level, group));
                    groupAlgo.replace(group, tmp);
                }
                else{
                    TreeSet<Problem> tmp = new TreeSet<>();
                    tmp.add(new Problem(idx, level, group));
                    groupAlgo.put(group, tmp);
                }

                levelMap.put(idx, level);
                groupMap.put(idx, group);
            }

            else if(input.equals("solved")){
                int idx = Integer.parseInt(stringTokenizer.nextToken());
                int level = levelMap.get(idx);
                int group = groupMap.get(idx);

                problems.remove(new Problem(idx, level, group));
                groupAlgo.get(group).remove(new Problem(idx, level, group));
                levelMap.remove(idx);
                groupMap.remove(idx);
            }
        }
        System.out.print(sb);
    }
}