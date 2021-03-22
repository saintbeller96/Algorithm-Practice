package P11559뿌요뿌요;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] board = new char[12][6];
		
		for(int i = 0; i<12; i++) {
			board[i] = br.readLine().toCharArray();
		}
		int answer = 0;
		while(true) {
			int result = 0;
			for(int i = 0; i<12; i++) {
				for(int j = 0; j < 6; j++) {
					if(board[i][j] != '.') {
						result += bfs(board, i, j);
					}
				}
			}
			if(result > 0) {
				answer++;
				remove(board);
			}else {
				break;
			}
			print(board);
		}
		System.out.println(answer);
		
		
	}
	
	static int bfs(char[][] board, int si, int sj) {
		boolean[][] visited = new boolean[12][6];
		Queue<int[]> que = new LinkedList<>();
		Queue<int[]> puyos = new LinkedList<>();
		
		char curPuyo = board[si][sj];
		
		que.offer(new int[] {si, sj});
		puyos.offer(new int[] {si, sj});
		visited[si][sj] = true;
		
		while(!que.isEmpty()) {
			int[] p = que.poll();
			
			for(int d = 0; d<4; d++) {
				int r = p[0] + dr[d];
				int c = p[1] + dc[d];
				if(r >= 0 && r<12 && c >=0 && c<6 && board[r][c] == curPuyo) {
					if(!visited[r][c]) {
						que.offer(new int[] {r, c});
						puyos.offer(new int[] {r, c});
						visited[r][c] = true;
					}
				}
			}
			
		}
		//���� ����
		if(puyos.size() >= 4) {
			while(!puyos.isEmpty()) {
				int[] p = puyos.poll();
				board[p[0]][p[1]] = '.';
			}
			return 1;
		}			
		return 0;
	}
	
	static void remove(char[][] board) {
		for(int j = 0; j<6; j++) {
			for(int i = 11; i>=0; i--) {
				if(board[i][j] != '.') {
					for(int k = 11; k>i; k--) {
						if(board[k][j] == '.') {
							char temp = board[i][j];
							board[i][j] = board[k][j];
							board[k][j] = temp;
							break;
						}
					}
				}
			}
		}
	}
	static void print(char[][] board) {
		for(int i = 0; i<12; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
