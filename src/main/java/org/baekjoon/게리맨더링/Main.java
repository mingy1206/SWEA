package org.baekjoon.게리맨더링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] populations;
    private static int minimum_population = Integer.MAX_VALUE;
    private static Map<Integer, List<Integer>> connection;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = i+1;
        populations = new int[N];
        connection = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) populations[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            List<Integer> temp = new ArrayList<>();
            for(int j = 0; j < n; j++){
                temp.add(Integer.parseInt(st.nextToken()));
            }
            connection.put(i+1, temp);
        }

        int r_count = N/2;
        for(int i = 0; i< r_count; i++){
            boolean[] visited = new boolean[N];
            combination(arr, visited, N, i+1,0,0);
        }
        if (minimum_population < Integer.MAX_VALUE)
            System.out.println(minimum_population);
        else
            System.out.println(-1);

    }


    private static void combination(int[] arr, boolean[] visited, int n, int r, int start, int count){
        //기저
        if(count == r){
            calculation(arr, visited);
            return;
        }

        for(int i = start; i < n; i++){
            visited[i] = true;

            combination(arr, visited, n, r, i + 1, count + 1);

            visited[i] = false;
        }
    }
    private static void calculation(int[] arr, boolean[] visited){
        List<Integer> selected = new ArrayList<>();
        List<Integer> unselected = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            if(visited[i]) selected.add(arr[i]);
            else unselected.add(arr[i]);
        }

        if(!connectingCheck(selected) || !connectingCheck(unselected)) return;

        int selected_poppulations = 0;
        int unselected_populations = 0;
        for(int sel : selected) selected_poppulations += populations[sel-1];
        for(int unsel : unselected) unselected_populations += populations[unsel-1];

        minimum_population = Math.min(minimum_population,
            Math.abs(selected_poppulations - unselected_populations));
    }

    private static boolean connectingCheck(List<Integer> graph){
        if(graph.size() == 0) return false;

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[populations.length + 1];
        queue.add(graph.get(0));
        visited[graph.get(0)] = true;

        int count = 1;

        while(!queue.isEmpty()){
            int num = queue.poll();
            for(int next : connection.get(num)){
                if(graph.contains(next) && !visited[next]){
                    visited[next] = true;
                    queue.add(next);
                    count++;
                }
            }
        }

        return count == graph.size();
    }
}
