    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Deque;
    import java.util.HashSet;
    import java.util.LinkedList;
    import java.util.List;
    import java.util.Map;
    import java.util.PriorityQueue;
    import java.util.Set;
    import java.util.StringTokenizer;
    import java.util.TreeMap;

    public class Main_BOJ_1713_후보추천하기 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            StringTokenizer stringTokenizer;

            int N = Integer.parseInt(br.readLine());
            int recommendNum = Integer.parseInt(br.readLine());

            stringTokenizer = new StringTokenizer(br.readLine());
            List<student> reco = new ArrayList<>();

            for(int i = 0 ; i < recommendNum ; i++){
                int num = Integer.parseInt(stringTokenizer.nextToken());
                boolean flag = false;

                for(int j = 0 ; j < reco.size(); j++){
                    if(reco.get(j).num == num){
                        reco.get(j).cnt++;
                        flag = true;
                        break;
                    }
                }

                // 새로 추가
                if(!flag){
                    if(reco.size() >= N){
                        Collections.sort(reco);
                        reco.remove(0);
                    }

                    reco.add(new student(num, i, 1));
                }
            }

            List<Integer> list = new ArrayList<>();
        
            for(student s : reco){
                list.add(s.num);
            }

            Collections.sort(list);
            for(int n : list)
                System.out.print(n + " ");
            System.out.println();

        }
        
        public static class student implements Comparable<student>{
            int num;
            int idx;
            int cnt;

            student(int num, int idx, int cnt){
                this.num = num;
                this.idx = idx;
                this.cnt = cnt;
            }

            @Override
            public int compareTo(student o) {
                // TODO Auto-generated method stub
                int result = this.cnt - o.cnt;
                if(result == 0)
                    result = this.idx - o.idx;

                return result;    
            }
        }
    }
