package P1953최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TTT {

   static class Node {
      ArrayList<int[]> adjList;

      public Node() {
         this.adjList = new ArrayList<>();
      }

   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine().trim());

      int V, E, start;

      // ���� ����Ʈ
      Node[] adjList;

      // ���ͽ�Ʈ���� ����� ��� array
      int[] distance;

      // ���ͽ�Ʈ�󿡼� ����� PriorityQueue (���� ª�� �Ÿ��� �������� ���;� �ؼ�)
      PriorityQueue<int[]> que;
      V = Integer.parseInt(st.nextToken());
      E = Integer.parseInt(st.nextToken());
      start = Integer.parseInt(br.readLine().trim());
      adjList = new Node[V + 1];
      distance = new int[V + 1];

      que = new PriorityQueue<>(new Comparator<int[]>() {

         // int[] o1 = {����, ����ġ}, ����ġ �������� ���� ������
         @Override
         public int compare(int[] o1, int[] o2) {
            return o1[1] - o2[1];
         }

      });
      for (int i = 1; i <= V; i++) {
         adjList[i] = new Node();
      }

      for (int i = 0; i < E; i++) {
         st = new StringTokenizer(br.readLine().trim());
         int u = Integer.parseInt(st.nextToken()); // ���� ����
         int v = Integer.parseInt(st.nextToken()); // ���� ����
         int w = Integer.parseInt(st.nextToken()); // ����ġ
//         boolean found = false;
         ArrayList<int[]> curList = adjList[u].adjList;
//         for (int[] curNode : curList) {
//            // u�� ����� v�� �̹� �ִµ� �̹� �ִ� ����ġ�� ���� ���� ������ ũ��, ���Ž����ش�
//            if (curNode[0] == v) {
//               if (curNode[1] > w)
//               {
//                  curNode[1] = w;
//               }
//               found = true;
//               break;
//            }
//         }
//         if (!found)
         curList.add(new int[] { v, w });

      }
      Arrays.fill(distance, Integer.MAX_VALUE);
      distance[start] = 0;

      // Dijkstra
      // distance: start(�Է¹��� ���۳��) ���� i��° node������ �ִܰŸ��� ������ node
      que.add(new int[] { start, 0 });
      while (!que.isEmpty()) {
         // pq���� ���� �Ÿ��� ª�� ���� ��������
         int[] cur = que.poll();
         if (distance[cur[0]] < cur[1])
            continue;
         for (int[] next : adjList[cur[0]].adjList) {
            // next[0] <= ����
            // next[1] <= cur�������� next���������� �Ÿ�
            // (start~cur������ �Ÿ� + cur=>next)������ �Ÿ��� (start~next)������ �Ÿ��� ��
            if (distance[cur[0]] + next[1] < distance[next[0]]) {
               // ������Ʈ
               distance[next[0]] = distance[cur[0]] + next[1];
               que.add(new int[] {next[0], next[1] + cur[1]});
            }
         }
      }
      for (int i = 1; i <= V; i++) {
         if (distance[i] == Integer.MAX_VALUE) {
            System.out.println("INF");// ���� �Ÿ��� ������ Max���̸� INF���
         } else {
            System.out.println(distance[i]);
         }
      }

      br.close();
   }

}