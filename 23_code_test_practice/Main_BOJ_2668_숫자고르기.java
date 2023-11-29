import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main_BOJ_2668_숫자고르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> list = new LinkedList<>();
        boolean[] isVisited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            isVisited[i] = true;
            DFS(i, i, arr, list, isVisited);
            isVisited[i] = false;
        }

        Collections.sort(list);

        System.out.println(list.size());
        for (int l : list) System.out.println(l);
    }

    private static void DFS(int index, int target, int[] arr, List<Integer> list, boolean[] isVisited) {
        if(arr[index] == target) list.add(target);

        if (!isVisited[arr[index]]) {
            isVisited[arr[index]] = true;
            DFS(arr[index], target, arr, list, isVisited);
            isVisited[arr[index]] = false;
        }
    }
}
