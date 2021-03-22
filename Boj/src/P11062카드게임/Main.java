package P11062카드게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringBuilder sb = new StringBuilder();
		StringTokenizer stk = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T; t++) {
			int answer = 0;
			int N = Integer.parseInt(br.readLine());
			int[] cards = new int[N];
			stk = new StringTokenizer(br.readLine());
			dp = new int[2][N+1][N+1];
			for(int i = 0; i<N; i++) {
				cards[i] = Integer.parseInt(stk.nextToken());
			}
			
			
			
			
			sb.append(answer).append('\n');
		}
		System.out.println(sb);

	}
	
	static int myDp(int order, int l, int r) {
		//ī�尡 ���� ���� ���
		if(l == r) {
			
		}
		
		
		
		return 0;
	}

}
