package P20437문자열게임2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int T, K;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 0; tc<T; tc++) {
			String W = br.readLine();
			K = Integer.parseInt(br.readLine());
			if(K == 1) {
				sb.append(1).append(' ').append(1).append('\n');
				continue;
			}
			Map<Character, List<Integer>> cmap = new HashMap<>();
			for(int i = 0; i<W.length(); i++) {
				char c = W.charAt(i);
				cmap.putIfAbsent(c, new ArrayList<>());
				cmap.get(c).add(i);
			}
			int min = Integer.MAX_VALUE;
			int max = 0;
			for(Entry<Character, List<Integer>> entry : cmap.entrySet()) {
				List<Integer> idxList = entry.getValue();
				if(idxList.size() < K) continue;
				for(int i = 0; i<=idxList.size()-K; i++) {
					int s = i, e = i+K-1;
					int len = idxList.get(e) - idxList.get(s) + 1;
					min = Math.min(len, min);
					max = Math.max(len, max);
				}
			}
			if(min == Integer.MAX_VALUE && max == 0) sb.append(-1).append('\n');
			else sb.append(min).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}
 	
}
