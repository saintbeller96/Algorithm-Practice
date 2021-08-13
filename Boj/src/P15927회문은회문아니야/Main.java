package P15927회문은회문아니야;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] dolls;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		Set<Character> checkSet = new HashSet<>();
		for (char c : str.toCharArray()) {
			checkSet.add(c);
		}
		if(checkSet.size() == 1){
			System.out.println(-1);
			return;
		}
		boolean result = isPalindrome(str);
		if(result){
			System.out.println(str.length()-1);
		}else{
			System.out.println(str.length());
		}
	}
	static boolean isPalindrome(String str){
		int s = 0, e = str.length()-1;
		while(s<=e){
			if(str.charAt(s) == str.charAt(e)){
				s++;
				e--;
			}else{
				return false;
			}
		}
		return true;
	}

}
