package P17954투튜브;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//1일때는 예외처리
		if(N == 1) {
			System.out.println(2);
			System.out.println(1);
			System.out.println(2);
			return;
		}
		
		ArrayList<Integer> tube1 = new ArrayList<Integer>();
		ArrayList<Integer> tube2 = new ArrayList<Integer>();
		
		//2N-3 2N-4 .... N-1 2N-2
		//2N-1  N-2 ....  1   2N
		
		for(int i = 2*N-3; i>=N-1; i--) {
			tube1.add(i);
		}
		tube1.add(2*N-2);
		
		tube2.add(2*N-1);
		for(int i = N-2; i>=1; i--) {
			tube2.add(i);
		}
		tube2.add(2*N);
		
		
		long total = (long)2*N*N+N;// 1~2N 합
		long answer = 0;
		for(int i = 0; i<N; i++) {
			total -= tube1.get(i);
			answer += (i+1)*(total);
		}
		for(int i = 0; i<N; i++) {
			total -= tube2.get(i);
			answer += (N+i+1)*(total);
		}

		
		System.out.println(answer);
		for(int i : tube1) {
			System.out.print(i + " ");
		}
		System.out.println();
		for(int i : tube2) {
			System.out.print(i + " ");
		}
	}
}
