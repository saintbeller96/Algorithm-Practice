package K2019실패율;

import java.util.*;

public class Solution {
	public int[] solution(int N, int[] stages) {
		int[] arrival = new int[N+2];
		int[] state = new int[N+2];
		for(int n : stages) {
			for(int i = 1; i<=n; i++) {
				arrival[i]+=1;
			}
			state[n]+=1;
		}
        double[][] rate = new double[N][2];
        for(int i = 1; i<N+1; i++) {
        	if(arrival[i] == 0) rate[i-1][0] = 0;
        	else rate[i-1][0] = state[i]/(double)arrival[i];
        	rate[i-1][1] = i;
        }
        Arrays.sort(rate, (a, b)->Double.compare(a[1], b[1]));
        Arrays.sort(rate, (a, b)->Double.compare(b[0], a[0]));
        int[] answer = new int[N];
        for(int i = 0; i<N; i++) {
        	answer[i] = (int)rate[i][1];
        }
        return answer;
    }
	
	
	public static void main(String[] args) throws Exception {
		Solution s = new Solution();
		int N = 4;
		int[] stages = {4,4,4,4};
		System.out.println(Arrays.toString(s.solution(N, stages)));
	}
}
