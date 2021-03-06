package P10875뱀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	//0 �� 1�� 2�� 3��   0�� 1��
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc= {0, 0, -1, 1};
	static int[][] dd = {{3, 2}, {2, 3}, {0, 1}, {1, 0}};//�ٲ� ����
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		map = new int[1 + 2*L][1 + 2*L];
		map[L][L] = 1;
		
		int[][] order = new int[N][2];
		for(int n = 0; n<N; n++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(stk.nextToken());
			char d = stk.nextToken().charAt(0);
			order[n][0] = t;
			order[n][1] = (d == 'R')?0:1;
		}
		
		int hr = L;
		int hc = L;
		
		int time = 0;
		int d = 3;
		for(int n = 0; n<N; n++) {	
			int t = order[n][0];
			for(int i = 0; i<t; i++) {
				time++;
				hr += dr[d];
				hc += dc[d];
				if(hr < 0 || hr >= 2*L+1 || hc < 0 || hc > 2*L+1 || map[hr][hc] != 0) {
					System.out.println(time);
					return;
				}
				map[hr][hc] = 1;
			}
			d = dd[d][order[n][1]];
		}
		
		System.out.println(time);
		
	}
}
