package P17942알고리즘공부;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int M, N, R;
	static int[] K;
	static ArrayList<int[]>[] adjList;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = new int[N+1];
		adjList = new ArrayList[N+1];
		
		int max = 0;
		
		stk = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			K[i] = Integer.parseInt(stk.nextToken());
			adjList[i] = new ArrayList<int[]>();
			max = Math.max(max, K[i]);
		}
		
		R = Integer.parseInt(br.readLine());
		for(int i = 0; i<R; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int d = Integer.parseInt(stk.nextToken());		
			adjList[a].add(new int[] {b, d});
		}
		
		int s = 1, e = max;
		int answer = max;
		while(s<=e) {
			int mid = (s+e)/2;
			if(bfs(mid)) {
				answer = Math.min(answer, mid);
				e = mid-1;
			}else {
				s = mid+1;
			}
		}
		System.out.println(answer);
		return;
	}
 	
 	static boolean bfs(int m) {
 		Queue<Integer> que = new LinkedList<>();
 		boolean[] learned = new boolean[N+1];
 		int[] costs = new int[N+1];
 		
 		int cnt = 0;
 		//m 이하의 공부량으로 공부할 수 있는 알고리즘을  큐에 추가
 		for(int i = 1; i<=N; i++) {
 			costs[i] = K[i];
 			if(K[i] <= m) {
 				cnt++;
 				learned[i] = true;
 				que.offer(i);
 			}
 		}
 		//공부한 알고리즘의 개수가 M 이상이면 true
 		if(cnt >= M) return true;
 		
 		while(!que.isEmpty()) {
 			int algo = que.poll();
 			for(int[] edge : adjList[algo]) {
 				int v = edge[0];
 				int cost = edge[1];
 				if(learned[v]) continue;
 				costs[v] -= cost;
 				if(costs[v] <= m) {
 					learned[v] = true;
 					que.offer(v);
 					cnt++;
 					if(cnt >= M) return true;
 				}
 			}
 		}
 		return false;
 	}
}
