package P2560짚신벌레;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2560_{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		long[] newb = new long[d];
		newb[0] = 1L;
		int cnt = 1;
		long children = 0;
		
		while(cnt<=N) {
			int curIdx = cnt++%d;
			int next  = remain(curIdx-a,d);
			int prior = remain(curIdx-b,d);
			children+=newb[next];
			children-=newb[prior];	
			newb[curIdx]=children%1000;
		}
		
		long sum=0;
		for (int i = 0; i < d; i++) {
			sum+=newb[i];
		}
		System.out.println(sum%1000);
		

	}
	private static int remain(int p, int q) {
		if (p<0) {
			p+=(-p*q);
			return p%q;
		}return p;
	}
}
