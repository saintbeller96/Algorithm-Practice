package P174267회사문화;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, M;
	static int[] answer;
	static class Node{
		int n;
		List<Integer> children = new ArrayList<>();
		public Node(int n) {
			this.n = n;
		}
	}
	static Node[] managers;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		stk = new StringTokenizer(br.readLine());
		managers = new Node[N+1];
		for(int i = 1; i<=N; i++) {
			managers[i] = new Node(i);
		}
		answer = new int[N+1];
		for(int i = 1; i<=N; i++) {
			int k = Integer.parseInt(stk.nextToken());
			if(k!= -1) managers[k].children.add(i);
			
		}
		int[] temp = new int[N+1];
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int e = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			temp[e] += w;
		}
		for(int i = 1; i<=N; i++) {
			if(temp[i] > 0) {
				dfs(i, temp[i]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<=N; i++) {
			sb.append(answer[i]).append(' ');
		}
		System.out.println(sb);
	}
	static void dfs(int i, int w) {
		answer[i]+=w;
		for(int c : managers[i].children) {
			dfs(c, w);
		}
	}
}
