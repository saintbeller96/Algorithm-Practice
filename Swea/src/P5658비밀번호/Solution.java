package P5658비밀번호;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	private static int T, N, K, Answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;
        T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
        	stk = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(stk.nextToken());
            K = Integer.parseInt(stk.nextToken());
            
            TreeSet<Integer> ts = new TreeSet<>();
            int n = N/4;
            
            String str = br.readLine();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            for(int k = 0; k < n; k++) {
            	for(int i = 0; i<4;i++) {
                	String s = sb.substring(i*n, i*n+n);
                	int m = Integer.parseInt(s, 16);
                	ts.add(m);
                }
            	char c = sb.charAt(N-1);
            	sb.deleteCharAt(N-1);
            	sb.insert(0, c);        	
            }
            Iterator<Integer> iter = ts.descendingIterator();
            int k = 1;
            while(iter.hasNext()) {
            	if(k == K) {
            		break;
            	}
            	iter.next();
            	k++;
            }
            Answer = iter.next();
            System.out.println("#" + t +" " + Answer);
        }

	}

}
