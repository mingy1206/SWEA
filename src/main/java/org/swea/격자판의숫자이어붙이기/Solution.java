package org.swea.격자판의숫자이어붙이기;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Solution {
    private static final int[][] directions = {{0,1}, {1,0}, {-1,0}, {0,-1}};
    private static int[][] grid;
    private static Map<Integer, Integer> count;
    // private static boolean visited[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++){
            //visited = new boolean[4][4];
            grid = new int[4][4];
            count = new HashMap<>();
            for(int i = 0; i < 4; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 4; j++){
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    Point start = new Point(j, i);
                    dfs(start, 0, grid[i][j]);
                }
            }

            System.out.println("#" + t + " " + count.size());
        }

    }
    private static void dfs(Point current, int depth, int index){
        if(depth >= 6){
            count.put(index, 1);
            return;
        }

            for(int[] direction : directions){
                int nx = current.x + direction[0];
                int ny = current.y + direction[1];
                if(!(nx < 0 || nx >= 4 || ny < 0 || ny >= 4)){
                    dfs(new Point(nx,ny), depth + 1, index*10 + grid[ny][nx]);
                }

        }
    }
}
