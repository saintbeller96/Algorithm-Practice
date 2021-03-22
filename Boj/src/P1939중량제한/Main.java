package P1939중량제한;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, M;
	static int A, B;
	static class Bridge{
		int from, to, cost;
		public Bridge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	static List<Bridge>[] adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		adjList = new List[N+1];
		for(int i = 0; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		int max = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			adjList[a].add(new Bridge(a, b, c));
			adjList[b].add(new Bridge(b, a, c));
			max = Math.max(max, c);
			min = Math.min(min, c);
		}
		
		stk = new StringTokenizer(br.readLine());
		A = Integer.parseInt(stk.nextToken());
		B = Integer.parseInt(stk.nextToken());
		
		int s = min;
		int e = max;
		int answer = min;
		while(s<=e) {
			int mid = (s+e)/2;
			if(bfs(mid)) {
				s = mid+1;
				answer = Math.max(answer, mid);
			}else {
				e = mid-1;
			}
		}
		System.out.println(answer);
	}
	
	static boolean bfs(int limit) {
		Queue<Integer> que = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		que.offer(A);
		visited[A] = true;
		
		while(!que.isEmpty()) {
			int from = que.poll();
			if(from == B) return true;
			
			for(Bridge bridge : adjList[from]) {
				if(bridge.cost < limit || visited[bridge.to]) continue;
				visited[bridge.to] = true;
				que.offer(bridge.to);
			}
		}
 		
		return false;
	}
}
