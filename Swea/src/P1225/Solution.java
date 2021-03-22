package P1225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int T;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		for (int t = 1; t <= 10; t++) {
			T = Integer.parseInt(br.readLine());
			stk = new StringTokenizer(br.readLine());
			int [] nums = new int[8];
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < 8; i++) {
				nums[i] = Integer.parseInt(stk.nextToken());
				if(nums[i]/15 < min) {
					min = nums[i]/15;
				}
			}
			for (int i = 0; i < 8; i++) {
				nums[i] = nums[i]%(15*(min-1));//나머지가 0인 경우 방지
			}			
            int p = 0;
            int cycle = 1;
            while(nums[p] != 0) {
                nums[p]-=cycle++;
                if(nums[p] <= 0) {
                	nums[p] = 0;
                	break;
                }
                if(cycle == 6) cycle = 1;
                p = (p+1)%8;
            }  
            System.out.print("#" + t);
            for (int i = p+1; i <= 8+p; i++) {
            	System.out.print(" " + nums[i%8]);
			}
            System.out.println();
		}			
	}
}
