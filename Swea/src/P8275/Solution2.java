package P8275;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {
	private static int T, N,M,X, max;
	private static int[][] info;
	private static int[] cases;
	private static int[] answer;
	public static void main(String[] args)throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer stk = null;
	    T = Integer.parseInt(br.readLine().trim());
	    for (int t = 1; t <=T; t++) {
	        stk = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(stk.nextToken());    //�츮 �� 1~N
	        X = Integer.parseInt(stk.nextToken());;    //�� �츮���� 0~X���� ���� �ܽ��� ����
	        M = Integer.parseInt(stk.nextToken());;    //������� ���
	        info = new int[M][3];
	        //������� ��� ��ŭ ������ �ܽ��Ͱ� ���� ���� ��츦 ���Ѵ�.
	        for (int i = 0; i < M; i++) {
	            stk = new StringTokenizer(br.readLine());
	            info[i][0] = Integer.parseInt(stk.nextToken());    //l��° �츮����
	            info[i][1] = Integer.parseInt(stk.nextToken());    //r��° �츮����
	            info[i][2] = Integer.parseInt(stk.nextToken());    //s������ �ܽ��Ͱ� �ִ�.  
	        }
	        max = -1;
	        cases = new int[N+1];
	        answer = new int[N+1];
	        BF(1, 0);
	        System.out.print("#" + t);
	        if(max == -1) {
	        	System.out.print(" " + "-1");
	        }else {
	        	for(int i = 1; i<=N; i++) {
	        		System.out.print(" " + answer[i]);
	        	}
	        }
	        System.out.println();
	    }
	}
	
	private static void BF(int idx, int sum) {
		if(idx == N+1) {
			if(sum > max) {//���� �ִ� ���� ū ���
				for(int i = 0; i<M; i++) {
					int l = info[i][0];
					int r = info[i][1];
					int s = info[i][2];				
					int temp = 0;//���� ���̽� �� �ܽ��� ��
					for(int j = l; j <= r; j++) {
						temp+=cases[j];
					}
					if(temp != s) {
						return;
					}//��ġ ���� ������ ����
				}
				System.arraycopy(cases, 1, answer, 1, N);//�� ����
				max = sum;				
			}		
		}else {
			for(int i = 0; i <= X; i++) {
				cases[idx] = i;
				BF(idx+1, sum + i);
			}	
		}			
	}
}
