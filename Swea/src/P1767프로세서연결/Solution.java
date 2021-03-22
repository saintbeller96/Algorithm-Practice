package P1767프로세서연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	private static int[] dr = { -1, 1, 0, 0 }; // 위, 아래, 좌, 우
	private static int[] dc = { 0, 0, -1, 1 };
	private static int T, N, Answer, maxConn;
	private static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			ArrayList<int[]> points = new ArrayList<>();//코어의 위치를 저장하는 리스트
			maxConn = 0;//현재 연결한 코어의 개수
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					
					//가장자리에 있지 않은 코어의 좌표만만 저장함
					if (map[i][j] == 1 && i > 0  && i<N-1 && j>0 && j<N-1) {
						points.add(new int[] { i, j });
					}
				}
			}
			Answer = Integer.MAX_VALUE;
			
			dfs(points, 0, 0, 0);

			System.out.println("#" + t + " " + Answer);
		}
	}

	static void dfs(ArrayList<int[]> point, int length, int nConn, int depth) {
		//가지치기: 
		//현재 연결된 코어의 개수가 최대로 연결된 코어의 개수보다 작지만 놓은 전선의 길이가 더 길다면
		//이때는 최솟값이 될 수 없음
		if(nConn < maxConn && length > Answer) {
			return;
		}
		//기저 조건
		if (depth == point.size()) {
			//현재 연결한 코어의 개수가 지금까지 연결한 코어의 개수보다 크다면
			//최대 연결 코어 개수와 전선의 길이 갱신
			if (nConn > maxConn) {
				maxConn = nConn;
				Answer = length;
			}
			//현재 연결한 코어의 개수가 지금까지 연결한 코어의 개수와 같다면
			//더 작은 길이를 답으로 저장
			else if(nConn == maxConn) {
				Answer = Math.min(Answer, length);
			}
			return;
		}
		//현재 코어의 좌표
		int r = point.get(depth)[0];
		int c = point.get(depth)[1];

		//전선을 놓을 수 있는지 4방향으로 탐색
		for (int d = 0; d < 4; d++) {
			//만약 놓을 수 있다면
			if (check(r, c, d)) {
				//전선을 -1로 놓고 놓은 길이를 저장
				int cnt = setLine(r, c, d, -1);
				//다음 코어로 이동
				dfs(point, length + cnt, nConn+1, depth+1);
				//놓은 전선을 0으로 되돌림
				setLine(r, c, d, 0);
			}
		}
		//이번 코어를 선택하지 않고 넘어감
		dfs(point, length, nConn, depth + 1);
	}
	
	//전선을 지정된 방향(d)으로 놓을 수 있는지 확인
	static boolean check(int r, int c, int d) {
		int i = r, j = c;
		while(true) {
			i += dr[d];
			j += dc[d]; 
			//좌표를 벗어나면 종료
			if(i < 0 || i >=N || j < 0 || j >= N) {
				break;
			}
			//전선을 놓을 경로에 코어나 다른 전선이 있다면 false 반환
			if(map[i][j] != 0) {
				return false;
			}
		}
		//전선을 놓을 수 있기 때문에 true 반환
		return true;
	}
	//전선을 놓은 후 그 놓은 개수(전선의 길이)를 반환
	static int setLine(int r, int c, int d, int s) {
		int cnt = 0;
		int i = r, j = c;
		while(true) {
			i += dr[d];
			j += dc[d]; 
			if(i < 0 || i >=N || j < 0 || j >= N) {
				break;
			}
			//전선을 s로 표시
			map[i][j] = s;
			//놓은 전선의 개수 증가시킴
			cnt++;
		}
		return cnt;
	}
}
