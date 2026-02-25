package org.baekjoon.맥주_마시면서_걸어가기;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.List;

public class Main {
    static int[][] directions = {{0,1},{0,1},{-1,0},{0,-1}};
    static Point house;
    static Point festival;
    static boolean[][] visited;
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Point> map = new HashMap<>();
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            house = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                map.put(i, new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            st = new StringTokenizer(br.readLine());
            festival = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            map.put(map.size(), festival);


            if(dfs(house,0, 20)) System.out.println("happy");
            else System.out.println("sad");
        }
    }
    static boolean dfs(Point now, int cd, int beer){
        if(now.x == festival.x && now.y == festival.y){
            return true;
        }
        for(int i = 0; i <n; i++){
            if(now.x == convenience[i].x && now.y == convenience[i].y){
                beer = 20;
            }
        }
        if(cd > 50){
            if(beer <= 0) return false;
            else{
                beer -= 1;
                cd += 1;
            }
        }
        for(int[] direction : directions){
            int nx = now.x + direction[0];
            int ny = now.y + direction[1];
            if((nx < 0 || nx > 32767)||(ny < 0 || ny > 32767)) return false;
            //if(visited[ny][nx]) return false;
            if(dfs(new Point(nx,ny), cd+1, beer-1)) return true;
        }
        return false;
    }
}
