package P1715카드정렬하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Long> pque = new PriorityQueue<Long>();
		
		for(int i = 0; i<N; i++) {
			pque.offer(Long.parseLong(br.readLine()));
		}
		long answer = 0;
		while(!pque.isEmpty()) {
			long card1 = pque.poll();
			if(pque.isEmpty()) {
				break;
			}
			long card2 = pque.poll();
			long sum = card1 + card2;
			answer += sum;
			pque.offer(sum);
		}
		System.out.println(answer);

	}

}
