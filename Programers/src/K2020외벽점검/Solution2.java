package K2020외벽점검;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {
	public int solution(int n, int[] weak, int[] dist) {
		int w = weak.length;
		int[] xWeak = new int[w*2-1];
		
		Arrays.sort(dist);

		int idx = 0;
		while(idx < w*2-1) {
			if(idx < w) {
				xWeak[idx] = weak[idx++];
			}else {
				xWeak[idx] = weak[(idx++)%w] + n;
			}
		}
		int howMany = dist.length+1;
		for(int i = 0; i<w; i++) {
			int s = i;
			int[] curWeak = new int[w];
			
			for(int k = 0; k<w; k++) {
				curWeak[k] = xWeak[s+k];
			}
			do {
				howMany = Math.min(howMany, work(curWeak, dist));
			}while(nextPermutation(dist, dist.length));
		}
		if(howMany == dist.length+1) {
			return -1;
		}
		return howMany;
	}
	
	private int work(int[] weak, int[] dist) {
		int idx = 0;
		for(int di = 0; di< dist.length; di++) {
			int cover = dist[di] + weak[idx];
			for(int wi = idx+1; wi < weak.length; wi++) {
				if(cover < weak[wi]) {
					idx = wi;
					break;
				}
			}
			if(cover >= weak[weak.length-1]) {
				return di+1;
			}
		}
		return dist.length+1;
	}
	
	private boolean nextPermutation(int[] numbers, int N) {
		int i = N-1;
		while(i > 0 && numbers[i-1] >= numbers[i]) {
			i--;
		}
		if(i == 0) return false;
		
		int j = N-1;
		while(numbers[j] <= numbers[i-1]) j--;
		swap(numbers, i-1, j);	
		int k = N-1;
		while(i<k) {
			swap(numbers, i++, k--);
		}
		return true;
	}
	
	private void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
	public static void main(String[] args) {
		Solution2 s = new Solution2();
		int[] weak = {1, 5, 6, 10};
		int[] dist = {8};
		
		int[] weak2 = {0, 10, 50, 80, 120, 160};
		int[] dist2 = {1, 10, 5, 40, 30};
		
		int[] weak3 = {0};
		int[] dist3 = {4, 1, 5, 6};
		int n = 1;
		int a = s.solution(n, weak, dist);
		
		System.out.println(a);

	}
}
