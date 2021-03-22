package K2020보석쇼핑;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
	
	public int[] solution(String[] gems) {
		int[] answer = {0, 0};
		//������ �󵵼�
		HashMap<String, Integer> tm = new HashMap<String, Integer>();
        HashSet<String> hs = new HashSet<String>();
		for (String s : gems) {
			hs.add(s);
		}
		int len = hs.size();
		int dist = Integer.MAX_VALUE;
		int r = 0, l = 0;
		while(r <= gems.length && l <gems.length) {
			if(tm.size() < len) {
				if(r >= gems.length) break;
				if(tm.containsKey(gems[r])) {
					tm.put(gems[r], tm.get(gems[r])+1);
				}else {
					tm.put(gems[r], 1);
				}
				r++;
			}
			if(tm.size() == len) {
				if(dist > r - l +1) {
					dist = r - l +1;
					answer[0] = l+1;
					answer[1] = r;
				}
				tm.put(gems[l], tm.get(gems[l])-1);
				if(tm.get(gems[l]) == 0) tm.remove(gems[l]);
				l++;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		String[] str1 = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		String[] str2 = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
		String[] str3 = {"C", "C", "C", "A", "B"};
		System.out.println(Arrays.toString(s.solution(str3)));

	}

}
