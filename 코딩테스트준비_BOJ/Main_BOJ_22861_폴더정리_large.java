import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_22861_폴더정리_large {

    static int N, M, K, Q, cnt = 0;
    static final int FILE = 0, FOLDER = 1;
    static Map<String, HashSet<String>> haveFiles = new HashMap<>();
    static Map<String, HashSet<String>> haveFolders = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        for (int i = 0; i < N + M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            String parFolder = stringTokenizer.nextToken();
            String name = stringTokenizer.nextToken();
            int isWhat = Integer.parseInt(stringTokenizer.nextToken());

            Input(parFolder, name, isWhat);
        }

        K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            String[] folderA = stringTokenizer.nextToken().split("/");
            String[] folderB = stringTokenizer.nextToken().split("/");

            move(folderA, folderB);
        }

        Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            String[] folders = stringTokenizer.nextToken().split("/");

            find(folders);
        }

        System.out.print(sb);
    }

    private static void find(String[] folders) {
        cnt = 0;
        set = new HashSet<>();
        String target = folders[folders.length - 1];
        findFolderAndFile(target);

        sb.append(cnt + " " + set.size()).append("\n");
    }

    private static void findFolderAndFile(String target) {
        if (haveFolders.containsKey(target)) {
            for (String nextFolder : haveFolders.get(target)) {
                findFolderAndFile(nextFolder);

            }
        }
        if (haveFiles.containsKey(target)) {
            for (String file : haveFiles.get(target)) {
                set.add(file);
                cnt += 1;
            }
        }
    }

    private static void move(String[] folderA, String[] folderB) {
        String target = folderA[folderA.length - 1];
        String dest = folderB[folderB.length - 1];

        if (haveFolders.containsKey(target)) {
            if(!haveFolders.containsKey(dest)) haveFolders.put(dest, new HashSet<>());

            for (String folder : haveFolders.get(target)) {
                haveFolders.get(dest).add(folder);
            }

            haveFolders.remove(target);
        }

        if (haveFiles.containsKey(target)) {
            if(!haveFiles.containsKey(dest)) haveFiles.put(dest, new HashSet<>());

            for (String file : haveFiles.get(target)) {
                haveFiles.get(dest).add(file);
            }

            haveFiles.remove(target);
        }

        String parent = folderA[folderA.length - 2];
        haveFolders.get(parent).remove(target);

    }

    private static void Input(String parFolder, String name, int isWhat) {
        if (isWhat == FOLDER) {
            if(!haveFolders.containsKey(parFolder)) haveFolders.put(parFolder, new HashSet<>());

            haveFolders.get(parFolder).add(name);
        } else if (isWhat == FILE) {
            if(!haveFiles.containsKey(parFolder)) haveFiles.put(parFolder, new HashSet<>());

            haveFiles.get(parFolder).add(name);
        }
    }
}
