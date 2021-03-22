package P2814최장경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, M;
	static HashMap<Integer, ArrayList<Integer>> adjList;
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			adjList = new HashMap<>();
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			
			for(int i = 0; i<M; i++) {
				stk = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(stk.nextToken());
				int y = Integer.parseInt(stk.nextToken());
				if(!adjList.containsKey(x)) {
					adjList.put(x, new ArrayList<>());
				}
				adjList.get(x).add(y);
				if(!adjList.containsKey(y)) {
					adjList.put(y, new ArrayList<>());
				}
				adjList.get(y).add(x);
			}
			
			int max =1;
			for(int v = 1; v<=N; v++) {
				max = Math.max(max, dfs(v, 0|(1<<v)));
			}
			
			System.out.println("#" + tc + " " + max);
		}
	}
	
	static int dfs(int v, int visited) {
		int ret = 1;
		if(!adjList.containsKey(v)) return ret;
		for(int a : adjList.get(v)) {
			if((visited & (1<<a)) != 0) continue;		
			ret = Math.max(ret, dfs(a, (visited|(1<<a)))+1);
		}
		return ret;
	}
}
