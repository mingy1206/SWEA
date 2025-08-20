package org.swea.Contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    private static int lastLevelmaxNum;
    private static int startNode;
    private static List<List<Integer>> nodeList;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        for(int t = 1; t <= 10; t++){
            nodeList = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            int edgeNum = Integer.parseInt(st.nextToken())/2;
            startNode = Integer.parseInt(st.nextToken());
            visited = new boolean[100+1];

            for(int i = 0; i <= 100; i++)
                nodeList.add(i, new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < edgeNum; i++){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                nodeList.get(from).add(to);
            }
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(startNode);
            visited[startNode] = true;
            lastLevelmaxNum = 0;

            while(!queue.isEmpty()){
                int level = queue.size();
                int nowlevelMaxNum = 0;
                for(int i = 0; i < level; i++){
                    int currentNode = queue.poll();
                    nowlevelMaxNum = Math.max(nowlevelMaxNum, currentNode);
                    for(int next : nodeList.get(currentNode)){
                        if(!visited[next]){
                            queue.add(next);
                            visited[next] = true;
                        }
                    }
                }
                lastLevelmaxNum = nowlevelMaxNum;
            }
            System.out.println("#" + t + " " + lastLevelmaxNum);

        }
    }

}
