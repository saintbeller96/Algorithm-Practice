package P2502떡먹는호랑이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int D = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		
		if(D == 3) {
			System.out.println(1);
			System.out.println(K-1);
			return;
		}
		
		long[] fibo = new long[31];
		fibo[1] = 1;
		fibo[2] = 1;
		
		for(int i = 3; i<=30; i++) {
			fibo[i] = fibo[i-1] + fibo[i-2];
		}
		
		for(int A = 1; A<100001; A++) {
			for(int B = A; B<100001; B++) {
				if(K == fibo[D-2]*A + fibo[D-1]*B) {
					System.out.println(A);
					System.out.println(B);
					return;
				}
			}
		}
		
	}

}
