package P1074Z;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long answer, num;
	static long r, c;
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stk.nextToken());
		r = Long.parseLong(stk.nextToken());
		c = Long.parseLong(stk.nextToken());
		flag = false;
		DAC(0,0,(long)Math.pow(2, N));
		
		System.out.println(answer);
	}
	
	static void DAC(long h, long w, long n) {
		if(flag) {
			return;
		}
		if(n == 2) {
			for(long i = h; i<h+n; i++) {
				for(long j = w; j < w+n; j++) {
					if(i == r && j == c) {
						answer = num;
						flag = true;
						return;
					}
					num++;
				}
			}
		}else {
			if(r < h+n/2 && c < w+n/2) {
				DAC(h, w, n/2);
			}
			else if(r < h+n/2 && c >= w + n/2) {
				num += n*n/4;
				DAC(h, w + n/2, n/2);
			}
			else if(r >= h+n/2 && c < w+n/2) {
				num += 2*n*n/4;
				DAC(h+ n/2, w , n/2);
			}else if(r >= h+n/2 && c >= w+n/2){
				num += 3*n*n/4;
				DAC(h + n/2, w + n/2, n/2);
			}
		}
	}
}
