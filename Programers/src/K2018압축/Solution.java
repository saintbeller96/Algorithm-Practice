package K2018압축;

import java.util.*;

public class Solution {

	public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i<26; i++) {
        	dict.put(Character.toString((char)('A'+i)),i+1);
        }
        int idx = 27;
        for(int i = 0; i<msg.length(); i++) {
        	String w = "";
        	for(int j = i; j<msg.length(); j++) {
        		String wc = w + msg.charAt(j);
        		if(!dict.containsKey(wc)) {
        			result.add(dict.get(w));
        			dict.put(wc, idx++);
        			break;
        		}else if(j == msg.length()-1){
        			result.add(dict.get(wc));
        		}
        		w = wc;
        		i = j;
        	}
        }
        int[] answer = new int[result.size()];
        for(int i = 0; i<result.size(); i++) {
        	answer[i] = result.get(i);
        }
        return answer;
    }
	
	public static void main(String[] args){
		Solution sol = new Solution();
		String msg = "ABABABABABABABAB";
		System.out.println(Arrays.toString(sol.solution(msg)));

	}

}
