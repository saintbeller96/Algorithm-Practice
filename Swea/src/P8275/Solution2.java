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
	        N = Integer.parseInt(stk.nextToken());    //우리 수 1~N
	        X = Integer.parseInt(stk.nextToken());;    //각 우리마다 0~X마리 이하 햄스터 존재
	        M = Integer.parseInt(stk.nextToken());;    //경근이의 기록
	        info = new int[M][3];
	        //경근이의 기록 만큼 돌려서 햄스터가 가장 많은 경우를 구한다.
	        for (int i = 0; i < M; i++) {
	            stk = new StringTokenizer(br.readLine());
	            info[i][0] = Integer.parseInt(stk.nextToken());    //l번째 우리에서
	            info[i][1] = Integer.parseInt(stk.nextToken());    //r번째 우리까지
	            info[i][2] = Integer.parseInt(stk.nextToken());    //s마리의 햄스터가 있다.  
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
			if(sum > max) {//만약 최댓값 보다 큰 경우
				for(int i = 0; i<M; i++) {
					int l = info[i][0];
					int r = info[i][1];
					int s = info[i][2];				
					int temp = 0;//현재 케이스 안 햄스터 수
					for(int j = l; j <= r; j++) {
						temp+=cases[j];
					}
					if(temp != s) {
						return;
					}//일치 하지 않으면 종료
				}
				System.arraycopy(cases, 1, answer, 1, N);//답 복사
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
