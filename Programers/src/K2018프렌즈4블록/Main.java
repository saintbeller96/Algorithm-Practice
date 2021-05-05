package K2018프렌즈4블록;

class Solution {
	private int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	private int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	private int R, C;
	private char[][] map;
	public int solution(int m, int n, String[] board) {
        int answer = 0;
        R = m; C = n;
        map = new char[R][C];
        for(int i = 0; i<board.length; i++) {
        	map[i] = board[i].toCharArray();
        }
        
        while(true) {
        	int[][] check = new int[R][C];
        	for(int i = 0; i<R-1; i++) {
            	for(int j = 0; j<C-1; j++) {
            		if(canErase(i, j)) {
            			erase(i, j, check);
            		}
            	}
            }
        	int cnt = 0;
        	for(int i = 0; i<R; i++) {
            	for(int j = 0; j<C; j++) {
            		if(check[i][j] == -1) {
            			cnt++;
            			map[i][j] = '\0';
            		}
            	}
            }
        	answer+=cnt;
        	if(cnt == 0) break;
        	fall();
        	
        }
        
        return answer;
    }
	
	private boolean canErase(int r, int c) {
		if(map[r][c] == '\0') return false;
		else if(map[r][c] != map[r+1][c]) return false;
		else if(map[r][c] != map[r+1][c+1]) return false;
		else if(map[r][c] != map[r][c+1]) return false;
		return true;
	}
	private void erase(int r, int c, int[][] check) {
		check[r][c] = check[r+1][c] = check[r+1][c+1] = check[r][c+1] = -1;
	}
    
    private void fall() {
    	for(int i = R-1; i>=0; i--) {
    		for(int j = 0; j<C; j++) {
    			if(map[i][j] == '\0') {
    				for(int r = i; r>=0; r--) {
    					if(map[r][j] != '\0') {
    						map[i][j] = map[r][j];
    						map[r][j] = '\0';
    						break;
    					}
    				}
    			}
    		}
    	}
    }
}

public class Main {

	public static void main(String[] args) {
		int m = 6;
		int n = 6;
		String board[] = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		String board2[] = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		String board3[] = { "BABA", 
							"AABB", 
							"AAAA", 
							"AAAA", 
							"AAAB"};
		Solution s = new Solution();
		int a = s.solution(m, n, board2);
		System.out.println(a);

	}

}
