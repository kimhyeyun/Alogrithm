import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 베스트앨범 {

    public static class Music implements Comparable<Music>{
        String genre;
        int idx;
        int cnt;

        public Music(String genre, int idx, int cnt){
            this.genre = genre;
            this.idx = idx;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Music o) {
            int result = o.cnt - this.cnt;
            if(result == 0)
                result = this.idx - o.idx;

            return result;
        }
    }
    public static int[] solution(String[] genres, int[] plays) {
        int[] answer;

        // 장르별 재생 순 저장
        Map<String, Integer> cntByGenre = new HashMap<>();
        for(int i = 0 ; i < genres.length ; i++){
            cntByGenre.put(genres[i], cntByGenre.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 재생 횟수가 많은 순으로 장르 정렬
        List<String> listKeySet = new ArrayList<>(cntByGenre.keySet());
        Collections.sort(listKeySet, (value1, value2) -> (cntByGenre.get(value2).compareTo(cntByGenre.get(value1))));

        // 각 장르에서 1개 or 2개 고르기
        ArrayList<Music> bestAlbum = new ArrayList<>();
        for(String genre : listKeySet){
            ArrayList<Music> list = new ArrayList<>();

            for(int i = 0 ; i < genres.length ; i++){
                if(genre.equals(genres[i])){
                    list.add(new Music(genres[i], i, plays[i]));
                }
            }

            // 재생 횟수가 많은 순으로, 같으면 번호가 낮은 순으로 정렬
            Collections.sort(list);
            bestAlbum.add(list.get(0));

            if(list.size() > 1){
                bestAlbum.add(list.get(1));
            }
        }
        
        answer = new int[bestAlbum.size()];
        for(int i = 0 ; i < bestAlbum.size() ; i++){
            answer[i] = bestAlbum.get(i).idx;
        }
        
        return answer;
    }
    
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] answer = solution(genres, plays);

        for(int a : answer){
            System.out.println(a);
        }
    }
}
