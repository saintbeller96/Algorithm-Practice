package P20191줄임말;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine().trim();
		String T = br.readLine().trim();
		
		Map<Character, ArrayList<Integer>> map = new HashMap<>();
		for(int i = 0; i<T.length(); i++) {
			char t = T.charAt(i);
			if(!map.containsKey(t)) map.put(t, new ArrayList<>());
			map.get(t).add(i);
		}
		
		int n = 1;
		int idx = -1;
		for(int i = 0; i<S.length(); i++) {
			char s = S.charAt(i);
			if(!map.containsKey(s)) {
				n = -1;
				break;
			}
			ArrayList<Integer> list = map.get(s);
			idx = indexOf(list, idx+1);
			if(idx == -1) {
				n++;
				idx = indexOf(list, 0);
			}
		}
		
		System.out.println(n);
	}
 	
 	static int indexOf(ArrayList<Integer> list, int index) {
 		int s = 0, e = list.size();
 		while(s < e) {
 			int mid = (s+e)/2;
 			if(list.get(mid) >= index) e = mid;
 			else s = mid+1;
 		}
 		if(e == list.size()) return -1;
 		return list.get(e);
 	}
}
