package P17270연예인은힘들어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int V, M, J, S;
	static ArrayList<int[]>[] adjList;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		V = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		adjList = new ArrayList[V+1];
		for(int i = 1; i<=V; i++) {
			adjList[i] = new ArrayList<int[]>();
		}
		
		for(int i = 1; i<=M; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			
			adjList[a].add(new int[] {b, c});
			adjList[b].add(new int[] {a, c});
		}
		stk = new StringTokenizer(br.readLine());
		J = Integer.parseInt(stk.nextToken());
		S = Integer.parseInt(stk.nextToken());
		
		int[] dist_J = djikstra(J);
		int[] dist_S = djikstra(S);
		int min = Integer.MAX_VALUE;
		for(int v= 1; v<=V; v++) {
			int j_dist = dist_J[v];
			int s_dist = dist_S[v];
			if(v == J || v == S || j_dist == Integer.MAX_VALUE || s_dist == Integer.MAX_VALUE ) continue;
			min = Math.min(min, j_dist + s_dist);
		}
		
		ArrayList<int[]> answer = new ArrayList<>();
		for(int v= 1; v<=V; v++) {
			int j_dist = dist_J[v];
			int s_dist = dist_S[v];
			if(v == J || v == S || j_dist == Integer.MAX_VALUE || s_dist == Integer.MAX_VALUE ) continue;
			if(min == j_dist + s_dist && j_dist <= s_dist) {
				answer.add(new int[] {j_dist, v});
			}
		}
		
		Collections.sort(answer, (a, b)->Integer.compare(a[0], b[0]));
		
		if(answer.size() == 0) {
			System.out.println(-1);
		}else {
			System.out.println(answer.get(0)[1]);
		}
	}
 	
 	static int[] djikstra(int start) {
 		int[] dist = new int[V+1];
 		for(int i = 1; i<=V; i++) {
 			dist[i] = Integer.MAX_VALUE;
 		}
 		dist[start] = 0;
 		
 		PriorityQueue<int[]> pque = new PriorityQueue<int[]>((a, b)->Integer.compare(a[1], b[1]));
 		pque.offer(new int[] {start, 0});
 		
 		while(!pque.isEmpty()) {
 			int[] p = pque.poll();
 			int src = p[0], cost = p[1];
 			
 			if(dist[src] >= cost) {
 				for(int[] v : adjList[src]) {
 					int dest = v[0];
 					int addCost = v[1];
 					if(dist[dest] > cost + addCost) {
 						dist[dest] = cost + addCost;
 						pque.offer(new int[] {dest, dist[dest]});
 					}
 				}
 			}
 		}
 		
 		return dist;
 	}
}
