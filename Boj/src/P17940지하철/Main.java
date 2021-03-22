package P17940지하철;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, M;
	static List<int[]>[] adjList;
	static boolean[] companies;
	static class Station{
		int n, cost, transfer_time;
		public Station(int n, int cost, int transfer_time) {
			this.n = n;
			this.cost = cost;
			this.transfer_time = transfer_time;
		}
		static int compare(Station s1, Station s2) {
			if(s1.transfer_time == s2.transfer_time) return Integer.compare(s1.cost, s2.cost);
			else return s1.transfer_time - s2.transfer_time;
		}
	}
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		adjList = new List[N];
		companies = new boolean[N];
		for(int i = 0; i<N; i++) {
			int b = Integer.parseInt(br.readLine());
			companies[i] = (b == 1)?true:false;
			adjList[i] = new ArrayList<>();
		}
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				int c = Integer.parseInt(stk.nextToken());
				if(c != 0) adjList[i].add(new int[] {j, c});
			}
		}
		int[] answer = djikstra();
		System.out.println(answer[0]+ " " + answer[1]);
		return;
	}
 	
 	static int[] djikstra() {
 		int[][] dist = new int[N][2];
 		for(int i = 0; i<N; i++) {
 			dist[i][0] = Integer.MAX_VALUE;
 			dist[i][1] = Integer.MAX_VALUE;
 		}
 		dist[0][0] = 0;
 		dist[0][1] = 0;
 		
 		PriorityQueue<Station> pque = new PriorityQueue<>(Station::compare);
 		pque.offer(new Station(0, 0, 0));
 		
 		while(!pque.isEmpty()) {
 			Station station = pque.poll();
 			int src = station.n;
 			int transfer_time = station.transfer_time;
 			int cost = station.cost;
 			
 			for(int[] edge : adjList[src]) {
 				int dest = edge[0];
 				int add_cost = edge[1];
 				int update_tt = transfer_time;
 				
 				if(companies[src] != companies[dest]) update_tt++;
 				
 				if(dist[dest][0] > update_tt) {
 					dist[dest][0] = update_tt;
 					dist[dest][1] = cost + add_cost;
 					pque.offer(new Station(dest, cost + add_cost, update_tt));
 				}else if(dist[dest][0] == update_tt && dist[dest][1] > cost + add_cost ) {
 					dist[dest][1] = cost + add_cost;
 					pque.offer(new Station(dest, cost + add_cost, update_tt));
 				}
 			}
 		}
 		return dist[M];
 	}
}
