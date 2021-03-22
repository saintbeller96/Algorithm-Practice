package P1238Contact;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SolutionDFS {
	private static int T, N, S;
	private static int answer, last;
	private static HashSet<Integer> visited;
	private static HashMap<Integer, ArrayList<Integer>> adjMap;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		for(int t = 1; t<=10; t++) {
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			S = Integer.parseInt(stk.nextToken());		
			visited = new HashSet<Integer>();
			adjMap = new HashMap<Integer, ArrayList<Integer>>();
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i= i+2) {
				int s = Integer.parseInt(stk.nextToken());
				int e = Integer.parseInt(stk.nextToken());
				if(adjMap.containsKey(s)) {
					adjMap.get(s).add(e);
				}else {
					ArrayList<Integer> alist = new ArrayList<Integer>();
					alist.add(e);
					adjMap.put(s, alist);
				}				
			}
			last = 1;
			answer = 0;
			DFS(S, 1);
			System.out.println("#" + t + " " + answer);
		}
	}
	private static void DFS(int node, int depth) {		
		if(!adjMap.containsKey(node)) {
			return;
		}
		for(int e : adjMap.get(node)) {
			if(!visited.contains(e)) {					
				visited.add(e);
				if(depth >= last) {
					if(depth > last) answer = e;
					else answer = answer>e?answer:e;
					last = depth;
				}
				DFS(e, depth+1);
			}
		}		
	}	
}

