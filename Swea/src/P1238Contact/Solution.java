package P1238Contact;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, S;
	private static HashSet<Integer> visited;//방문한 노드 번호 저장
	private static HashMap<Integer, ArrayList<Integer>> adjMap;//인접리스트
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
					ArrayList<Integer> alist = new ArrayList<>();
					alist.add(e);
					adjMap.put(s, alist);
				}		
			}
			int answer = BFS();
			System.out.println("#" + t + " " + answer);
		}
	}
	private static int BFS() {		
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {S, 1});
		visited.add(S);
		int last = S;
		int lastNum = 1;
		while(!que.isEmpty()) {
			int[] node = que.poll();
			if(!adjMap.containsKey(node[0])) {
				continue;
			}
			for(int e : adjMap.get(node[0])) {
				if(!visited.contains(e)) {			
					visited.add(e);
					que.offer(new int[] {e, node[1]+1});
					if(node[1]+1 >= lastNum) {
						if(node[1]+1 > lastNum) last = e;
						else last = last>e?last:e;
						lastNum = node[1] +1;
					}
				}
			}
		}
		return last;
	}	
}
