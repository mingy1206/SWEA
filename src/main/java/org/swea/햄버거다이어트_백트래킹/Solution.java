package org.swea.햄버거다이어트_백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int best_taste;
    private static int[] T;
    private static int[] K;
    private static int N;
    private static int L;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tast_case = Integer.parseInt(br.readLine());
        for(int t = 1; t <= tast_case; t++){

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            T = new int[N];
            K = new int[N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                T[i] = Integer.parseInt(st.nextToken());
                K[i] = Integer.parseInt(st.nextToken());
            }

            best_taste = 0;
            visited = new boolean[N];
            for (int i = 0; i < N; i++){
                dfs(0,0,0);
            }

            System.out.println("#" + t + " " + best_taste);

        }
    }
    public static void dfs(int index, int k, int t){
        if(k > L) return;
        else best_taste = Math.max(t, best_taste);

        if(index == N) return;

        for(int i = index; i < N; i++){
            dfs(i+1, k+K[i], t+T[i]);
        }
    }
}
