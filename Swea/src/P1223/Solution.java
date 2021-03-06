package P1223;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());
            Stack<Integer> stack = new Stack<>();
            Stack<Character> ss = new Stack<>();
            StringBuilder sb = new StringBuilder();
             
            String str = br.readLine();
 
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if(c != '+' && c != '*') {
                    sb.append(c);
                }else {
                    if(ss.isEmpty()) {
                        ss.push(c);
                    }else {
                        char cc = ss.peek();
                        if(cc == '+') {
                            if(c == '*') {
                                ss.push(c);
                            }else {
                                while(!ss.isEmpty()) {
                                    sb.append(ss.pop());                                    
                                }
                                ss.push(c);
                            }
                        }else {
                            if(c == '*') {
                                ss.push(c);
                            }else {
                                while(!ss.isEmpty()) {
                                    sb.append(ss.pop());
                                }
                                ss.push(c);
                            }
                        }
                    }
                }
                     
            }
            while(!ss.isEmpty()) {
                sb.append(ss.pop());
            }
            str = sb.toString();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if(c == '+') {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a+b);
                }else if(c == '*') {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a*b);
                }else {
                    stack.push(c-'0');
                }
            }       
            System.out.println("#" + t + " " + stack.pop());
        }
    }
}