package P15954인형들;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] dolls;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		dolls = new int[N];
		
		stk = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<N; i++) {
			dolls[i] = Integer.parseInt(stk.nextToken());
		}
		double answer = Double.MAX_VALUE;
		for(int i = 0; i<= N-K; i++) {
			//k�� �̻���
			for(int j = i + K-1; j < N; j++) {
				int sum = 0;
				//i���� j���� ������ ���, �л� ����
				for(int k = i ; k <= j; k++) {
					sum += dolls[k];
				}
				double avg = sum/(double)(j-i+1);

				double dev = 0.0;
				for(int k = i ; k <= j; k++) {
					dev += Math.pow(dolls[k] - avg, 2);
				}
				double standard = dev/(double)(j-i+1);
				answer = answer > Math.sqrt(standard)?Math.sqrt(standard):answer;
			}
		}
		System.out.println(answer);	
	}

}
