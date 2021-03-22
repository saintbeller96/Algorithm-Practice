package P4014활주로건설;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, X;
	static int[][] map, rmap;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			X = Integer.parseInt(stk.nextToken());
			
			map = new int[N][N];
			rmap = new int[N][N];
			for(int i = 0; i<N; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					rmap[j][i] = map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			System.out.println("#" + t +" " + process());
		}
	}
	
	static int process() {
		int count = 0;
		for(int i = 0; i<N; i++) {
			if(canMakeRoad(map[i])) count++; 
			if(canMakeRoad(rmap[i])) count++;
		}
		return count;
	}
	
	static boolean canMakeRoad(int[] road) {
		int prevHeight, size;
		prevHeight = road[0];
		size = 1;//평탄한 지형의 길이
		for(int i = 1; i<N; i++) {
			//이전칸과 높이가 같은지
			if(prevHeight == road[i]) {
				size++;
			}else if(prevHeight+1 == road[i]) {
				if(size < X) return false;
				prevHeight = road[i];
				size = 1;
			}else if(prevHeight-1 == road[i]) {
				int cnt = 0;
				for(int j = i; j<N; j++) {
					if(road[j] != prevHeight-1) break;
					cnt++;
				}
				if(cnt < X) return false;
				i += X-1;
				prevHeight = road[i];
				size = 0;
			}else {
				return false;
			}
		}
		return true;
	}
}
