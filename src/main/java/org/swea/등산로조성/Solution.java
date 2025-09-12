package org.swea.등산로조성;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 가장 높은 곳에서 시작 (가장 낮은 곳으로 가지 않을 수도 있음)
// 가장 높은 곳의 좌표에서 각각 DFS탐색 시작
// 공사 진행시와 진행하지 않을 경우 백트래킹을 통해서 분기 처리
public class Solution {
    private static final int[][] directions = {{0,1},{1,0},{-1,0},{0,-1}};
    private static int N,K;
    private static int[][] arr;
    private static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            result = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            List<Point> highPoint = new ArrayList<>();
            int maxHeight = 0;
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    int height = Integer.parseInt(st.nextToken());
                    arr[i][j] = height;
                    maxHeight = Math.max(maxHeight,height);
                }
            }

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                 if (arr[i][j] == maxHeight) highPoint.add(new Point(j,i));
                }
            }



            for(Point point : highPoint){
                boolean[][] visited = new boolean[N][N];
                visited[point.y][point.x] = true;
                DFS(point.x, point.y, arr[point.y][point.x], visited, 1, false);
            }


            System.out.println("#" + t + " " + result);




        }
    }
    private static void DFS(int x, int y, int currentHeight, boolean[][] visited, int depth, boolean construction){
        for(int d = 0; d < 4; d++){
            int[] direction = directions[d];
            int nx = x + direction[0];
            int ny = y + direction[1];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(visited[ny][nx]) continue;
            if(arr[ny][nx]-K >= currentHeight) continue;

            int height;
            if(arr[ny][nx] >= currentHeight && !construction){
                construction = true;
                height = currentHeight-1;
                visited[ny][nx] = true;
                DFS(nx, ny, height, visited,  depth+1, construction);
                construction =false;
                visited[ny][nx] = false;
            }
            else if(arr[ny][nx] < currentHeight){
                height = arr[ny][nx];
                visited[ny][nx] = true;
                DFS(nx, ny, height, visited,  depth+1, construction);
                visited[ny][nx] = false;
            }
        }
        result = Math.max(result, depth);
    }

}
