package org.swea.요리사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int N;
    private static int[][] S;
    private static int result;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++){
            result = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            S = new int[N][N];
            visited = new boolean[N];
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    S[i][j] = Integer.parseInt(st.nextToken());
            }
            combination(0,0);
            System.out.println("#" + t + " " +result);
        }
    }
    private static void combination(int index, int cnt){
        if (cnt == N/2){
            calcul();
            return;
        }
        for(int i = index; i < N; i++){
            visited[i] = true;
            combination(i + 1, cnt + 1);
            visited[i] = false;
        }
    }
    private static void calcul(){
        int foodA = 0;
        int foodB = 0;
        for(int i = 0; i < N-1; i++){
            for(int j = i+1; j <N; j++){
                if(visited[i] && visited[j]) foodA += S[i][j] + S[j][i];
                if(!visited[i] && !visited[j]) foodB += S[i][j] + S[j][i];
            }
        }
        int howS = Math.abs(foodA-foodB);
        if( howS < result)
            result = howS;
    }
}

/*
1
4
0 5 3 8
4 0 4 1
2 5 0 3
7 2 3 0
 */
