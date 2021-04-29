package P21320순간이동여행;

import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static final int MOD = 1000000007;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		long[] mem = new long[3001];
		mem[0] = mem[1] = mem[2] = 0L;
		mem[3] = 2L;
		long add = 2L;
		
		for(int i = 4; i<=N; i++) {			
			mem[i] = mem[i-1] + add;
			add += 4;
		}
		
		if((N+K)%2 == 0) {
			System.out.println(mem[N]);
		}else {
			System.out.println((mem[N]+1)%MOD);
		}
				
	}
}