package DevMatching행렬테두리;

import java.util.*;

public class Solution {
	int[][] map;
	int R, C;
	public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        R = rows;
        C = columns;
        int cnt = 1;
        map = new int[R+1][C+1];
        for(int i = 1; i<=R; i++) {
        	for(int j = 1; j<=C; j++) {
        		map[i][j] = cnt++;
        	}
        }
        
        int idx = 0;
        for(int[] query: queries) {
        	int ret = rotation(query[0], query[1], query[2], query[3]);
        	answer[idx++] = ret;        	
        }
        
        return answer;
    }
	
	private int rotation(int r1, int c1, int r2, int c2) {
		int[][] cmap = new int[R+1][C+1];
		for(int i = 1; i<=R; i++) {
        	for(int j = 1; j<=C; j++) {
        		cmap[i][j] = map[i][j];
        	}
        }
		int ret = Integer.MAX_VALUE;
		for(int i = r1; i<=r2; i++) {
        	for(int j = c1; j<=c2; j++) {
        		if(i == r1 && j>c1) {
        			map[i][j] = cmap[i][j-1];
        			ret = Math.min(ret, map[i][j]);
        		}else if(j == c2 && i > r1) {
        			map[i][j] = cmap[i-1][j];
        			ret = Math.min(ret, map[i][j]);
        		}else if(i == r2 && j<c2) {
        			map[i][j] = cmap[i][j+1];
        			ret = Math.min(ret, map[i][j]);
        		}else if(j == c1 && i<r2) {
        			map[i][j] = cmap[i+1][j];
        			ret = Math.min(ret, map[i][j]);
        		}
        	}
        }
		return ret;
	}
	
	public static void main(String[] args){
		Solution sol = new Solution();
		int rows = 6;
		int columns = 6;
		int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
		System.out.println(Arrays.toString(sol.solution(rows, columns, queries)));
	}

}
