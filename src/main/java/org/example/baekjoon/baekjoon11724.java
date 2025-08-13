package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon11724 {
    private static int result = 0;
    private static int N = 0;
    private static int M = 0;
    private static boolean flag;
    private static boolean[] visited;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] network = new int[N+1][N+1];
        visited = new boolean[N+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            network[u][v] = 1;
            network[v][u] = 1;
        }


        for(int i = 1; i <= N; i++) {
            if(!visited[i]){
                search(network, i);
                result++;
            }
        }
        System.out.println(result);


    }
    public static void search(int[][] network, int i) {
        visited[i] = true;
        for(int j = 1; j <= N; j++) {
            if(network[i][j] == 1 && !visited[j]) {
                search(network, j);
            }
        }
    }
}