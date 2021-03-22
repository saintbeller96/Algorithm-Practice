package P카펫;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	class Info{
		String word;
		int step;
		public Info(String word, int step) {
			this.word = word;
			this.step = step;
		}
	}
	
	public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        Queue<Info> que = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        
        que.offer(new Info(begin, 0));
        visited.add(begin);
        while(!que.isEmpty()) {
        	Info info = que.poll();
        	
        	if(info.word.equals(target)) {
        		answer = info.step;
        		break;
        	}
        	
        	for(String word : words) {
        		if(visited.contains(word)) continue;
        		int cnt = 0;
        		for(int i = 0; i<word.length(); i++) {
        			if(word.charAt(i) == info.word.charAt(i)) cnt++;
        		}
        		if(cnt == word.length()-1) {
        			que.offer(new Info(word, info.step+1));
        			visited.add(word);
        		}
        	}
        }
        
        return answer;
    }
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		String begin = "hit", target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		String[] words2 = {"hot", "dot", "dog", "lot", "log"};
		s.solution(begin, target, words2);
	}
}
