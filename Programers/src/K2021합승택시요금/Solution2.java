package K2021합승택시요금;

import java.util.*;

public class Solution2 {
	
	public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int[][] dist = new int[n+1][n+1];
        
        for(int[] fare : fares) {
        	int from = fare[0];
        	int to = fare[1];
        	int cost = fare[2];
        	dist[from][to] = cost;
        	dist[to][from] = cost;
        }
        
        for(int i = 1; i<=n; i++) {
        	for(int j = 1; j<=n; j++) {
        		dist[i][j] = Integer.MAX_VALUE/4;
        	}
        	dist[i][i] = 0;
        }
        
        for(int k = 1; k<=n; k++) {
        	for(int i = 1; i<=n; i++) {
        		for(int j = 1; j<=n; j++) {
        			dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        		}
        	}
        }
        for(int i = 1; i<=n; i++) {
        	answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        return answer;
    }
	
	
	public static void main(String[] args) throws Exception {
		Solution2 sol = new Solution2();
		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		System.out.println(sol.solution(n, s, a, b, fares));
	}
}
