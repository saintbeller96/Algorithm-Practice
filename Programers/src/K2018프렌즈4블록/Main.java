package K2018프렌즈4블록;

class Solution {
	private int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	private int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	private int M, N;
	private boolean flag;
    public int solution(int m, int n, String[] board) {
        M = m;
        N = n;
        char[][] map = new char[m][n];
        
        for(int i =0; i<m; i++) {
        	for(int j = 0 ; j<n; j++) {
        		map[i][j] = board[i].charAt(j);
        	}
        }
        flag = true;
        while(flag) {
        	flag = false;
        	for(int i = 0; i<m; i++) {
             	for(int j = 0; j<n; j++) {
             		boolean[][] b = new boolean[M][N];
             		if(map[i][j] != '0') {
             			check(i, j, map, b);
             		}
             	}
            }
        	falling(map);
        }
        int cnt = 0;
        for(int i = 0; i<m; i++) {
        	for(int j = 0; j<n; j++) {
        		if(map[i][j] == '0') cnt++;
        	}
        }
        
        return cnt;
    }
    private void check(int r, int c, char[][] board, boolean[][] b) {
    	if(r >=0 && r+1 < M && c >= 0 && c+1 < N) {
    		if(board[r][c] != '0' && board[r][c] == board[r+1][c] &&  board[r][c] == board[r][c+1] && board[r][c] == board[r+1][c+1]) {    			
    			b[r][c] = b[r+1][c] = b[r][c+1] = b[r+1][c+1] = true;
				for (int d = 0; d < 8; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr >=0 && nr+1<M && nc >= 0 && nc+1 < N) {
						if(!b[nr][nc] || !b[nr][nc+1] ||!b[nr+1][nc] || !b[nr+1][nc+1]) {
							check(nr, nc, board, b);
						}
					}
				}
    			board[r+1][c] = board[r][c] = board[r][c+1] = board[r+1][c+1] = '0';
    			flag = true;
    		}
    	}
    }
    
    private void falling(char[][] board) {
    	for(int j = 0; j < N; j++) {
    		for(int i = M-2; i>=0; i--) {
    			char block = board[i][j];
    			if(block != '0') {
    				int r = i+1;      			
        			while(r <= M-1 ) {
        				if(board[r][j] != '0') break;
        				board[r-1][j] = '0';
        				r++;
        			}
        			board[r-1][j] = block;
    			}
    		}
    	}
    }
    
    private void print(char[][] board) {
    	for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
    	System.out.println();
    }
}

public class Main {

	public static void main(String[] args) {
		int m = 5;
		int n = 4;
		String board[] = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		String board2[] = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		String board3[] = { "BABA", 
							"AABB", 
							"AAAA", 
							"AAAA", 
							"AAAB"};
		Solution s = new Solution();
		int a = s.solution(m, n, board3);
		System.out.println(a);

	}

}
