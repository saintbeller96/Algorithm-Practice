package P8980택배;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static class Box{
		int source, dest, quantity;
		public Box(int s, int d, int q) {
			source = s;
			dest = d;
			quantity = q;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int C = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(br.readLine());
		
		PriorityQueue<Box> truck = new PriorityQueue<Box>(new Comparator<Box>() {
			@Override
			public int compare(Box b1, Box b2) {
				if(b1.dest < b2.dest) {
					return -1;
				}else if(b1.dest > b2.dest) {
					return 1;
				}else {
					return Integer.compare(b1.source, b2.source);
				}
			}
		});

		int[] capacityArr = new int[2001];
		for(int i = 1; i<=2000; i++) {
			capacityArr[i] = 0;
		}
		
		for(int i = 0; i< M; i++) {
			stk = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int d = Integer.parseInt(stk.nextToken());
			int q = Integer.parseInt(stk.nextToken());
			truck.offer(new Box(s, d, q));
		}
		int answer = 0;
		while(!truck.isEmpty()) {
			Box box = truck.poll();
			
			int curCap = capacityArr[box.source];
			if(curCap >= C) continue;
			int load = canLoad(capacityArr, box.source, box.dest, C, box.quantity);
			if(load > 0) {
				for(int i = box.source; i < box.dest; i++) {
					capacityArr[i] += load;
				}
				answer += load;
			}
		}
		System.out.println(answer);
	}
	private static int canLoad(int[] capacityArr, int s, int d, int C, int q) {
		int ret = q;
		for(int i = s; i< d; i++) {
			int iCap = capacityArr[i];
			if(iCap >= C) return 0;
			else if(iCap < C && iCap + q > C) {
				ret = Math.min(ret, C - iCap);
			}
		}
		return ret;
	}
}
