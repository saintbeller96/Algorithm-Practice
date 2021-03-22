package P2239스도쿠;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int[][] map;
	static int[][] location;
	static boolean flag;
	static int zero = 0;
	static int[][] ranges = {{0,0,2,2},{0,3,2,5},{0,6,2,8},
							 {3,0,5,2},{3,3,5,5},{3,6,5,8},
							 {6,0,8,2},{6,3,8,5},{6,6,8,8}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		location = new int[9][9];
		for(int i = 0; i<9; i++) {
			String str = br.readLine();
			for(int j = 0; j<9; j++) {
				map[i][j] = str.charAt(j) - '0';
				if(map[i][j] == 0) zero++;
			}
		}
		int[] temp = {0, 3, 6};
		int cnt = 0;
		for(int i : temp) {
			for(int j : temp) {
				for(int r = 0; r<3; r++) {
					for(int c = 0; c<3; c++) {
						location[i+r][j+c] = cnt;
					}
				}
				cnt++;
			}
		}
		flag = false;
		dfs(0);
	}
	static void dfs(int cnt) {
		if(flag) return;
		//놓지 않은 곳이 있는지 확인
		if(cnt == zero) {
			flag = true;
			print();
			return;
		}
		
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				if(map[i][j] == 0) {
					ArrayList<Integer> candidates = findCandidates(i, j);
					for(int c : candidates) {
						map[i][j] = c;
						dfs(cnt+1);
						map[i][j] = 0;
					}
					return;
				}
			}
		}
	}
	static ArrayList<Integer> findCandidates(int r, int c){
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		
		int[] nums = new int[10];
		//가로 세로
		for(int i = 0; i<9; i++) {
			if(map[i][c] != 0) nums[map[i][c]]++;
			if(map[r][i] != 0) nums[map[r][i]]++;
		}
		
		int[] arr = ranges[location[r][c]];
		for(int i = arr[0]; i<=arr[2];i++) {
			for(int j = arr[1]; j<=arr[3]; j++) {
				if(map[i][j] != 0) nums[map[i][j]]++;
			}
		}
		
		for(int i = 1; i<=9; i++) {
			if(nums[i] == 0) candidates.add(i); 
		}
		
		return candidates;
	}
	static void print() {
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
