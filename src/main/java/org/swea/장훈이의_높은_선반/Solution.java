package org.swea.장훈이의_높은_선반;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int N;
    private static int B;
    private static int[] P;
    private static boolean[] visited;
    private static int min_result;
    public static void main(String[] args) throws IOException {
        min_result = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            P = new int[N];
            for(int i = 0; i < N; i++) P[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(P);
            for(int num = 0; num < N; num++){
                visited = new boolean[N];
                for(int i = 0; i < N; i++){
                    if(!visited[i]){
                        visited[i] = true;
                        dfs(num, 0,  P[i]);
                        visited[i] = false;
                    }
                }
            }
            System.out.println("#" + t + " " + (min_result-B));

        }
    }
    public static void dfs(int num, int cnt, int pTop){
        if(pTop >= B) {//기저 조건
            if(pTop < min_result) min_result = pTop;
            return;
        }else if(num <= cnt){
            return;
        }else{
            for(int i = 0; i < N; i++){
                if(!visited[i]){
                    visited[i] = true;
                    dfs(num, cnt + 1, pTop + P[i]);
                    visited[i] = false;
                }
            }
        }
    }
}
