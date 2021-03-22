package P9280;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int T, Answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			stk = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stk.nextToken());
			int m = Integer.parseInt(stk.nextToken());
			Answer = 0;
			int[] R = new int[n];
			int[] parkingArea = new int[n];
			for (int i = 0; i < n; i++) {
				R[i] = Integer.parseInt(br.readLine());
			}

			int[] W = new int[m+1];
			for (int i = 1; i <= m; i++) {
				W[i] = Integer.parseInt(br.readLine());
			}
			
			int[] order_park = new int[2*m];
			for (int i = 0; i < 2*m; i++) {
				order_park[i] = Integer.parseInt(br.readLine());
			}

			Queue<Integer> waitQue = new LinkedList<>();
			
			for (int i = 0; i < 2*m; i++) {
				if(order_park[i] > 0) {
					int k = check_parking(parkingArea);
					if(k != -1) {
						parkingArea[k] = order_park[i];
					}
					else {
						waitQue.offer(order_park[i]);
					}
				}else {
					for (int j = 0; j < parkingArea.length; j++) {
						if(parkingArea[j] == -order_park[i]) {
							Answer += W[-order_park[i]]*R[j];
							if(!waitQue.isEmpty()) {
								parkingArea[j] = waitQue.poll();
							}else {
								parkingArea[j] = 0;
							}
						}
					}
				}
			}	
            System.out.println("#" + t + " " + Answer);
		}			
	}
	
	private static int check_parking(int[] parkingArea) {
		for (int j = 0; j < parkingArea.length; j++) {
			if(parkingArea[j] == 0) {
				return j;
			}
		}
		return -1;
	}
}
