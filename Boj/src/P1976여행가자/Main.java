package P1976여행가자;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] table;
	static int[] labels;
	static int[] plan;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		table = new int[N+1][N+1];
		labels = new int[N+1];
		for(int i = 1; i<=N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				table[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		plan = new int[M];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(int i = 0; i<M; i++) {
			plan[i] = Integer.parseInt(stk.nextToken());
		}
		
		int label = 0;
		boolean[] visited = new boolean[N+1];
		for(int i = 1; i<=N; i++) {
			if(!visited[i]) {
				dfs(visited, i, ++label);
			}
		}
		int group = labels[plan[0]];
		boolean answer = true;
		for(int i = 1; i<M; i++) {
			if(labels[plan[i]] != group) {
				answer = false;
				break;
			}
		}
		
		System.out.println(answer?"YES":"NO");

	}
	static void dfs(boolean[] visited, int u, int label) {
		visited[u] = true;
		labels[u] = label;
		for(int i = 1; i<=N; i++) {
			int v = table[u][i];
			if(v == 0 || visited[i]) continue;
			dfs(visited, i, label);
		}
	}
}
