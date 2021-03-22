package P7699수지의수지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T, R, C;
	private static int answer;
	private static int[] dr = {0, 0, 1, -1};
	private static int[] dc = {1, -1, 0, 0};
	private static char[][] map;
	private static boolean[] visited;
	private static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        T = Integer.parseInt(br.readLine().trim());
        for(int t =1; t<=T; t++) {
        	stk = new StringTokenizer(br.readLine().trim());
        	
        	R = Integer.parseInt(stk.nextToken());
        	C = Integer.parseInt(stk.nextToken());
        	
        	answer = 1;
        	visited = new boolean[26];
        	map = new char[R][C];
        	for(int i = 0; i<R; i++) {
        		String str = br.readLine();
        		for(int j = 0; j < C; j++) {
        			map[i][j] = str.charAt(j);
        		}
        	}
        	flag = false;
        	visited[map[0][0] - 'A'] = true;
        	DFS(0,0,1);
        	
        	System.out.println("#" + t + " " + answer);
        }
	}
	private static void DFS(int r, int c, int depth) {
		answer = Math.max(answer, depth);
		if(flag) {
			return;
		}
		if(depth == 26) {
			flag = true;
			return;
		}
    	for(int d = 0; d < 4; d++) {
    		int i = r + dr[d];
    		int j = c + dc[d];
    		if(i >= 0 && i<R && j >=0 && j<C) {
    			if(!visited[map[i][j] - 'A'] && depth < 26) {
        			visited[map[i][j] - 'A'] = true;
        			DFS(i, j, depth+1);
        			visited[map[i][j] - 'A'] = false;
        		}
    		}
    	}
    }
}
