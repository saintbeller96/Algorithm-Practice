package P2560짚신벌레;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(stk.nextToken());
		int b = Integer.parseInt(stk.nextToken());
		int d = Integer.parseInt(stk.nextToken());
		int N = Integer.parseInt(stk.nextToken());
		
		Queue<Integer> baby = new LinkedList<>();
		Queue<Integer> adult = new LinkedList<>();
		Queue<Integer> elder = new LinkedList<>();
		
		int totalAdult = 0;
		int nAlive = 1; 
		
		baby.offer(1);
		
		for(int i = 1; i<=N; i++) {
			if(baby.size() >= a) {
				int x = baby.poll();
				totalAdult += x;
				adult.offer(x);
			}
			
			if(adult.size() > b - a) {
				int x = adult.poll();
				totalAdult -= x;
				elder.offer(x);
			}
			
			if(elder.size() > d- b) {
				int x = elder.poll();
				nAlive -= x;
			}
			baby.offer(totalAdult%1000);
			nAlive += totalAdult%1000;
		}
		
		System.out.println(nAlive%1000);		
	}

}
