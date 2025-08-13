package org.swea.파리퇴치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int N;
    private static int M;
    private static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max_count = 0;
            for(int i = 0; i <= N-M; i++){
                for(int j=0; j <= N-M; j++){
                    int count = count_fly(j,i);
                    if(count > max_count) max_count = count;
                }
            }
            System.out.println("#" + t + " " + max_count);
        }






    }
    public static int count_fly(int x, int y){
        int count = 0;
        for(int i = 0; i < M; i++){
            for(int j=0; j < M; j++){
                count += arr[y+i][x+j];
            }
        }
        return count;
    }
}
