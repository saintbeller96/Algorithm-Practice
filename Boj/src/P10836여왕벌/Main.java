package P10836여왕벌;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(stk.nextToken());
		int N = Integer.parseInt(stk.nextToken());

		int[] arr = new int[2*M-1];
		
		for(int k = 0; k < N; k++) {
			stk = new StringTokenizer(br.readLine());
			int zero = Integer.parseInt(stk.nextToken());
			int one = Integer.parseInt(stk.nextToken());
			int two = Integer.parseInt(stk.nextToken());
 			for(int i = zero; i<zero + one; i++) {
 				arr[i] += 1;
 			}
 			for(int i = zero + one; i<zero + one + two; i++) {
 				arr[i] += 2;
 			}
		}
		
		for(int i = 0; i<M; i++) {
			for(int j = 0; j<M; j++) {
				if(j == 0) {
					System.out.print((arr[M-1-i]+1) + " ");
				}else {
					System.out.print((arr[M-1+j]+1) + " ");
				}
			}
			System.out.println();
		}
	}

}
