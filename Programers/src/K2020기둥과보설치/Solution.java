package K2020기둥과보설치;

import java.util.*;

class Solution {
	int n;
	int[][] mapCol;
	int[][] mapRow;
	public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        mapCol = new int[n+2][n+2];
        mapRow = new int[n+2][n+2];
        
        for(int i = 0; i<=n+1; i++) {
        	for(int j = 0; j<=n+1; j++) {
        		mapCol[i][j] = -1;
        		mapRow[i][j] = -1;
        	}
        }
        
        for(int[] frame : build_frame) {
        	int x = frame[0];
        	int y = frame[1];
        	int a = frame[2];
        	int b = frame[3];
        	
        	if(b == 1) {
        		if(a == 0) {
        			if(canBuildCol(x, y)) mapCol[x][y] = 0;
        		}else {
        			if(canBuildRow(x, y)) mapRow[x][y] = 1;
        		}
        	}else {
        		if(a == 0) {
        			mapCol[x][y] = -1;
        			if(!canRemoveCol(x, y)) mapCol[x][y] = 0;
        		}else {
        			mapRow[x][y] = -1;
        			if(!canRemoveRow(x, y)) mapRow[x][y] = 1;
        		}
        	}
        }
        List<int[]> result = new ArrayList<>();
        for(int i = 0; i<=n; i++) {
        	for(int j = 0; j<=n; j++) {
        		if(mapCol[i][j] == 0) {
        			result.add(new int[] {i, j, mapCol[i][j]});
        		}
        		if(mapRow[i][j] == 1) {
        			result.add(new int[] {i, j, mapRow[i][j]});
        		}
        	}
        }
        Collections.sort(result, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] < o2[0]) return -1;
				else if(o1[0] > o2[0]) return 1;
				else {
					if(o1[1] < o2[1]) return -1;
					else if(o1[1] > o2[1]) return 1;
					else {
						return Integer.compare(o1[2], o2[2]);
					}
				}
			}
		});
        
        return result.toArray(new int[0][0]);
    }
	private boolean canBuildCol(int x, int y) {
		if(y==0 || (x>0 && mapRow[x-1][y] == 1) || (mapRow[x][y] == 1) || (y>0 && mapCol[x][y-1] == 0)) return true;
		return false;
	}
	
	private boolean canBuildRow(int x, int y) {
		if(y>0 && (mapCol[x][y-1] == 0) || (mapCol[x+1][y-1] == 0) || (mapRow[x+1][y]==1 && x>0 && mapRow[x-1][y] == 1)) return true;
		return false;
	}
	
	private boolean canRemoveCol(int x, int y) {
		if(mapCol[x][y+1] == 0 && !canBuildCol(x, y+1)) {
			return false;
		}
		if(mapRow[x][y+1] == 1 && !canBuildRow(x, y+1)) {
			return false;
		}
		if(x>0 && mapRow[x-1][y+1] == 1 && !canBuildRow(x-1, y+1)) {
			return false;
		}
		if(mapRow[x][y]==1 && !canBuildRow(x, y)) {
			return false;
		}
		if(x>0 && mapRow[x-1][y] == 1 && !canBuildRow(x-1, y)) {
			return false;
		}
		return true;
	}
	
	private boolean canRemoveRow(int x, int y) {
		if(x>0&&mapRow[x-1][y] == 1 && !canBuildRow(x-1, y)) {
			return false;
		}
		if(mapRow[x+1][y] == 1 && !canBuildRow(x+1, y)) {
			return false;
		}
		if(mapCol[x+1][y] == 0 && !canBuildCol(x+1, y)) {
			return false;
		}
		if(mapCol[x][y] == 0 && !canBuildCol(x, y)) {
			return false;
		}
		
		return true;
	}

    
    public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		int n = 5;
		//int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
//		int n = 5;
		int[][] build_frame = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};		
		int[][] answer = s.solution(n, build_frame);
		for(int[] row : answer) {
			System.out.println(Arrays.toString(row));
		}
	}
}
