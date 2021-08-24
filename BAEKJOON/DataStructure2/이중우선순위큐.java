package DataStructure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 이중우선순위큐 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // 시간초과 코듣
           /*  PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            int size = 0;

            int Q = Integer.parseInt(br.readLine());

            while (Q-- > 0) {
                StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
                String input = stringTokenizer.nextToken();

                if (input.equals("I")) {
                    int x = Integer.parseInt(stringTokenizer.nextToken());
                    minHeap.add(x);
                    maxHeap.add(x);
                    size++;
                } else {
                    if (size == 0) {
                        continue;
                    } else {
                        // 시간초과 remove(Object) -> O(n)time
                        if (Integer.parseInt(stringTokenizer.nextToken()) == 1) {
                            int max = maxHeap.poll();
                            minHeap.remove(max);
                        } else {
                            int min = minHeap.poll();
                            maxHeap.remove(min);
                        }
                        size--;
                    }
                }
            }

            if(size == 0){
                sb.append("EMPTY\n");
            }
            else{
                sb.append(maxHeap.peek() + " " + minHeap.peek() + "\n");
            }
        }
        System.out.print(sb); */

            int Q = Integer.parseInt(br.readLine());
            // treeMap은 red-black tree로 구현되어있음 -> O(log n) time
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();

            while(Q-- > 0){
                StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
                String input = stringTokenizer.nextToken();

                if(input.equals("I")){
                    int x = Integer.parseInt(stringTokenizer.nextToken());
                    treeMap.put(x, treeMap.getOrDefault(x, 0)+1);
                }
                else{
                    if(treeMap.isEmpty())
                        continue;
                    else{
                        if(Integer.parseInt(stringTokenizer.nextToken()) == 1){
                            // 최대값
                            int max = treeMap.lastKey();

                            if(treeMap.get(max) == 1){
                                treeMap.remove(max);
                            }
                            else{
                                treeMap.put(max, treeMap.get(max)-1);
                            }
                        }
                        else{
                            // 최소값
                            int min = treeMap.firstKey();
                            
                            if(treeMap.get(min) == 1)
                                treeMap.remove(min);
                            else
                                treeMap.put(min, treeMap.get(min)-1);    
                                
                        }
                    }
                }
            }

            if(treeMap.isEmpty())
                sb.append("EMPTY\n");
            else{
                sb.append(treeMap.lastKey() + " " + treeMap.firstKey()).append("\n");
            }
        }

        System.out.print(sb);
    }
}
