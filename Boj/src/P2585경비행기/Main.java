package P2585경비행기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, K;
	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}	
	}
	static Point start = new Point(0, 0);
	static Point end = new Point(10000, 10000);
	static Point[] points;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		points = new Point[N+1];
		
		points[0] = new Point(0, 0);
		for(int i= 1 ; i<=N; i++) {
			stk = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			points[i] = new Point(x, y);
		}
		
		int s = 0, e = calFuelAmount(start, end);
		int answer = Integer.MAX_VALUE;
		while(s<=e) {
			int mid = (s+e)/2;
			if(canArrive(mid)) {
				answer = Math.min(answer, mid);
				e = mid-1;
			}else {
				s = mid+1;
			}
		}
		System.out.println(answer);
		return;
	}
 	
 	static boolean canArrive(int k) {
 		Queue<int[]> que = new LinkedList<>();
 		boolean[] visited = new boolean[N+1];
 		visited[0] = true;
 		que.offer(new int[] {0, 0}); 		
 		while(!que.isEmpty()) {
 			int[] p = que.poll();
 			int pid = p[0], cnt = p[1];
 			
 			for(int i = 1; i<=N; i++) {
 				if(cnt+1 > K || visited[i]) continue;
 				if(calFuelAmount(points[pid], points[i]) > k) continue;
 				if(calFuelAmount(points[i], end) <= k) return true;
 				
 				que.offer(new int[] {i, cnt+1});
 				visited[i] = true;
 			}
 		}
 		return false;
 	}
 	
 	static int calFuelAmount(Point p1, Point p2) {
 		return (int)(Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2)))/10+1;
 	}
}