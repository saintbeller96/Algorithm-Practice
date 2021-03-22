package P2138스위치;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] target;
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		int[] bulbs = new int[N];
		int[] bulbs2 = new int[N];
		target = new int[N];
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		for(int i = 0; i<N; i++) {
			bulbs[i] = s1.charAt(i) - '0';
			bulbs2[i] = bulbs[i] ;
			target[i] = s2.charAt(i) - '0';
		}
		
		push(bulbs, 0);
		
		
		
		
		push(bulbs2, 0);
		

	}
	static void push(int[] bulbs, int i) {		
		if(i -1 >= 0 && i+1 < N) {
			bulbs[i-1] ^= 1;
			bulbs[i] ^= 1;
			bulbs[i+1] ^= 1;
		}else if(i -1 == 0) {
			bulbs[i-1] ^= 1;
			bulbs[i] ^= 1;
		}else {
			bulbs[i+1] ^= 1;
			bulbs[i] ^= 1;
		}
	}
	
	static void simulation(int[] bulbs) {
		int pushes = 0;
		for(int i = 1; i<N; i++) {
			
		}
	}

}
