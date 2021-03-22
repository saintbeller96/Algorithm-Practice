package P15999뒤집기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] board;
	static int[] dr = {-1, 1, 0 ,0};
	static int[] dc = { 0, 0,-1, 1};
 	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		board = new char[N][];
		for(int i = 0 ; i<N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		long cnt = 0;
		for(int i = 0; i<N; i++) {
			for(int j  =0; j<M; j++) {
				if(check(i, j)) {
					cnt++;
				}
			}
		}
		long l = 1;
		for(int i = 0; i<cnt; i++) {
			l = (l*2L) % 1000000007L;
		}
		
		System.out.println(cnt);

	}
 	//�����¿� ��� ���� ���� ��ϰ� ���� ���̸� true ��ȯ
 	static boolean check(int i, int j) {
 		int cnt = 0;
 		for(int d = 0; d<4; d++) {
 			int r = i + dr[d];
 			int c = j + dc[d];
 			if(r >=0 && r<N && c>=0 && c<M) {
 				if(board[i][j] == board[r][c]) {
 					cnt++;
 				}else {
 					return false;
 				}
 			}
 			else {
 				cnt++;
 			}
 		}
 		if(cnt == 4) return true;
 		return false;
 	}

}
