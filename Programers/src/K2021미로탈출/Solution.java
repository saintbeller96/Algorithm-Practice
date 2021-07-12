package K2021미로탈출;

import java.util.*;

public class Solution {
    List<int[]>[] adjList;
    List<int[]>[] reversAdjList;
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        adjList = new List[roads.length+1];
        reversAdjList = new List[roads.length+1];
        for(int i = 1; i<= n; i++){
            adjList[i] = new ArrayList<>();
            reversAdjList[i] = new ArrayList<>();
        }
        for(int[] road : roads){
            int p = road[0];
            int q = road[1];
            int s = road[2];
            adjList[p].add(new int[] {q, s});
            reversAdjList[q].add(new int[] {p, s});
        }

        return answer;
    }
    private int findWay(int n, int t, int start, int end, int[][] roads, int[] traps){
        int ret = Integer.MAX_VALUE;
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[n+1][t];
        que.offer(new int[] {start, 0});
        visited[start][0] = true;
        while(!que.isEmpty()){
            int[] p = que.poll();
            int cur = p[0];
            int cost = p[1];
            int trap_bit = p[2];
            if(cur == end) {
                ret = Math.min(ret, cost);
            }

            //현재 방이 함정이라면 길을 반대로 돌림
            if(Arrays.stream(traps).anyMatch(trap -> trap == cur)){
                
            }else{
                
            }

//            for(int[] edge : ) {
//                int target = edge[1];
//                int addCost = edge[2];
//                if(!visited[target][trap_bit]){
//                    visited[target][trap_bit] = true;
//                    que.offer(new int[] {target, cost + addCost, trap_bit});
//                }
//            }
        }

        return ret;
    }

    public static void main(String[] args) {

    }
}
