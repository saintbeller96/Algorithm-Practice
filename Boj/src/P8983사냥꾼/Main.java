package P8983사냥꾼;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int M, N, L;
	static int[] shooter;
	static int[][] animals;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(stk.nextToken());
		L = Integer.parseInt(stk.nextToken());
		shooter = new int[M];
		animals = new int[N][2];
		stk = new StringTokenizer(br.readLine());
		for(int i = 0; i<M; i++) {
			shooter[i] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(shooter);
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			animals[i][0] = Integer.parseInt(stk.nextToken());
			animals[i][1] = Integer.parseInt(stk.nextToken());
		}
		int cnt = 0;
		for(int i = 0; i<N; i++) {
			int x = animals[i][0];
			int y = animals[i][1];
			if(y > L) continue;
			int ub = x+L-y;
			int lb = x+y-L;
			int s = 0;
			int e = M-1;
			while(s<=e) {
				int mid = (s+e)/2;
				if(lb <= shooter[mid] && shooter[mid] <=ub) {
					cnt++;
					break;
				}else if(shooter[mid] < lb) {
					s = mid+1;
				}else {
					e = mid-1;
				}
			}
			
		}
		
		System.out.println(cnt);
	}
}
