package P3078좋은친구;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		
		String[] name = new String[N];
		for(int i = 0; i<N; i++) {
			name[i] = br.readLine();
		}
		
		int[] window = new int[21];
		long answer = 0;
		
		for(int i = 0; i<=K; i++) {
			int len = name[i].length();
			window[len]++;
			if(window[len] > 1) answer+=window[len]-1;
		}
		
		for(int i = K+1; i<N; i++) {
			int len = name[i-K-1].length();
			window[len]--;
			len = name[i].length();
			window[len]++;
			if(window[len] > 1) answer+=window[len]-1;
		}
		System.out.println(answer);
	}
}
