package org.baekjoon.나무재태크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N,M,K;
    private static A[][] ground;
    private static int[][] directions = {{0,1},{1,0},{-1,0},{0,-1},{-1,-1},{1,1},{1,-1},{-1,1}};
    static class A{
        int x;
        int y;
        int nutrients;
        int plus_nutrients;
        Queue<Integer> queue = new PriorityQueue<>();

        A(int x, int y, int nutrients, int plus_nutrients){
            this.x = x;
            this.y = y;
            this.nutrients = nutrients;
            this.plus_nutrients = plus_nutrients;
        }


        public void spring_summer(){
            Queue<Integer> temp_queue = new PriorityQueue<>();
            int num = 0;
            while (!queue.isEmpty()){
                int age = queue.poll();
                if(age <= nutrients){
                    nutrients -= age;
                    temp_queue.add(++age);
                }
                else{
                    num += age/2;
                }
            }
            queue = temp_queue;
            nutrients += num;
        }
        public void fall_winter(){
            Queue<Integer> temp_queue = new PriorityQueue<>();
            while (!queue.isEmpty()){
                int age = queue.poll();
                temp_queue.add(age);
                if(age%5 == 0){
                    for(int[] direction : directions){
                        int ny = y + direction[0];
                        int nx = x + direction[1];
                        if((ny>0&&ny<=N)&&(nx>0&&nx<=N)){
                            ground[ny][nx].queue.add(1);
                        }
                    }
                }
            }
            queue = temp_queue;
            nutrients += plus_nutrients;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ground = new A[N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) ground[i][j] = new A(j, i, 5, Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            ground[y][x].queue.add(z);
        }
        for(int i = 0; i < K; i++){
            spring_summer();
            fall_winter();
        }

        System.out.println(count());
    }

    private static void spring_summer(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                ground[i][j].spring_summer();
            }
        }
    }
    private static void fall_winter(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                ground[i][j].fall_winter();
            }
        }
    }
    private static int count(){
        int result = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                result += ground[i][j].queue.size();
            }
        }
        return result;
    }
}
