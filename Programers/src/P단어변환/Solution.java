package P단어변환;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	
	
	public int[] solution(String[] genres, int[] plays) {
		int len = plays.length;
		
		ArrayList<Integer> answer_list = new ArrayList<Integer>();
        
        HashMap<String, ArrayList<int[]>> genre_list = new HashMap<>();
        HashMap<String, Integer> genre_play = new HashMap<>();
        for(int i = 0; i<len; i++) {
        	if(!genre_list.containsKey(genres[i])) {
        		genre_list.put(genres[i], new ArrayList<int[]>());
        	}
        	genre_list.get(genres[i]).add(new int[] {i, plays[i]});
        	if(!genre_play.containsKey(genres[i])) {
        		genre_play.put(genres[i], 0);
        	}
        	genre_play.put(genres[i], genre_play.get(genres[i]) + plays[i]);
        }
        TreeMap<Integer, String> r_tmap = new TreeMap<>(Collections.reverseOrder());
        for(Entry<String, Integer> e : genre_play.entrySet()) {
        	r_tmap.put(e.getValue(), e.getKey());
        }
        
        for(Entry<Integer, String> e : r_tmap.entrySet()) {
        	String genre = r_tmap.get(e.getKey());
        	ArrayList<int[]> list = genre_list.get(genre);
        	list.sort((a, b)->Integer.compare(b[1], a[1]));
        	int cnt = 0;
        	for(int[] p : list) {
        		if(cnt == 2) break;
        		answer_list.add(p[0]);
        		cnt++;
        	}
        }
        int[] answer = new int[answer_list.size()];
        int idx = 0;
        for(int a :answer_list) {
        	answer[idx++] = a;
        }
        
        
        return answer;
    }
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		System.out.println(Arrays.toString(s.solution(genres, plays)));
	}
}
