package P1912연속합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int sum = 0;
		int answer = Integer.MIN_VALUE;
		for(int i = 0; i<N; i++) {
			int n = Integer.parseInt(stk.nextToken());
			sum += n;
			answer = Math.max(answer, sum);
			if(sum < 0) sum = 0;
			
		}
		System.out.println(answer);
	}

}
