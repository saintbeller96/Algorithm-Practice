package P19847여우신탁;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] numbers;
	static int[] div;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		numbers = new int[N];
		int n = Integer.parseInt(stk.nextToken())-1;
		int max = 0;
		for(int i = 1; i<N; i++) {
			numbers[i] = Integer.parseInt(stk.nextToken());
			max = Math.max(max, numbers[i]);
		}
		
		div = new int[max];
		
		for(int i = 1; i<N; i++) {
			
		}
		
		double answer = 0.0;
		
		
		System.out.println(answer/(n+1));

	}

}
