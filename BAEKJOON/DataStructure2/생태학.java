package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class 생태학 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String treeName = "";
        Map<String, Double> trees = new HashMap<>();
        double total = 0;

        while(sc.hasNextLine()){
            treeName = sc.nextLine();
            trees.put(treeName, trees.getOrDefault(treeName, 0.0)+1.0);
            total++;
        }

        List<String> list = new LinkedList<>();

        for(String k : trees.keySet()){
            list.add(k);
        }

        Collections.sort(list);

        for(String l : list){
            sb.append(l + " " + String.format("%.4f", (trees.get(l)/total * 100))).append("\n");
        }

        System.out.print(sb);
    }
}
