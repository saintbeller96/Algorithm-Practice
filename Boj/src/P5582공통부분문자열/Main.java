package P5582공통부분문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		int n = Math.max(str1.length(), str2.length());
		dp = new int[n][n];
		int max = 0;
		for(int i = 0; i<str1.length(); i++) { 
			char c1 = str1.charAt(i);
			for(int j = 0; j < str2.length(); j++) {
				char c2 = str2.charAt(j);
				if(c1 == c2) {
					if(i == 0 || j == 0) {
						dp[i][j] = 1;
					}else {
						dp[i][j] = dp[i-1][j-1] + 1;
					}
					max = Math.max(max, dp[i][j]);
				}
				
			}
		}
		System.out.println(max);
	}
}
