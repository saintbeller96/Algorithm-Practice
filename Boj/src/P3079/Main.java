package P3079;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static long max;
	private static long[] T;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		T = new long[N];
		for(int i = 0; i<N; i++) {
			T[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, T[i]);
		}
		max *= M;
		long s = 0;
		long e = max;
		System.out.println(binarySearch(s, e));		
	}
	
	private static long binarySearch(long s, long e) {
		long result = e;
		long mid = -1;
		while(e > s) {
			mid = (e + s)/2;
			long p = 0;
			for(int i = 0; i<N; i++) {
				p += mid/T[i];
			}
			
			if(p < M) {
				s = mid+1;
			}else {
				result = result > mid? mid : result;
				e = mid;
			}		
		}
		return result;
	}
}