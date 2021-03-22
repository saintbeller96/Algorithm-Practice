package P5015ls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[][] dp;
	static String pattern;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		pattern = br.readLine();
		int N = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i<N; i++) {
			String fileName = br.readLine();
			dp = new int[101][101];
			for(int j = 0; j<101; j++) {
				Arrays.fill(dp[j], -1);
			}
			
			if(isMatch(0, 0, fileName) == 1) sb.append(fileName).append('\n');
		}
		
		System.out.println(sb);
	}
	
	/**
	 * 
	 * @param p : index of a pattern
	 * @param f: index of a fileName
	 * @param fileName
	 * @return 0: does not match, 1: match
	 */	
	static int isMatch(int p, int f, String fileName) {
		if(dp[p][f] != -1) return dp[p][f];
		
		//패턴과 파일명이 매칭
		if(p < pattern.length() && f < fileName.length() && pattern.charAt(p) == fileName.charAt(f)) {
			return dp[p][f] = isMatch(p+1, f+1, fileName);
		}
		
		//만약 패턴의 끝에 도달했다면
		if(p == pattern.length()) {
			//파일명도 끝에 도달했다면 패턴과 파일명은 일치
			if(f == fileName.length()) {
				return dp[p][f] = 1;
			}
			//그렇지 않으면 일치한 것이 아님
			else {
				return dp[p][f] = 0;
			}
		}
		
		//현재 패턴의 글자가 *이라면 매칭할 문자의 개수를 정해야 함
		if(pattern.charAt(p) == '*') {
			//현재 f 인덱스부터 fileName의 길이까지 매칭을 시도
			//i<=fileName.length()인 이유는 46번째 줄에서 파일명의 끝에 도달했음을 알리기 위해
			for(int i = f; i<=fileName.length(); i++) {
				//매칭되면 dp 배열에 갱신 후 1 반환
				if(isMatch(p+1, i, fileName) == 1) {
					return dp[p][f] = 1;
				}
			}
		}	
		return 0;
	}
}
