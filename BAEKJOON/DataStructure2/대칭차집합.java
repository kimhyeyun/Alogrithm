package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class 대칭차집합 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        HashSet<String> aHashSet = new HashSet<>();
        HashSet<String> bHashSet = new HashSet<>();
        
        int aNum = Integer.parseInt(stringTokenizer.nextToken());
        int bNum = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0; i < aNum ; i++){
            aHashSet.add(stringTokenizer.nextToken());
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        for(int i = 0; i < bNum ; i++){
            bHashSet.add(stringTokenizer.nextToken());
        }

        HashSet<String> aCpySet = new HashSet<>(aHashSet);
        aHashSet.removeAll(bHashSet);
        bHashSet.removeAll(aCpySet);
        aHashSet.addAll(bHashSet);
        System.out.println(aHashSet.size());
  
    }
}
