package P9470Strahler순서;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int T, K, M, P;
	static List<Integer>[] inAdjList;//나가는 간선
	static List<Integer>[] outAdjList;//들어오는 간선
	static int[] nodes;
	static int[] inDegree;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			K = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			P = Integer.parseInt(stk.nextToken());
			inAdjList = new List[M+1];
			outAdjList = new List[M+1];
			nodes = new int[M+1];
			inDegree = new int[M+1];
			for(int i = 0; i<=M; i++) {
				inAdjList[i] = new ArrayList<>();
				outAdjList[i] = new ArrayList<>();
			}
			for(int i = 0; i<P; i++) {
				stk = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(stk.nextToken());
				int b = Integer.parseInt(stk.nextToken());
				outAdjList[a].add(b);
				inAdjList[b].add(a);
				inDegree[b]++;
			}
			System.out.println(tc + " " + tps());
		}
		return;
	}
 	static int tps() {
 		Queue<Integer> que = new LinkedList<>();
 		//진입 차수가 0인 정점부터 큐에 넣음
 		for(int v = 1; v<=M; v++) {
 			if(inDegree[v] == 0) {
 				que.offer(v);
 				nodes[v] = 1;
 			}
 		}
 		
 		while(!que.isEmpty()) {
 			int p = que.poll();
 			
 			for(int v : outAdjList[p]) {
 				inDegree[v]--;
 				if(inDegree[v] == 0) {
 					que.offer(v);
 					int max = 0;
 					for(int u : inAdjList[v]) {
 						max = Math.max(max, nodes[u]);
 					}
 					int cnt = 0;
 					for(int u : inAdjList[v]) {
 						if(max == nodes[u]) cnt++;
 					}
 					if(cnt <= 1) {
 						nodes[v] = max;
 					}else {
 						nodes[v] = max+1;
 					}
 				}
 			}
 		}
 		
 		int answer = 0;
 		for(int i = 0; i<=M; i++) {
 			answer = Math.max(answer, nodes[i]);
 		}
 		return answer;
 	}
}
