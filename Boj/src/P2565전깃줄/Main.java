package P2565전깃줄;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		int[] LIS = new int[N+1];
		
		for(int i = 0; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(stk.nextToken());
			arr[i][1] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(arr, (a, b)->Integer.compare(a[0], b[0]));
		
		for(int i = 0; i<=N; i++) {
			LIS[i] = Integer.MAX_VALUE;
		}
		
		int max = 0;
		int len = 1;
		LIS[0] = Integer.MIN_VALUE;
		for(int i = 0; i<N; i++) {
			int idx = bs(LIS, 0, len, arr[i][1]);
			if(LIS[idx] > arr[i][1]) {
				if(LIS[idx] == Integer.MAX_VALUE) len++;
				LIS[idx] = arr[i][1];
				max = Math.max(max, idx);
			}
		}
		System.out.println(N - max);		
	}
	
	static int bs(int[] LIS, int s, int e, int value) {
		while(s < e) {
			int mid = (s + e)/2;
			if(LIS[mid] < value) {
				s = mid +1;
			}else {
				e = mid;
			}
		}		
		return e;
	}
}
