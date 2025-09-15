package org.swea.나무높이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxHeight = 0;
        int N = Integer.parseInt(br.readLine());
        int[] trees = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());



        for (int i = 0; i < N; i++){
            int Height = Integer.parseInt(st.nextToken());
            trees[i] = Height;
            maxHeight = Math.max(maxHeight, Height);
        }

        int[][] treePlanes = new int[maxHeight+1][N];
        for (int i = 0; i < N; i++) treePlanes[0][i] = trees[i];

        for(int i = 0; i < N; i++){
            for (int j = 1; j <maxHeight; j++){
                if(treePlanes[j-1][i] - maxHeight > 2)
                treePlanes[j][i] =
            }
        }



    }
}
