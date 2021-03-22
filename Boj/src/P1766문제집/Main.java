package P1766문제집;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int M, N;
	static List<Integer>[] adjList;
	static int[] in_degrees;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		in_degrees = new int[N+1];
		adjList = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			in_degrees[b]++;
			adjList[a].add(b);
		}
		
		tps();

	}
 	
 	static void tps() {
 		PriorityQueue<Integer> pque = new PriorityQueue<>();
 		
 		for(int i = 1; i<=N; i++) {
 			if(in_degrees[i] == 0) pque.offer(i);
 		}
 		
 		StringBuilder sb = new StringBuilder();
 		while(!pque.isEmpty()) {
 			int p = pque.poll();
 			sb.append(p).append(' ');
 			for(int o : adjList[p]) {
 				in_degrees[o]--;
 				if(in_degrees[o] == 0) {
 					pque.offer(o);
 				}
 			}
 		}
 		System.out.println(sb);
 	}
}
