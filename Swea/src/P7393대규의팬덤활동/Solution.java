package P7393대규의팬덤활동;

import java.util.Scanner;

public class Solution {
	static int T, N;
	static int visit = (1<<10);
	static final int MOD = 1000000000;
	static long dp[][][];
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		T = scan.nextInt();
		for(int t = 1; t<=T; t++) {
			N = scan.nextInt();			
			dp = new long[101][10][visit];
			//[i][j][k] [자릿수][숫자][자릿수 -1 위치에서 앞단계 마킹]

			System.out.println("#" + t + " " + goDp());
		}
		
	}
	private static long goDp() {
		long sum = 0;
		for(int j = 1; j<10; j++) {
			dp[1][j][1<<j] = 1;			
		}
		
		for(int i = 1; i<= N; i++) {
			for(int j = 0; j < 10; j++) {
				for(int k = 0; k< visit; k++) {
					int bit = k | (1<<j);
					dp[i][j][bit] = (dp[i][j][bit]%MOD
								   + ((0<j ? dp[i-1][j-1][k] : 0)%MOD 
								   + ( 9>j ? dp[i-1][j+1][k] : 0)%MOD)%MOD)%MOD; 
				}
			}
		}
				
		for(int i = 0; i < 10; i++) {
			sum = (sum+dp[N][i][visit-1])%MOD;
		}
		
		return sum;
	}

}
