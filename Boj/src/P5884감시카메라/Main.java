package P5884감시카메라;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int N;
 	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer stk = null;
		Map<Integer, Integer> xAxis = new HashMap<>();
		Map<Integer, Integer> yAxis = new HashMap<>();
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());		
			xAxis.put(x, xAxis.getOrDefault(x, 0)+1);
			yAxis.put(y, yAxis.getOrDefault(y, 0)+1);
		}
		
		System.out.println(mySolve(xAxis, yAxis));
		return;
	}
 	
 	public static int mySolve(Map<Integer, Integer> xAxis, Map<Integer, Integer> yAxis) {
 		if(xAxis.size() <= 3 || yAxis.size() <= 3) {
			return 1;
		}
 		// 3번째 경우
 		List<Integer> yList = new ArrayList<>();
		for(Entry<Integer, Integer> e : yAxis.entrySet()) {
			if(e.getValue() > 1) yList.add(e.getKey());
		}
		
		List<Integer> xList = new ArrayList<>();
		for(Entry<Integer, Integer> e : xAxis.entrySet()) {
			if(e.getValue() > 1) xList.add(e.getKey());
		}
		if(yList.size() < 3) {
			
		}else if(xList.size() < 3) {
			
		}
		
		return 0;
 	}
 	
}
