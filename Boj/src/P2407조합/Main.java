package P2407조합;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static String[][] dp;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringTokenizer stk = new StringTokenizer(scan.nextLine());
		
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		dp = new String[101][101];
		for(int i = 0; i<101; i++) {
			Arrays.fill(dp[i], "");
		}
		System.out.println(combination(n, m));
	}
	
	static String combination(int n, int m) {
		if(n == m || m == 0) return "1";
		if(!dp[n][m].equals("")) return dp[n][m];
		
		String n1 = combination(n-1, m-1);
		String n2 = combination(n-1, m);
		dp[n][m] = convert2String(n1, n2);
		return dp[n][m];
	}
	
	static String convert2String(String num1, String num2) {
		StringBuilder sb = new StringBuilder();
		int idx1 = num1.length()-1;
		int idx2 = num2.length()-1;
		
		int temp = 0;
		while(idx1 >= 0 || idx2 >= 0) {
			int n = temp;
			if(idx1 >= 0) {
				n += num1.charAt(idx1) - '0';
				idx1--;
			}
			
			if(idx2 >=0) {
				n += num2.charAt(idx2) - '0';
				idx2--;
			}
			
			temp = n/10;
			n = n%10;
			sb.append(n);
		}
		if(temp == 1) sb.append(1);
		return sb.reverse().toString();
	}
}
