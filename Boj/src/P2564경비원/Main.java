package P2564경비원;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int H, W, S;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());
		
		S = Integer.parseInt(br.readLine());
		ArrayList<Integer> line = new ArrayList<>();
		
		int base = 0;
		for(int i = 0; i<=S; i++) {
			stk = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(stk.nextToken());
			int value = Integer.parseInt(stk.nextToken());
			
			int point = 0;
			switch(d) {
			case 1:
				point = (-H - value);
				break;
			case 2:
				point = (value);
				break;
			case 3:
				point = (-H + value);
				break;
			case 4:
				point = (W + H - value);
				break;
			}
			line.add(point);
			if(i == S) base = point;
		}
		
		int answer = 0;
		for(int l : line) {
			if(l == base) continue;
			answer += Math.min(Math.abs(base - l), Math.abs(2*H+2*W - Math.abs(base-l)));
		}
		System.out.println(answer);
	}
}
