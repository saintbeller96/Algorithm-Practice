package P10800컬러볼;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	private static class Ball{
		int idx, color;
		public Ball(int i, int c) {
			idx = i;
			color = c;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeMap<Integer, ArrayList<Ball>> tMap = new TreeMap<Integer, ArrayList<Ball>>();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i<N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(stk.nextToken());
			int size = Integer.parseInt(stk.nextToken());
			if(!tMap.containsKey(size)) {
				tMap.put(size, new ArrayList<Ball>());
			}
			tMap.get(size).add(new Ball(i, color));
		}
		
		int sum = 0;
		int[] answer = new int[N];
		HashMap<Integer, Integer> csMap = new HashMap<Integer, Integer>();
		for(Entry<Integer, ArrayList<Ball>> e : tMap.entrySet()) {
			int curSize = e.getKey();
			int temp = 0;
			for(Ball ball : e.getValue()) {
				if(!csMap.containsKey(ball.color)) {
					csMap.put(ball.color, 0);
				}
				answer[ball.idx] = sum - csMap.get(ball.color);
				temp += curSize;
			}
			for(Ball ball : e.getValue()) {		
				csMap.put(ball.color, csMap.get(ball.color) + curSize);
			}
			sum += temp;		
		}
		for(int a : answer) {
			System.out.println(a);
		} 
	}
}
