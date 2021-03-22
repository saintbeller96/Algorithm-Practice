package P2623음악프로그램;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] adjList;
	static int[] in_degree;//진입차수 저장
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		adjList = new ArrayList[N+1];
		in_degree = new int[N+1];
		for(int i = 1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stk.nextToken());
			int a = Integer.parseInt(stk.nextToken());
			for(int j = 1; j<n; j++) {
				int b = Integer.parseInt(stk.nextToken());
				boolean flag = false;
				for(int k : adjList[a]) {
					//이미 인접 리스트로 추가되었으면 넣지 않음
					if(k == b) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					adjList[a].add(b);
					in_degree[b]++;//진입차수 증가
				}
				a = b;
			}
		}
		
		tps();
		return;
	}
 	
 	static void tps() {
 		StringBuilder sb = new StringBuilder();
 		Queue<Integer> que = new LinkedList<>();
 		//진입 차수가 0인 정점부터 큐에 삽입
 		for(int v = 1; v<=N; v++) {
 			if(in_degree[v] == 0) {
 				que.offer(v);
 			}
 		}
 		int cnt = 0;
 		while(!que.isEmpty()) {
 			int u = que.poll();
 			sb.append(u).append('\n');
 			cnt++;
 			
 			for(int v : adjList[u]) {
 				//연결된 정점의 진입 차수를 감소
 				in_degree[v]--;
 				if(in_degree[v] == 0) {
 					que.offer(v);
 				}
 			}
 		}
 		
 		if(cnt != N) {
 			System.out.println(0);
 			return;
 		}
 		
 		System.out.println(sb);
 	}
}
