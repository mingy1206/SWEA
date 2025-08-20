package org.swea.정사각형방;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    private static int[][] arr;
    private static boolean[][] visited;
    private static int N;
    private static int max_room;
    private static int max_room_num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            visited = new boolean[N][N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            max_room = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(!visited[i][j]){
                        visited[i][j] = true;
                        bfs(i*N+j, arr[i][j]);
                    }
                }
            }
            System.out.println("#" + t + " " + max_room_num + " " + max_room );
        }
    }
    public static void bfs(int n, int room_num){
        int cnt = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add((n));
        int[][] directions = {{0,1},{1,0},{-1,0},{0,-1}};
        while (!queue.isEmpty()){
            n = queue.poll();
            int y = n/N;
            int x = n%N;
            for(int[] dxy : directions){
                int nx = dxy[0] + x;
                int ny = dxy[1] + y;
                if((0<=nx && nx < N)&&(0<=ny && ny < N)){
                    if(arr[y][x] + 1 == arr[ny][nx]){
                        queue.add(ny*N + nx);
                        visited[ny][nx] = true;
                        cnt++;
                    }
                }
            }

        }
        if(cnt > max_room) {
            max_room = cnt;
            max_room_num = room_num;
        }
        else if(cnt == max_room){
            if (max_room_num > room_num){
                max_room_num = room_num;
            }
        }
    }


}
