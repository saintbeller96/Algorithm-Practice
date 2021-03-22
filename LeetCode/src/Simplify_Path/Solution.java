package Simplify_Path;

import java.util.*;

public class Solution {
	
	public String simplifyPath(String path) {
        
		path = path.replaceAll("/{0,}/", "/");
		path = path.replaceAll("/\\./", "/");
		System.out.println(path);
		String[] dir = path.split("/");
		
		Stack<String> stack = new Stack<>();
		for(String d: dir) {
			if(d.length() == 0) continue;
			if("..".equals(d)) {
				if(!stack.isEmpty())
					stack.pop();
			}else if(!".".equals(d)){
				stack.push(d);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(String str : stack) {
			sb.append('/').append(str);
		}
		
		return sb.length() != 0 ? sb.toString(): "/";
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		String path = "/a/../../b/../c//.//";
		System.out.println(s.simplifyPath(path));
	}

}
