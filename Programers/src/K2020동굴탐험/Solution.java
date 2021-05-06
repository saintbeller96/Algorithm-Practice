package K2020동굴탐험;

import java.util.*;
public class Solution {
	List<Integer>[] biAdjList;
	List<Integer>[] adjList;
	int[] indegree;
	public boolean solution(int n, int[][] path, int[][] order) {
        biAdjList = new List[n];
        adjList = new List[n];
        indegree = new int[n];
        for(int i = 0; i<n; i++) {
        	biAdjList[i] = new ArrayList<>();
        	adjList[i] = new ArrayList<>();
        }
        for(int[] p : path) {
        	int u = p[0];
        	int v = p[1];
        	biAdjList[u].add(v);
        	biAdjList[v].add(u);
        }
        
        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        que.offer(0);
        while(!que.isEmpty()) {
        	int u = que.poll();
        	for(int v : biAdjList[u]) {
        		if(!visited[v]) {
        			visited[v] = true;
        			que.offer(v);
        			adjList[u].add(v);
        			indegree[v]++;
        		}	
        	}
        }
        
        for(int[] o : order) {
        	int f = o[0];
        	int t = o[1];
        	adjList[f].add(t);
        	indegree[t]++;
        }

        return !hasCycle(n);
    }
	
	private boolean hasCycle(int n) {
		Queue<Integer> que = new ArrayDeque<>();
		for(int i = 0; i<n; i++) {
			if(indegree[i] == 0) que.offer(i);
		}
		int cnt = 0;
		while(!que.isEmpty()) {
			int u = que.poll();
			cnt++;
			for(int v : adjList[u]) {
				indegree[v]--;
				if(indegree[v] == 0) {
					que.offer(v);
				}
			}
		}
		
		return cnt != n;
	}

	public static void main(String[] args) {
		int n = 9;
		int[][] path = {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
		int[][] order = {{8,5},{6,7},{4,1}};
		Solution s = new Solution();
		System.out.println(s.solution(n, path, order));
	}
}
