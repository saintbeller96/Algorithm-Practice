package K2021표편집;

import java.util.*;
public class Solution {

    public String solution(int n, int k, String[] cmd) {
        Deque<Integer> stack = new ArrayDeque<>();
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i<n; i++){
            set.add(i);
        }
        int cur = k;
        for(String command : cmd){
            if(command.startsWith("C")){
                int prev = cur;
                if(cur == set.last()){
                    cur = set.lower(cur);
                }else{
                    cur = set.higher(cur);
                }
                set.remove(prev);
                stack.push(prev);
            }else if(command.startsWith("Z")){
                if(stack.isEmpty()) continue;
                set.add(stack.pop());
            }else{
                String[] splits = command.split(" ");
                int x = Integer.parseInt(splits[1]);
                if("U".equals(splits[0])){
                    for(int i = 0; i<x; i++){
                        cur = set.lower(cur);
                    }
                }else{
                    for(int i = 0; i<x; i++){
                        cur = set.higher(cur);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean[] answer = new boolean[n];
        for(int s : stack){
            answer[s] = true;
        }
        for(int i = 0; i<n; i++){
            if(!answer[i]) sb.append('O');
            else sb.append('X');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        System.out.println(sol.solution(n, k, cmd));
    }
}
