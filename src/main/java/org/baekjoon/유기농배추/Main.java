package org.baekjoon.유기농배추;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] ground;
    private static boolean[][] visited;
    private static final int[][] directions =
        {{1,0},{0,1},{-1,0},{0,-1}};
    private static int M;
    private static int N;
    private static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t = 0; t < T; t++){
            int result = 0;
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ground = new int[N][M];
            visited = new boolean[N][M];

            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                ground[y][x] = 1;
            }
            for (int i = 0; i < N; i++){
                for (int j = 0; j < M; j++){
                    if(!visited[i][j]&&ground[i][j]==1){
                        visited[i][j] = true;
                        bfs(i,j);
                        result++;
                    }
                }
            }
            System.out.println(result);
        }
    }
    private static void bfs(int y, int x){
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x,y));
        while(!queue.isEmpty()){
            Point point = queue.poll();
            for(int[] direction : directions){
                int dx = direction[0];
                int dy = direction[1];
                int nx = point.x + dx;
                int ny = point.y + dy;
                if((ny >= 0 && ny < N) && (nx >= 0 && nx < M)){
                    if(!visited[ny][nx]&&ground[ny][nx]==1){
                        queue.add(new Point(nx, ny));
                        visited[ny][nx] = true;
                    }
                }
            }
        }

    }
}
