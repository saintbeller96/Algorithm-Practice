package P20955민서의응급수술;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int M, N, L;
	static List<Integer>[] adjList;
	static int[] set;
	static int groups = 0;
	static int cycles = 0;
	static int order = 1;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		adjList = new List[N+1];
		set = new int[N+1];
		for(int i = 0; i<=N; i++) {
			adjList[i] = new ArrayList<>();
			set[i] = i;
		}
		
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		int label = 0;
		int[] visited = new int[N+1];
		for(int i = 1; i<=N; i++) {
			if(visited[i] != 0) continue;
			dfs(visited, i, -1, ++label);
		}
		
		System.out.println(label-1 + cycles);
	}
 	
 	static void dfs(int[] visited, int cur, int prev, int label) {	
 		set[cur] = label;
 		visited[cur] = order++;
 		for(int v : adjList[cur]) {
 			//v가 직전에 방문하지 않았고 이전에 방문했다면 사이클임
 			if(prev == v) continue;
 			if(visited[v] == 0) {
 				dfs(visited, v, cur, label);
 			}else if(visited[cur] > visited[v]) {
 				cycles++;
 			}
 		}
 	}
}
