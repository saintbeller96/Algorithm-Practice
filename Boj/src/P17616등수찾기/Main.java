package P17616등수찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, X;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		X = Integer.parseInt(stk.nextToken());
		
		ArrayList<Integer>[] g_adjList = new ArrayList[N+1];
		ArrayList<Integer>[] l_adjList = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			g_adjList[i] = new ArrayList<>();
			l_adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			l_adjList[a].add(b);
			g_adjList[b].add(a);
		}
		
		int min = bfs(g_adjList)+1;
		int max = N-bfs(l_adjList);
		
		System.out.println(min + " " + max);
		return;
	}
 	
 	static int bfs(ArrayList<Integer>[] adjList) {
 		Queue<Integer> que = new LinkedList<>();
 		boolean[] visited = new boolean[N+1];
 		que.offer(X);
 		visited[X] = true;
 		int cnt = 0;
 		while(!que.isEmpty()) {
 			int u = que.poll();
 			cnt++;
 			for(int v : adjList[u]) {
 				if(!visited[v]) {
 					que.offer(v);
 					visited[v] = true;
 				}
 			}
 		}
 		return cnt-1;
 	}
}
