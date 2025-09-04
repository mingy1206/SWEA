package org.swea.벌꿀채집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int N, M, C;
    private static int[][] area;
    private static int[][] honeyBottle; //꿀통

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            int result = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            honeyBottle = new int[N][N];
            area = new int[N][N];
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++){
                    area[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int y = 0; y < N; y++){
                for (int x = 0; x < N-M+1; x++){
                    int[] arr = new int[M];
                    for(int k = 0; k < M; k++){
                        arr[k] = area[y][x+k];
                    }
                    honeyBottle[y][x] = search(arr, 0, 0,0);
                }
            }
            for (int y1 = 0; y1 < N; y1++)
                for (int x1 = 0; x1 < N; x1++)
                    for (int y2 = 0; y2 < N; y2++)
                        for (int x2 = 0; x2 < N; x2++){
                            if(y1==y2 && !(x2 >= x1+M || x1 >= x2+M)) continue;
                            result = Math.max(result, honeyBottle[y1][x1] + honeyBottle[y2][x2]);
                        }






            System.out.println("#" + t + " " + result);
        }
    }

    private static int search(int[] arr, int index, int sum, int pow){
        if(sum > C) return 0;
        if(index == arr.length) return pow;
        int take = search(arr, index+1, sum+arr[index],pow+arr[index]*arr[index]);

        int notTake = search(arr, index+1, sum, pow);
        return Math.max(take, notTake);
    }
}
