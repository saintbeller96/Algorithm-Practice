package P17073나무위의빗물;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, W;
	static int[] nodes;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		W = Integer.parseInt(stk.nextToken());
		nodes = new int[N+1];
		for(int i = 1; i<N; i++){
			stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			nodes[u]++;
			nodes[v]++;
		}
		int cnt = 0;
		for(int i = 2; i<=N; i++){
			//Leaf 노드인 경우
			if(nodes[i] == 1) {
				cnt++;
			}
		}
		System.out.println((double)W/(double)cnt);
	}
}
