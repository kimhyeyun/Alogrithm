package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 배부른마라토너 {
    /* public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        List<String> participants = new LinkedList<>();
        List<String> finisher = new LinkedList<>();

        for(int i = 0 ; i < N ; i++){
            participants.add(br.readLine());
        }

        Collections.sort(participants);

        for(int i = 0 ; i < N-1; i++){
            finisher.add(br.readLine());
        }

        Collections.sort(finisher);

        for(int i = 0; i < finisher.size(); i++){
            if(!participants.get(i).equals(finisher.get(i))){
                System.out.println(participants.get(i));
                return;
            }
        }

        System.out.println(participants.get(participants.size()-1));
    } */
    // 시간초과

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer>  participants = new HashMap<>();
        Map<String, Integer>  finisher = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N; i++){
            String name = br.readLine();
            participants.put(name, participants.getOrDefault(name, 0)+1);
        }

        for(int i = 0 ; i < N-1; i++){
            String name = br.readLine();
            if(participants.get(name) > 1){
                participants.put(name, participants.get(name)-1);
            }
            else{
               participants.remove(name);
            }
        }

        Set<String> pSet = participants.keySet();

        for(String s : pSet)
            System.out.println(s);
    }
}
