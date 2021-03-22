package P9369암호깨기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	static int T, N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			String[] samples = new String[N];
			for(int i = 0; i<N; i++) {
				samples[i] = br.readLine();
			}
			String decode = br.readLine();
			String cryptogram = br.readLine();
			
			ArrayList<String> matchingList = new ArrayList<String>();
			
			for(int i = 0; i<N; i++) {
				if(samples[i].length() != decode.length()) continue;
				if(canMatch(samples[i].toCharArray(), decode.toCharArray())) {
					matchingList.add(samples[i]);
				}
			}
			//주어진 샘플 문자들로 해독할 수 없는 경우
			if(matchingList.size() == 0) {
				sb.append("IMPOSSIBLE").append('\n');
				continue;
			}
			//하나의 문자가 2개 이상으로 해독되는지 확인하기 위한 트리맵
			TreeMap<Character, TreeSet<Character>> rMatching = new TreeMap<Character, TreeSet<Character>>();
			//2개로 해독되는 경우
			//a->b   f->b
			//b를 키로 가지는 트리맵의 value에서 중복 확인 가능
			for(int i = 0; i<26; i++) {
				rMatching.put((char)('a'+i), new TreeSet<Character>());
			}
			
			//해독판
			char[] matching = new char[26];
			
			for(String sampleStr : matchingList) {
				char[] sample = sampleStr.toCharArray();
				for(int i = 0; i < sample.length; i++) {
					char s = sample[i];//샘플
					char d = decode.charAt(i);//해독문
					TreeSet<Character> ts = rMatching.get(d);
					//해독 트리맵을 체크
					if(!ts.contains(s)) {
						matching[s-'a'] = d;
						ts.add(s);
						//2개 이상 해독될 수 있는 문자라면 해당 문자를 해독판에서 모두 ?로 변경
						if(ts.size() > 1) {
							for(char c: ts) {
								matching[c-'a'] = '?';
							}
						}
					}
				}
			}
			//알파벳26개 중 25개가 확정되었다면 나머지 하나도 확정됨
			int cnt = 0;
			int idx = -1;
			boolean[] check = new boolean[26];
			for(int i = 0; i<26; i++) {
				if(matching[i] == '\0') {
					cnt++;
					idx = i;
				}else if(matching[i] != '?'){
					check[matching[i]-'a'] = true;
				}
			}
			if(cnt == 1) {
				for(int i = 0; i<26; i++) {
					if(!check[i]) {
						matching[idx] = (char)(i+'a');
						break;
					}
				}
			}
			//암호 해독표 작성 끝
			
			//암호 해독하기
			for(int i =0; i<cryptogram.length(); i++) {
				char c = cryptogram.charAt(i);
				//매칭되는 문자가 없다면 ?로 출력
				if(matching[c-'a'] == '\0') {
					sb.append('?');
				}else sb.append(matching[c-'a']);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	//해독될 수 있는 문자열인지 확인
	static boolean canMatch(char[] sample, char[] decode) {
		char[] s2d = new char[26];
		char[] d2s = new char[26];
		for(int i = 0; i < sample.length; i++) {
			char s = sample[i];//샘플
			char d = decode[i];//해독문
			//만약 모순이 발생한다면 false 리턴
			if(s2d[s -'a'] != '\0' && s2d[s -'a'] != d) {
				return false;
			}
			if(d2s[d - 'a'] != '\0' && d2s[d - 'a'] != s) {
				return false;
			}
			s2d[s-'a'] = d;
			d2s[d - 'a'] = s;
		}		
		return true;
	}
}
