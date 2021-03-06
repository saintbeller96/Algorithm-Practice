package P11053최장증가수열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] LIS = new int[N+1];
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
			LIS[i] = Integer.MAX_VALUE;
		}
		LIS[N] = Integer.MAX_VALUE;
		
		int max = 0;
		LIS[0] = Integer.MIN_VALUE;
		for(int i = 0; i<N; i++) {
			int idx = bs(LIS, arr[i]);
			if(idx < N && LIS[idx] == Integer.MAX_VALUE || LIS[idx] >= arr[i]) {
				LIS[idx] = arr[i];
				max = Math.max(max, idx);
			}					
		}
		System.out.println(max);
		
	}
	
	static int bs(int[] LIS, int value) {
		int s = 0;
		int e = LIS.length;		
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

