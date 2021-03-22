package P17420깊콘이넘쳐흘러;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		int N = Integer.parseInt(br.readLine());
		
		long[][] A = new long[N][2];
		stk = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			A[i][0] = i;
			A[i][1] = Integer.parseInt(stk.nextToken());
		}
		
		Arrays.sort(A, (a, b)->Long.compare(a[1], b[1]));
		
		long[] B = new long[N];
		stk = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			B[i] = Integer.parseInt(stk.nextToken());
		}
		
		
		
		
	}
}
