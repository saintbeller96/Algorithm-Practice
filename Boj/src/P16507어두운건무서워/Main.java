package P16507어두운건무서워;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, Q;
	static int[][] image;
	static int[][] prifixSum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		Q = Integer.parseInt(stk.nextToken());
		image = new int[R+1][C+1];
		prifixSum = new int[R+1][C+1];
		for(int i = 1; i<=R; i++) {
			stk = new StringTokenizer(br.readLine());
			int sum = 0;
			for(int j = 1; j<=C; j++) {
				image[i][j] = Integer.parseInt(stk.nextToken());
				sum+=image[i][j];
				prifixSum[i][j] += sum;
			}
		}
		
		//������ ���
		for(int c = 1; c<=C; c++) {
			int sum = prifixSum[1][c];
			for(int r = 2; r<=R; r++) {
				prifixSum[r][c] += sum;
				sum = prifixSum[r][c];
			}
		}
		
		for(int i = 0; i<Q; i++) {
			stk = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(stk.nextToken());
			int c1 = Integer.parseInt(stk.nextToken());
			int r2 = Integer.parseInt(stk.nextToken());
			int c2 = Integer.parseInt(stk.nextToken());
			
			long sum = prifixSum[r2][c2] - prifixSum[r2][c1-1] - prifixSum[r1-1][c2] + prifixSum[r1-1][c1-1];
			System.out.println(sum/((r2-r1+1)*(c2-c1+1)));
		}
	}
}
