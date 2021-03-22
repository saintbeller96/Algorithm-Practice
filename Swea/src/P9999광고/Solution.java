package P9999광고;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N;
	private static long L;
	private static int[] ad;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine());
			L = Long.parseLong(stk.nextToken());
			N = Integer.parseInt(stk.nextToken());			
			for(int i = 0; i<N; i++) {
				stk = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(stk.nextToken());
				int e = Integer.parseInt(stk.nextToken());
				
				
			}
		}

	}

}
