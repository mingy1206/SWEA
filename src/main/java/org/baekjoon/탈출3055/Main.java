package org.baekjoon.탈출3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 결국 2개의 요소에 대한 bfs를 진행하면서 최솟값 구하기
// 물 먼저 bfs 진행 -> 고슴도치의 bfs 진행 -> 비버 굴 도착 -> 종료 제일 빨리 도착한 경우
                            //ㄴ> 갈 곳이 전부 물이면 -> 칵투스!
public class Main {
    private static int Height;
    private static int Width;
    private static String[][] forest;
    // x,y로 배열에 저장
    private static int[] start;
    private static int[] goal;
    private static List<int[]> water;
    private static boolean[][] visited;
    private static boolean[][] flooded;
    private static final int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Height = Integer.parseInt(st.nextToken());
        Width = Integer.parseInt(st.nextToken());
        forest = new String[Height][Width];
        flooded = new boolean[Height][Width]; // 침수 범위
        visited = new boolean[Height][Width]; // 고슴도치 탈출로
        start = new int[2];
        goal = new int[2];
        water = new ArrayList<>();

        for(int i = 0; i < Height; i++){
            forest[i] = br.readLine().split("");
        }
        for(int i = 0; i < Height; i++){
            for(int j = 0; j < Width; j++){
                if(forest[i][j].equals("D")){
                    goal[0] = j;
                    goal[1] = i;

                }
                if(forest[i][j].equals("S")){
                    start[0] = j;
                    start[1] = i;
                    visited[i][j] = true;
                }
                if(forest[i][j].equals("*")){
                    int[] w = {j,i};
                    water.add(w);
                    flooded[i][j] = true;
                }
                if(forest[i][j].equals("X")){
                    // 미리 방문처리를 통해 해당 위치에 간섭 못하게 처리
                    flooded[i][j] = true;
                    visited[i][j] = true;
                }
            }
        }
        int result = bfs();
        if (result == -1)
            System.out.println("KAKTUS");
        else
            System.out.println(result);
    }
    private static int bfs(){
        Queue<int[]> escapePath = new ArrayDeque<>();
        Deque<int[]> waterFlow = new ArrayDeque<>();

        escapePath.offer(new int[] {start[0], start[1], 0});
        for(int[] w : water){
            waterFlow.offer(new int[] {w[0], w[1], 0});
        }



        while(!escapePath.isEmpty()){ // 고슴도치의 이동 경로가 없으면 -> 고슴도치 익사
//            System.out.println("flood");
//            for(int i = 0; i < Height; i++)
//                System.out.println(Arrays.toString(flooded[i]));
//            System.out.println("visit");
//            for(int i = 0; i < Height; i++)
//                System.out.println(Arrays.toString(visited[i]));
//            System.out.println("--------------------------------------");

            int dx, dy, nx, ny, count;
            int[] escape = escapePath.poll();
            int escapeX = escape[0];
            int escapeY = escape[1];
            count = escape[2];
            // 목적지 도착 기저조건

            while (!waterFlow.isEmpty()){
                int[] water = waterFlow.poll();
                int waterX = water[0];
                int waterY = water[1];
                int depth = water[2];

                if(depth > count) {
                    waterFlow.addFirst(new int[]{waterX, waterY, depth});
                    break;
                }
                for(int[] direction : directions){
                    dx = direction[0];
                    dy = direction[1];
                    //침수 처리 먼저 -> 어차피 고슴도치는 침수될 곳에 갈 수 없기 때문에
                    nx = waterX + dx;
                    ny = waterY + dy;
                    if(nx == goal[0] && ny == goal[1]) continue;

                    if(nx < 0 || nx >= Width || ny < 0 || ny >= Height) continue;

                    if(!flooded[ny][nx]){
                        waterFlow.offer(new int[] {nx, ny, depth+1});// x,y로 저장
                        flooded[ny][nx] = true;
                    }
                }
            }

            for(int[] direction : directions){

                dx = direction[0];
                dy = direction[1];
                //침수 처리 먼저 -> 어차피 고슴도치는 침수될 곳에 갈 수 없기 때문에
                nx = escapeX + dx;
                ny = escapeY + dy;
                if(nx == goal[0] && ny == goal[1]) return ++count;
                if(nx < 0 || nx >= Width || ny < 0 || ny >= Height) continue;

                if(!visited[ny][nx]){
                    if(!flooded[ny][nx]) {// 고슴도치는 침수 여부도 한번 봐야 함.
                    escapePath.offer(new int[] {nx, ny, count+1}); // x,y로 저장
                    visited[ny][nx] = true;
                    }}
            }
        }
        return -1;
    }
}

