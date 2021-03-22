package P3190뱀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc= {0, 0, -1, 1};
	static int[][] dd = {{3, 2}, {2, 3}, {0, 1}, {1, 0}};//바뀐 방향 0R 1L
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;;

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		
		for(int k = 0; k<K; k++) {
			stk=new StringTokenizer(br.readLine());
			int r = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			map[r][c] = 2;
		}
		
		int L = Integer.parseInt(br.readLine());
		
		int[][] order = new int[L][2];
		for(int l = 0; l<L; l++) {
			stk = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(stk.nextToken());
			char d = stk.nextToken().charAt(0);
			order[l][0] = t;
			order[l][1] = (d == 'D')?0:1;
		}
		
		Deque<int[]> snake = new LinkedList<>();
		snake.offer(new int[] {1, 1});
		
		//0 1 2 3 상 하 좌 우
		int d = 3;//최초는 오른쪽
		int time = 0;
		for(int l = 0; l<L ; l++) {
			int time2change = order[l][0] - time;
			//시간동안 이동
			for(int t = 0; t< time2change; t++) {
				time++;
				int[] head = snake.getFirst();
				int hr = head[0] + dr[d];
				int hc = head[1] + dc[d];
				if(hr <= 0 || hr > N || hc <= 0 || hc > N || map[hr][hc] == 1) {
					System.out.println(time);
					return;
				}
				snake.offerFirst(new int[] {hr, hc});
				//이동 방향에 사과가 있으면 머리만 늘림
				if(map[hr][hc] != 2) {
					int[] tail = snake.pollLast();
					map[tail[0]][tail[1]] = 0;
				}
				map[hr][hc] = 1;
			}
			
			d = dd[d][order[l][1]];
		}
		
		//입력이 종료되면 종료될때 까지 이동
		for(int t = 0; t< 100; t++) {
			time++;
			int[] head = snake.getFirst();
			head[0] += dr[d];
			head[1] += dc[d];
			if(head[0] <= 0 || head[0] > N || head[1] <= 0 || head[1] > N || map[head[0]][head[1]] == 1) {
				System.out.println(time);
				return;
			}
		}
		
		
		System.out.println(time);
	}
}
