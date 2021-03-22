package P7465창용이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, M;
	private static int Answer;
	private static int[] set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			set = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				set[i] = i;
			}
			for (int i = 0; i < M; i++) {
				stk = new StringTokenizer(br.readLine());
				if(stk.countTokens() == 2) {
					int s1 = Integer.parseInt(stk.nextToken());
					int s2 = Integer.parseInt(stk.nextToken());
					union(s1, s2);
				}				
			}
			Answer = 0;
			for(int i = 1; i<= N; i++) {
				if(set[i]==i) {
					Answer++;
				}
			}
			System.out.println("#" + t+" " + Answer);
		}
	}
	private static void union(int s1, int s2) {
		set[find_next(s2)] = find_next(s1);
	}
	private static int find_next(int s) {
		if(set[s] != s) {
			set[s] = find_next(set[s]);
		}
		return set[s];
	}

}
