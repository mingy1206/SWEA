package org.swea.미로2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    private static final int N = 100;
    private static int[][] directions = {{0,-1},{0,1},{-1,0},{1,0}};
    private static int[][] maze;
    private static int[] start; // x,y로 저장
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        start = new int[2];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 미로 모양 저장 및 출발지 도착지 확인
        for (int t = 1; t <= 10; t++) {
            br.readLine();
            maze = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    int mazeShape = Integer.parseInt(line[j]);
                    maze[i][j] = mazeShape;
                    if (mazeShape == 2) {
                        start[0] = j;
                        start[1] = i;
                    }
                }
            }
            int result = search();
            System.out.println("#" + t + " " + result);
        }
    }
    private static int search() {
        Queue<int[]> queue = new ArrayDeque();
        queue.offer(start);
        visited = new boolean[N][N];
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            for (int i = 0; i < 4; i++) {
                int[] direction = directions[i];
                int nx = x + direction[0];
                int ny = y + direction[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(!visited[ny][nx]) {
                    if(maze[ny][nx] == 0){
                        queue.offer(new int[] {nx,ny});
                        visited[ny][nx] = true;
                    }
                    if(maze[ny][nx] == 3){
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
}