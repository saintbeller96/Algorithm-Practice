package P1202보석도둑;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		
		int[][] gems = new int[N][2];
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			gems[i][0] = Integer.parseInt(stk.nextToken());
			gems[i][1] = Integer.parseInt(stk.nextToken());
		}
		
		Arrays.sort(gems, (a, b)->Integer.compare(a[0], b[0]));
		
		long[] bag = new long[K];
		for(int i = 0; i<K; i++) {
			bag[i] = Long.parseLong(br.readLine());
		}
		
		Arrays.sort(bag);
		PriorityQueue<Integer> pque = new PriorityQueue<Integer>((a, b)->Integer.compare(b, a));	
		long sum = 0;
		int idx = 0;
		for(int i = 0; i<K; i++) {
			for(int j = idx; j<N; j++) {
				if(gems[j][0] <= bag[i]) {
					pque.offer(gems[j][1]);
					idx++;
				}else {
					break;
				}
			}
			if(!pque.isEmpty()) {
				sum += pque.poll();
			}	
		}
		
		System.out.println(sum);
	}

}
