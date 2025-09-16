package org.swea.키순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    private static int N;
    private static int M;
    private static boolean[] visited;
    private static int cnt;
    private static Map<Integer, List<Integer>> adjlist1;
    private static Map<Integer, List<Integer>> adjlist2;

    public static void main(String[] args) throws IOException {
        // 방향을 그대로하거나 역으로 바꾸었을 때 포함해서 모든 노드 이동이 가능하다면,
        // 키를 알 수 있다?
        //500*500
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            adjlist1 = new HashMap<>();
            adjlist2 = new HashMap<>();

            for (int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                List<Integer> tempList1 = adjlist1.getOrDefault(a, new ArrayList<>());
                tempList1.add(b);
                adjlist1.put(a, tempList1);
                List<Integer> tempList2 = adjlist2.getOrDefault(b, new ArrayList<>());
                tempList2.add(a);
                adjlist2.put(b, tempList2);
            }

            int result = 0;
            for (int i = 1; i <= N; i++){
                visited = new boolean[N+1];
                cnt = 0;
                DFS1(i);
                visited[i] = false;
                cnt--;
                DFS2(i);
                if(cnt == N) result++;
            }
            System.out.println("#" + t + " " + result);
        }

    }
    private static void DFS1(int current){
        if(visited[current]) return;
        visited[current] = true;
        cnt++;
        if (adjlist1.get(current) == null) return;

        for(int next : adjlist1.get(current)){
            DFS1(next);
        }
    }
    private static void DFS2(int current){
        if(visited[current]) return;
        visited[current] = true;
        cnt++;
        if (adjlist2.get(current) == null) return;

        for(int next : adjlist2.get(current)){
            DFS2(next);
        }
    }
}

/*
1
6
6
1 5
3 4
5 4
4 2
4 6
5 2
 */