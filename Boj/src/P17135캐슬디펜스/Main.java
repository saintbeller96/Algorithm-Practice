package P17135캐슬디펜스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, D;
	private static int[][] map;
	private static ArrayList<int[]> archer;
	private static int dead;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		D = Integer.parseInt(stk.nextToken());
		archer = new ArrayList<int[]>();
		map = new int[N + 1][M];

		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		int[] comb = new int[3];
		setArcher(comb, 0, 0);//�ü����� ��ġ�� ����
		int max = 0;
		for (int[] arr : archer) {
			dead = 0;
			max = Math.max(max, simul(arr));
		}
		System.out.println(max);
	}

	private static void setArcher(int[] comb, int start, int curlen) {
		if (curlen == 3) {
			int[] arr = new int[3];
			System.arraycopy(comb, 0, arr, 0, 3);
			archer.add(arr);
			return;
		}
		for (int i = start; i < M; i++) {
			comb[curlen] = i;
			setArcher(comb, i + 1, curlen + 1);
		}
	}
	
	
	private static int simul(int[] archerPos) {
		int[][] cmap = new int[N + 1][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cmap[i][j] = map[i][j];
			}
		}//�� ����
		ArrayList<int[]> alist = new ArrayList<int[]>();//���� ��ǥ�� ������ ����Ʈ
		for (int m = 0; m < N; m++) {
			for(int j = 0 ; j<3; j++) {
				int[] p = shot(cmap, N, archerPos[j]);
				if(p != null) {
					alist.add(p);
				}
			}
			for(int[] p : alist) {
				if(cmap[p[0]][p[1]] != 0) {
					dead++;
					cmap[p[0]][p[1]] = 0;
				}			
			}
			alist.clear();
			move(cmap);//���� ���� ��ĭ�� �̵�
		}
		return dead;
	}

	private static int[] shot(int[][] map, int ai, int aj) {
		for (int h = 1; h <= D; h++) {
			int j = aj - h+1;
			for (int i = ai - 1; i >= ai - h; i--) {
				if(i>=0 && i<N && j >=0 && j<M) {
					if (map[i][j] == 1) {
						return new int[] {i, j};
					}
				}	
				j++;
			}
			for (int i = ai - h + 1; i < ai; i++) {	
				if(i>=0 && i<N && j >=0 && j<M) {
					if (map[i][j] == 1) {
						return new int[] {i, j};
					}
				}
				j++;
			}
		}
		return null;
	}

	private static void move(int[][] map) {
		for (int j = 0; j < M; j++) {
			for (int i = N-1; i > 0; i--) {
				map[i][j] = map[i-1][j];
			}
			map[0][j] = 0;
		}	
	}
}
