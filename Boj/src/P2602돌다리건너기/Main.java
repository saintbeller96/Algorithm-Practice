package P2602돌다리건너기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][][] dp;
	static String scroll;
	static String[] stones;
	static int len;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		scroll = br.readLine();
		stones = new String[2];
		stones[0] = br.readLine();
		stones[1] = br.readLine();
		len = stones[0].length();
		
		dp = new int[2][len][scroll.length()];
		for(int k = 0; k<2; k++) {
			for(int i = 0; i<len; i++) {
				Arrays.fill(dp[k][i], -1);
			}
		}
		
		int answer = 0;
		for(int i = 0; i<len; i++) {
			if(stones[0].charAt(i) == scroll.charAt(0)) {
				answer += expedition(0, i, 1);
			}
			if(stones[1].charAt(i) == scroll.charAt(0)) {
				answer += expedition(1, i, 1);
			}
		}
		System.out.println(answer);
	}
	static int expedition(int k, int stone, int depth) {
		if(depth >= scroll.length()) return 1;
		if(dp[k][stone][depth] != -1) return dp[k][stone][depth];
		
		dp[k][stone][depth] = 0;
		for(int i = stone+1; i<len; i++) {
			if(stones[k^1].charAt(i) == scroll.charAt(depth)) {
				dp[k][stone][depth] += expedition(k^1, i, depth+1);
			}
		}
		return dp[k][stone][depth];
	}
}
