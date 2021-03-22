package P13422도둑;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M, K;
	static int[] houses;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			stk = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			K = Integer.parseInt(stk.nextToken());
			
			houses = new int[N];
			
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				houses[i] = Integer.parseInt(stk.nextToken());
			}
			
			int money = 0;
			int answer = 0;
			for(int i = 0; i<M; i++) {
				money += houses[i];
			}
			
			if(N == M) {
				if(money < K) System.out.println(1);
				else System.out.println(0);
			}
			////
			for(int l = 0; l<N; l++) {
				if(money < K) answer++;
				money = money - houses[l] + houses[(l+M)%N];
			}
			
			System.out.println(answer);
		}
 	}
}
