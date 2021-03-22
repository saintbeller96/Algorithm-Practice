package P9708가장긴수열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N;
	private static int[] arr;
	private static boolean[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			stk = new StringTokenizer(br.readLine());
			arr = new int[N];
			dp = new boolean[N];
			for(int i = 0; i<N; i++) {			
				arr[i] = Integer.parseInt(stk.nextToken());				
			}			
			Arrays.sort(arr);
			int answer = 0;
			for(int i = 0; i < N; i++) {
				int cur = arr[i];
				int cnt = 1;
				if(dp[i]) {
					continue;
				}
				if(N-i <= answer) {
					break;
				}
				for(int j = i+1; j < N; j++) {
					if(arr[j]%cur == 0) {
						cnt++;
						dp[j] = true;
						cur = arr[j];
					}
				}
				
				answer = answer > cnt ? answer:cnt;
			}
			System.out.println("#" + t + " " + answer);
		}
	}
}
