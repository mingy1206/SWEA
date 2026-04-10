package org.baekjoon.효율적인해킹;

import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int M;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int[] result;
    static int cnt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        result = new int[N+1];
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(B).add(A);
        }

        int max_val = 0;
        for(int i = 1; i <= N; i++){
            visited = new boolean[N+1];
            cnt = 1;
            visited[i] = true;
            dfs(i);
            result[i] = cnt;
            max_val = Math.max(max_val,cnt);
        }

        int num = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= N; i++){
            if(max_val==result[i]) list.add(i);
        }

        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }

    static void dfs(int B){
        for(int i : graph.get(B)){
            if(!visited[i]){
                visited[i] = true;
                cnt++;
                dfs(i);
            }
        }
    }
}
