package P1931회의실;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		int N = Integer.parseInt(br.readLine());
		int[][] conf= new int[N][2];
		
		for(int i = 0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			conf[i][0] = Integer.parseInt(stk.nextToken());
			conf[i][1] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(conf, new Comparator<int[]>(){
			public int compare(int a[], int b[]) {
				if(a[1] < b[1]) {
					return -1;
				}
				else if(a[1] > b[1]) {
					return 1;
				}else {
					if(a[0] < b[0]) return -1;
					else if(a[0] > b[0]) return 1;
					else return 0;
				}				
			}
		});
		int answer = 0;
		int time = 0;
		for(int i = 0; i< N; i++) {
			if(time <= conf[i][0]) {
				answer++;
				time = conf[i][1];
			}
		}
		System.out.println(answer);
	}
}
