package P8275;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N,M,X, sum;
	private static int[] hamster;
	private static int[][] info;
	private static int[] temp;
	private static int[] answer;
	private static ArrayList<int[]>[] cases;
	public static void main(String[] args)throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer stk = null;
	    T = Integer.parseInt(br.readLine().trim());
	    for (int t = 1; t <=T; t++) {
	        stk = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(stk.nextToken());    //우리 수 1~N
	        X = Integer.parseInt(stk.nextToken());;    //각 우리마다 0~X마리 이하 햄스터 존재
	        M = Integer.parseInt(stk.nextToken());;    //경근이의 기록
	        sum = 0;
	        hamster = new int[X+1];
	        for(int i = 0; i<= X; i++) {
	        	hamster[i] = i;
	        }
	        cases = new ArrayList[M];
	        info = new int[M][3];
	        //경근이의 기록 만큼 돌려서 햄스터가 가장 많은 경우를 구한다.
	        for (int i = 0; i < M; i++) {
	            stk = new StringTokenizer(br.readLine());
	            info[i][0] = Integer.parseInt(stk.nextToken());    //l번째 우리에서
	            info[i][1] = Integer.parseInt(stk.nextToken());    //r번째 우리까지
	            info[i][2] = Integer.parseInt(stk.nextToken());    //s마리의 햄스터가 있다.  
	            ArrayList<int[]> alist = new ArrayList<>();
	            myPerm(alist, info[i][1] - info[i][0]+1, 0, 0, i);
	            cases[i] = alist;
	        }
	        answer = new int[N+1];
	        temp = new int[N+1];
	        Arrays.fill(temp, -1);

	        solve(0);
	        System.out.print("#" + t);
	        if(sum != 0) {
	        	for(int i = 1; i<=N; i++) {
		        	System.out.print(" " + answer[i]);
		        }
	        }else {
	        	System.out.print(" " + -1);
	        }        
	        System.out.println();
	    }
	    
	}
	private static void myPerm(ArrayList<int[]> alist, int len, int cnt, int sum, int n) {
		if(cnt == len) {
			if(sum == info[n][2]) {
				int[] cage = new int[N+1];
				Arrays.fill(cage, -1);
				int idx = info[n][0];
				for (int i = 0; i < cnt; i++) {
					cage[idx++] = hamster[i];
				}
				alist.add(cage);
			}		
			return;
		}
		for(int i = 0; i <= X; i++) {
			hamster[cnt]= i;
			myPerm(alist, len, cnt+1, sum + hamster[cnt], n);
		}
	}
	private static void solve(int idx) {
		if(idx == M) {
			int s = 0;
			for(int i = 1; i<=N; i++) {
				if(temp[i] == -1) {
					s+=X;
					temp[i] = 5;
				}else {
					s += temp[i];
				}				
			}
			if(sum < s) {
				System.arraycopy(temp, 0, answer, 0, N+1);
				sum = s;
			}
			
			return;
		}
		ArrayList<int[]> alist = cases[idx];
		
		int l = info[idx][0];
		int r = info[idx][1];
		boolean flag = false;
		
		for(int[] arr : alist) {
			for(int k = l; k <= r;k++) {
				if(arr[k] != -1) {
					if(temp[k] == -1) {
						temp[k] = arr[k];
					}else {
						if(temp[k] == arr[k]) {
							flag = true;
						}else {
							flag = false;
							break;
						}
					}
				}
			}
			if(idx == 0 || flag) {
				solve(idx+1);
				break;
			}		
		}
		Arrays.fill(temp, -1);
	}
}
