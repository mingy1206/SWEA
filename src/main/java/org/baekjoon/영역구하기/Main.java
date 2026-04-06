package org.baekjoon.영역구하기;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.List;

public class Main {
    static int M;
    static int N;
    static int K;
    static boolean[][] visited;
    static int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[M][N];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;

            for(int y = y1; y <= y2; y++){
                for(int x = x1; x <= x2; x++){
                    visited[y][x] = true;
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        Queue<Point> queue = new ArrayDeque<>();
        for (int i =0; i < M; i++){
            for (int j = 0; j < N; j++){
                if (visited[i][j]) continue;
                visited[i][j] = true;
                queue.add(new Point(j,i));
                int extent = 0;
                while (!queue.isEmpty()){
                    Point p = queue.poll();
                    int x = p.x;
                    int y = p.y;
                    extent++;
                    for(int[] direction : directions) {
                        int nx = x + direction[0];
                        int ny = y + direction[1];
                        if((0<=ny && ny<M)&&(0<=nx && nx<N)){
                            if (visited[ny][nx]) continue;
                            visited[ny][nx] = true;
                            queue.add(new Point(nx, ny));
                        }
                    }
                }
                list.add(extent);
            }
        }
        Collections.sort(list);
        System.out.println(list.size());
        for (int li : list) System.out.print(li + " ");
    }
}
