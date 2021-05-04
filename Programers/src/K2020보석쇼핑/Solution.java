package K2020보석쇼핑;

import java.util.*;

public class Solution {
	public int[] solution(String[] gems) {
		int[] answer = {1, gems.length+1};
		Set<String> set = new HashSet<>();
		for(String gem : gems) {
			set.add(gem);
		}
		
		Map<String, Integer> map = new HashMap<>();
		int l = 0;
		int r = 0;
		int len = gems.length;
		while(l<len) {
			if(map.size() == set.size()) {
				if(r-1-l < answer[1] - answer[0]) {
					answer[0] = l+1;
					answer[1] = r;
				}
				String gem = gems[l];
				map.put(gem, map.get(gem)-1);
				if(map.get(gem) == 0) {
					map.remove(gem);
				}
				l++;			
			}else{
				if(r>=len) break;
				String gem = gems[r];
				map.put(gem, map.getOrDefault(gem, 0)+1);
				r++;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		String[] str1 = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		String[] str2 = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
		String[] str3 = {"C", "C", "C", "A", "B"};
		String[] str4 = {"AA", "AB", "AC", "AA", "AC"};
		System.out.println(Arrays.toString(s.solution(str3)));
	}

}
