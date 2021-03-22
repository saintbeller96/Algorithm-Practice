package P6808규영이카드게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] card = new int[9];
	static int[] gcard = new int[9];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t= 1; t<=T; t++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int i = 0; i<9; i++) {
				gcard[i] = Integer.parseInt(stk.nextToken());
			}
			int idx = 0;
			for(int i = 1; i<=18; i++) {
				boolean flag = true;
				for(int j = 0; j<9; j++) {
					if(i == gcard[j]) {
						flag = false;
						break;
					}
				}
				if(flag) {
					card[idx++] = i;
				}
			}
			int a = 0;
			int b = 0;

			do {
				int sumIn = 0;
				int sumGyu = 0;
				for(int i = 0; i<9; i++) {
					if(card[i] > gcard[i]) {
						sumIn += card[i] + gcard[i];
					}else if(card[i] < gcard[i]){
						sumGyu+=card[i] + gcard[i];
					}
				}
				if(sumIn > sumGyu) {
					a++;
				}else if(sumGyu > sumIn) {
					b++;
				}
			}while(nextPermutation(card, 9));
			System.out.println("#" + t + " " + b + " " + a);
		}
	}
	private static boolean nextPermutation(int[] numbers, int N) {
		// step1. 꼭대기 찾기
		int i = N-1;
		while(i>0 && numbers[i-1] >= numbers[i]) {
			i--;
		}
		if(i == 0) return false; //더이상 다음 순열을 구할 수 없는 경우
		
		//step2. i-1과 교환할 다음단계 큰 수를 뒷쪽에서 찾기
		int j = N-1;
		while(numbers[j] <= numbers[i-1]) j--;
		
		//step 3. i-1, j 교환
		swap(numbers, i-1, j);
		
		int k = N-1;
		while(i<k) {
			swap(numbers, i++, k--);
		}
		
		return true;
	}
	
	private static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}

}
