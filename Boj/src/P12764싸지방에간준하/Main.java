package P12764싸지방에간준하;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		int N = Integer.parseInt(br.readLine());
		int[][] info = new int[N][2];
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(stk.nextToken());
			int q = Integer.parseInt(stk.nextToken());
			info[i][0] = p;
			info[i][1] = q;
		}
		Arrays.sort(info, (a, b)->Integer.compare(a[0], b[0]));
		PriorityQueue<int[]> wait = new PriorityQueue<int[]>((a, b)->Integer.compare(a[1], b[1]));
		PriorityQueue<Integer> available = new PriorityQueue<>();
		
		ArrayList<Integer> C = new ArrayList<>();
		C.add(1);
		wait.offer(new int[] {0, info[0][1]});
		for(int i = 1; i<N; i++) {
			int start = info[i][0];
			int end = info[i][1];
			
			while(!wait.isEmpty()) {
				int[] p = wait.peek();
				if(p[1] > start) break;
				p = wait.poll();
				available.offer(p[0]);
			}
			if(available.isEmpty()) {
				C.add(1);
				wait.offer(new int[] {C.size()-1, end});
			}else {
				int idx = available.poll();
				C.set(idx, C.get(idx)+1);
				wait.offer(new int[] {idx, end});
			}
		}
		
		System.out.println(C.size());
		StringBuilder answer = new StringBuilder();
		for(int n : C) answer.append(n).append(' ');
		System.out.println(answer);
 	}
}
