package org.baekjoon.맥주_마시면서_걸어가기_dfs;

import java.awt.*;
import java.util.*;
import java.io.*;

public class Main {
    static int[][] directions = {{0,1},{0,1},{-1,0},{0,-1}};
    static Point house;
    static Point festival;
    static boolean[] visited;
    static int n;
    static Map<Integer, Point> map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            map = new HashMap<>();
            n = Integer.parseInt(br.readLine());
            visited = new boolean[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            house = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                map.put(i, new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            st = new StringTokenizer(br.readLine());
            festival = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            map.put(map.size(), festival);


            if(dfs(house)) System.out.println("happy");
            else System.out.println("sad");
        }
    }
    static boolean dfs(Point now){
        if(now.x == festival.x && now.y == festival.y){
            return true;
        }
        for(int i = 0; i < map.size(); i++){
            if(visited[i]) continue;
            Point next = map.get(i);
            if(Math.abs(now.x-next.x) + Math.abs(now.y-next.y) > 1000) continue;
            visited[i] = true;
            if(dfs(next)) return true;
        }
        return false;
    }
}
