package P5607조합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static final int P = 1234567891;
	static long answer = 0;
	static long[] factorial;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		factorial = new long[1000001];
		factorial(1000000);
		for(int t = 1; t<=T; t++) {
			answer = 0;
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stk.nextToken());
			int r = Integer.parseInt(stk.nextToken());
			long a = factorial[n];
			long b = factorial[n-r]%P;
			long c = factorial[r]%P;
			answer = a*pow(b*c%P, P-2)%P;
			System.out.println("#" + t + " " + answer);
		}
	}
	
	static long factorial(int n) {
		factorial[0] = 1;
		for(int i = 1; i<= 1000000; i++) {
			factorial[i] = ((factorial[i-1]%P)*i)%P;
		}
		return factorial[n];
	}
	
	static long pow(long n, int p) {
		if(p == 0) return 1;
		else if(p == 1) return n;
		
		if(p%2 == 0) {
			long a = pow(n, p/2)%P;
			return (a*a)%P;
		}else {
			long a = pow(n, p-1)%P;
			return (a*n)%P;
		}
	}
}
