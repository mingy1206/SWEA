package org.baekjoon.트리의부모찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<ArrayList<Integer>> graph;
    private static boolean[] visited;
    private static int[] parentNodes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        parentNodes = new int[N+1];
        graph = new ArrayList<>();
        for(int i = 0; i <= N; i++) graph.add(i, new ArrayList<>());
        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        search(1);
        for(int i = 2; i <= N; i++){
            System.out.println(parentNodes[i]);
        }
    }
    private static boolean search(int node){
        visited[node] = true;
        for(int nextNode : graph.get(node)){
            if(!visited[nextNode]){
                parentNodes[nextNode] = node;
                search(nextNode);
            }
        }
        return false;
    }
}
