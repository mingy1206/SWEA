package org.baekjoon.옥수수밭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class Con implements Comparable<Con> {
        private int x;
        private int y;
        private int value;

        @Override
        public int compareTo(Con o) {
            return Integer.compare(o.value, this.value);
        }
        Con(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    private static int[][]  directions = {{0,1},{1,0},{-1,0},{0,-1}};
    private static int N,M,K;
    private static boolean[][] visited;
    private static boolean[][] inTheQueue;
    private static int[][] field;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        inTheQueue = new boolean[N+1][M+1];
        Queue<Con> q = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                field[i+1][j+1] = Integer.parseInt(st.nextToken());
                if(i == 0 || i == N-1 || j == 0 || j == M-1 ){
                    q.add(new Con(j+1, i+1, field[i+1][j+1]));
                    inTheQueue[i+1][j+1] = true;
                }
            }
        }
        K = Integer.parseInt(br.readLine());
        while(K-- > 0){
            if (q.isEmpty()) break;
            Con con = q.poll();
            int x = con.x;
            int y = con.y;
            visited[y][x] = true;
            for(int[] direction : directions){
                int nx = x + direction[0];
                int ny = y + direction[1];

                if(nx <= 0 || nx >M || ny <= 0 || ny >N) continue;
                if(inTheQueue[ny][nx] || visited[ny][nx]) continue;

                q.add(new Con(nx, ny, field[ny][nx]));
                inTheQueue[ny][nx] = true;
            }
            System.out.println(y + " " + x);
        }


    }
}
