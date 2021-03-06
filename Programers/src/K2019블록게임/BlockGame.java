package K2019블록게임;

class Solution {
	static int N = 0;

	public int solution(int[][] board) {
		int answer = 0;
		N = board.length;

		for (int n = 0; n < N; n++) {
			for (int c = 0; c < N; c++) {
				int r = 0;
				for (r = 0; r < N; r++) {
					if (r + 2 < N && c + 1 < N && (board[r + 2][c] != 0 && board[r + 2][c + 1] != 0
						&& board[r + 2][c] == board[r + 2][c + 1])) {
						break;
					}
				}
				if (r + 2 < N && c + 1 < N) {
					answer += check1(board, r, c);
				}

				for (r = 0; r < N; r++) {
					if (r + 1 < N && c + 2 < N
						&& (board[r + 1][c] != 0 && board[r + 1][c + 1] != 0 && board[r + 1][c + 2] != 0)
						&& (board[r + 1][c] == board[r + 1][c + 1] && board[r + 1][c + 2] == board[r + 1][c + 1])) {
						break;
					}
				}
				if (r + 1 < N && c + 2 < N) {
					answer += check2(board, r, c);
				}
			}
		}
		return answer;
	}

	// ����
	private int check1(int[][] board, int r, int c) {
		int block = board[r + 2][c];
		if (board[r][c] == 0 && board[r + 1][c] == 0 && board[r][c + 1] == block && board[r + 1][c + 1] == block) {
			if(!checkUp(board, r+1, c)) {
				return 0;
			}
			for (int i = r; i <= r + 2; i++) {
				for (int j = c; j <= c + 1; j++) {
					board[i][j] = 0;
				}
			}
			return 1;
		} else if (board[r][c] == block && board[r + 1][c] == block && board[r][c + 1] == 0
				&& board[r + 1][c + 1] == 0) {
			if(!checkUp(board, r+1, c+1)) {
				return 0;
			}
			for (int i = r; i <= r + 2; i++) {
				for (int j = c; j <= c + 1; j++) {
					board[i][j] = 0;
				}
			}
			return 1;
		}
		return 0;
	}

	// ����
	private int check2(int[][] board, int r, int c) {
		int block = board[r + 1][c];
		if (board[r][c] == block && board[r][c + 1] == 0 && board[r][c + 2] == 0) {
			if(!checkUp(board, r, c+1) || !checkUp(board, r, c+2)) {
				return 0;
			}
			for (int i = r; i <= r + 1; i++) {
				for (int j = c; j <= c + 2; j++) {
					board[i][j] = 0;
				}
			}
			return 1;
		} else if (board[r][c] == 0 && board[r][c + 1] == 0 && board[r][c + 2] == block) {
			if(!checkUp(board, r, c) || !checkUp(board, r, c+1)) {
				return 0;
			}
			for (int i = r; i <= r + 1; i++) {
				for (int j = c; j <= c + 2; j++) {
					board[i][j] = 0;
				}
			}
			return 1;
		} else if (board[r][c] == 0 && board[r][c + 1] == block && board[r][c + 2] == 0) {
			if(!checkUp(board, r, c) || !checkUp(board, r, c+2)) {
				return 0;
			}
			for (int i = r; i <= r + 1; i++) {
				for (int j = c; j <= c + 2; j++) {
					board[i][j] = 0;
				}
			}
			return 1;
		}
		return 0;
	}
	
	private boolean checkUp(int[][] board, int r, int c) {
		for(int i = r; i>=0 ; i--) {
			if(i>=0 && board[i][c] != 0) {
				return false;
			}
		}
		return true;
	}

	private static void print(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

public class BlockGame {
	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 4, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 4, 4, 0, 0, 0 }, { 0, 0, 0, 0, 3, 0, 4, 0, 0, 0 }, { 0, 0, 0, 2, 3, 0, 0, 0, 5, 5 },
				{ 1, 2, 2, 2, 3, 3, 0, 0, 0, 5 }, { 1, 1, 1, 0, 0, 0, 0, 0, 0, 5 } };
		int[][] board2 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 9, 0, 0, 0, 4, 0, 0, 0, 0 }, { 0, 7, 9, 0, 0, 0, 4, 0, 0, 0, 0 },
				{ 0, 7, 9, 9, 3, 4, 4, 0, 0, 0, 0 }, { 7, 7, 0, 2, 3, 0, 0, 0, 5, 5, 0 },
				{ 1, 2, 2, 2, 3, 3, 0, 0, 0, 5, 0 }, { 1, 1, 1, 6, 0, 0, 0, 0, 0, 5, 0 },
				{ 0, 0, 6, 6, 6, 0, 0, 0, 0, 0, 0 } };
		int[][] board3 = {{0,0,0,0,0,0,0,0,0,0}
		,{0,0,0,0,0,0,0,0,0,0}
		,{0,0,0,0,0,0,0,0,0,0}
		,{0,0,0,0,0,0,0,0,0,0}
		,{0,0,0,2,2,0,0,0,0,0}
		,{0,0,0,2,1,0,0,0,0,0}
		,{0,0,0,2,1,0,0,0,0,0}
		,{0,0,0,0,1,1,0,0,0,0}
		,{0,0,0,0,0,0,0,0,0,0}
		,{0,0,0,0,0,0,0,0,0,0}};
		Solution s = new Solution();
		System.out.println(s.solution(board3));
	}
}
