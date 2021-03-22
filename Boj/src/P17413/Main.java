package P17413;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		
		for(int i = 0; i<str.length(); i++) {
			int c = str.charAt(i);
			if(c == '<') {
				int j = i;
				StringBuilder tmp = new StringBuilder();
				while(j < str.length() && str.charAt(j) != '>') {
					tmp.append(str.charAt(j++));
				}
				tmp.append(str.charAt(j));
				i = j;
				sb.append(tmp.toString());
			}else if(c != ' ') {
				int j = i;
				StringBuilder tmp = new StringBuilder();
				while(j < str.length() && str.charAt(j) != ' ' && str.charAt(j) != '<') {
					tmp.append(str.charAt(j++));
				}
				sb.append(tmp.reverse().toString());
				i = j-1;
			}else if(c == ' '){
				sb.append(" ");
			}
		}
		
		System.out.println(sb);

	}

}
