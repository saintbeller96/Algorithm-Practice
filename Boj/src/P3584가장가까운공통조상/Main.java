package P3584가장가까운공통조상;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int T, N, A, B;
	static int[] nodes;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			nodes = new int[N+1];
			for(int i = 0; i<N-1; i++) {
				stk = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(stk.nextToken());
				int b = Integer.parseInt(stk.nextToken());
				
				nodes[b] = a;
			}
			stk = new StringTokenizer(br.readLine());
			A = Integer.parseInt(stk.nextToken());
			B = Integer.parseInt(stk.nextToken());
			
			HashSet<Integer> elders = new HashSet<>();
			
			toRoot(elders, A);
			
			System.out.println(findCommon(elders, B));
		}
	}
	static void toRoot(HashSet<Integer> elders, int node) {
		if(node == 0) {
			return;
		}
		elders.add(node);
		toRoot(elders, nodes[node]);
	}
	
	static int findCommon(HashSet<Integer> elders, int node) {
		if(elders.contains(node)) {
			return node;
		}
		return findCommon(elders, nodes[node]);
	}
}
