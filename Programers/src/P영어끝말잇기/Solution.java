package P영어끝말잇기;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	Set<String> checkList;
	public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        checkList = new HashSet<>();
        
        String prevWord = words[0];
        checkList.add(prevWord);
        
        for(int i = 1; i<words.length; i++) {
        	String word = words[i];
        	if(checkList.contains(word) || prevWord.charAt(prevWord.length()-1) != word.charAt(0)) {
        		answer[0] = (i)%n+1;
        		answer[1] = (i)/n+1;
        		break;
        	}
        	prevWord = word;
        	checkList.add(word);
        }

        System.out.println(answer[0] + " " + answer[1]);
        return answer;
    }

	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		//int n = 3;
		//String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
		
		int n = 2;
		String[] words = 	{"hello", "one", "even", "never", "now", "world", "draw"};
		
		System.out.println(s.solution(n, words));
	}
}
