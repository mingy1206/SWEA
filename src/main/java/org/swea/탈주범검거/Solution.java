package org.swea.탈주범검거;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    private static int N,M,R,C,L;
    private static int[][] tunnel;
    private static final int[][][] pipelines = {
            {},
            {{0,-1}, {1,0}, {0,1}, {-1,0}},
            {{0,-1}, {0,1}},
            {{1,0}, {-1,0}},
            {{0,-1}, {1,0}},
            {{1,0}, {0,1}},
            {{0,1}, {-1,0}},
            {{-1,0}, {0,-1}}
    };//x,y로 저장, 시계방향으로 저장
    private static boolean visited[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 세로 크기
            M = Integer.parseInt(st.nextToken()); // 가로 크기
            R = Integer.parseInt(st.nextToken()); // 맨홀 세로 위치
            C = Integer.parseInt(st.nextToken()); // 맨홀 가로 위치
            L = Integer.parseInt(st.nextToken()); // 소요된 시간

            tunnel = new int[N][M];
            visited = new boolean[N][M];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++){
                    tunnel[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = escape();
            System.out.println("#" + t + " " + result);
        }






    }
    private static int escape(){
        Queue<Point> queue = new ArrayDeque();
        Point coverPoint = new Point(C,R);
        queue.offer(coverPoint);
        visited[R][C] = true;
        int time = 0;
        int num = 1;
        while (!queue.isEmpty()){
            time++;
            int size = queue.size();
            if(time == L){
                return num;
            }
            for(int i = 0; i < size; i++){
                Point current = queue.poll();
                int pipeType = tunnel[current.y][current.x];
                int[][] pipeline = pipelines[pipeType];
                for(int[] direction : pipeline){
                    int nx = current.x + direction[0];
                    int ny = current.y + direction[1];

                    if(nx < 0 || nx >= M || ny < 0 || ny >=N || tunnel[ny][nx] == 0) continue;
                    if(visited[ny][nx]) continue;

                    int nextPipeType = tunnel[ny][nx];
                    int[][] nextPipeline = pipelines[nextPipeType];
                    for(int[] nextDirection : nextPipeline){
                        if((nextDirection[0] + direction[0] == 0) &&
                                (nextDirection[1] + direction[1] == 0)){
                            queue.offer(new Point(nx, ny));
                            num++;
                            visited[ny][nx] = true;
                            break;
                        }
                    }
                }
            }
        }
        return num;

    }



}
