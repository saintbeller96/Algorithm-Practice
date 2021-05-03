package K2019후보키;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class Solution {
	private static int N, R;
	private static int[] indexes;

	public int solution(String[][] relation) {
		R = relation.length;
		N = relation[0].length;
		indexes = new int[N];
		for(int i = 0 ; i<N; i++) {
			indexes[i] = i;
		}
		TreeSet<Integer> keySet = new TreeSet<Integer>();
		for(int i = 1; i< 1 << N; i++) {
			ArrayList<Integer> candiKey = new ArrayList<Integer>();
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j<N; j++) {
				if((i & (1<<j)) != 0) {
					sb.append(1);
					candiKey.add(indexes[j]);
				}else {
					sb.append(0);
				}
			}
			int candi = Integer.parseInt(sb.toString(), 2);
			boolean flag = canCandidateKey(candiKey.toArray(new Integer[0]), relation);
			if(flag) {
				boolean check = true;
				for(int c : keySet.toArray(new Integer[0])) {
					if((c&candi) == candi) {
						keySet.remove(c);
					}else if((c&candi) == c) {
						check = false;
					}
				}
				if(check) {
					keySet.add(candi);
					System.out.println(sb);
				}
			}
		}
		return keySet.size();
	}
	
	private boolean canCandidateKey(Integer[] combKey, String[][] relation) {
		HashSet<String> hs = new HashSet<String>();
		for(int i = 0; i<R; i++) {
			StringBuilder sb = new StringBuilder();
			for (int idx : combKey) {
				sb.append(relation[i][idx]).append(' ');
			}
			hs.add(sb.toString());
		}
		if(hs.size() == R) return true;
		return false;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		String[][] relation = {{"100","ryan","music","2"},
								{"200","apeach","math","2"},
								{"300","tube","computer","3"},
								{"400","con","computer","4"},
								{"500","muzi","music","3"},
								{"600","apeach","music","2"}};
		System.out.println(s.solution(relation));
		
	}

}
