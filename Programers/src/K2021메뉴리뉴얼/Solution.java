package K2021메뉴리뉴얼;

import java.util.*;
import java.util.Map.Entry;

public class Solution {
	int max = 0;
	public String[] solution(String[] orders, int[] course) {
		Set<String> answer = new TreeSet<String>();
		Map<Character, Integer> items = new HashMap<>();
		for(String s : orders) {
			for(char c : s.toCharArray()) {
				items.put(c, items.getOrDefault(c, 0)+1);
			}
		}
		List<Character> valid_items = new ArrayList<>();
		for(Entry<Character, Integer> entry : items.entrySet()) {
			if(entry.getValue() >= 2) valid_items.add(entry.getKey());
		}
		
		
		
		for(int r : course) {
			Map<Integer, List<String>> map = new TreeMap<>();
			max = 0;
			combination(valid_items.size(), r, 0, 0, new int[r], orders, valid_items, map);
			List<String> list = map.get(max);
			if(list != null) answer.addAll(list);
		}	
		
		return answer.toArray(new String[0]);
	}
	
	private void combination(int n, int r, int depth, int start, int[] comb, String[] orders, List<Character> valid_items, Map<Integer, List<String>> map) {
		if(depth == r) {
			int cnt = 0;
			String item = "";
			for(int i = 0; i<r; i++) {
				char c = valid_items.get(comb[i]);
				item+=c;
			}
			for(String str: orders) {
				if(myContains(str, item)) cnt++;
			}
			if(cnt >= 2) {
				map.putIfAbsent(cnt, new ArrayList<>());
				map.get(cnt).add(item);
				max = Math.max(max,cnt);
			}
			return;
		}
		
		for(int i = start; i<n; i++) {
			comb[depth] = i;
			combination(n, r, depth+1, i+1, comb, orders, valid_items, map);
		}
	}
	
	private boolean myContains(String str, String item) {
		for(char c : item.toCharArray()) {
			if(!str.contains(c+"")) return false;
		}
		return true;
	}


	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
		int[] course = {2,3,5};
		String[] answer = s.solution(orders, course);
		System.out.println(answer);
	}
}
