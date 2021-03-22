package P18235지금만나러갑니다;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, A, B;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		A = Integer.parseInt(stk.nextToken());
		B = Integer.parseInt(stk.nextToken());
		
		if((A+B)%2 == 1) {
			System.out.println(-1);
		}else {
			System.out.println(bfs());
		}
	}
 	static int bfs() {
 		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {A, B});
		int day = 1;
		while(!que.isEmpty()) {
			
			int len = que.size();
			for(int i = 0; i<len; i++) {
				int[] p = que.poll();
				int a = p[0], b = p[1];
				
				if(a == b) return day-1;
				
				int jump = (int)Math.pow(2, day-1);
				
				int a1 = a + jump;
				int a2 = a - jump;
				int b1 = b + jump;
				int b2 = b -jump;
				if(a1 >=0 && a1<=N && b1>=0 && b1<=N) {
					que.offer(new int[] {a1, b1});
				}
				if(a1 >=0 && a1<=N && b2>=0 && b2<=N) {
					que.offer(new int[] {a1, b2});
				}
				if(a2 >=0 && a2<=N && b1>=0 && b1<=N) {
					que.offer(new int[] {a2, b1});
				}
				if(a2 >=0 && a2<=N && b2>=0 && b2<=N) {
					que.offer(new int[] {a2, b2});
				}
			}
			day++;
		}
		return -1;
 	}
}
