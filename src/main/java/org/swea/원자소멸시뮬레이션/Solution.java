package org.swea.원자소멸시뮬레이션;

import java.awt.*;
import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.List;

// 가로나 세로 거리 중 더 거리가 멀리 떨어져 있는 횟수 만큼 반복
public class Solution {
    private static final int[][] directions = {{0,1},{0,-1},{-1,0},{1,0}};//상하좌우 (dx,dy)로 저장
    static class Atom{
        int x, y, energy, dir;
        boolean isAlive;
        Atom(int x, int y, int dir, int energy){
            this.x = x; this.y = y; this.dir = dir; this. energy = energy;
            isAlive = true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t<=T; t++){
            int totalEnergy = 0;
            int N = Integer.parseInt(br.readLine());
            Atom[] atoms = new Atom[N];
            for (int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                atoms[i] = new Atom(Integer.parseInt(st.nextToken())*2, Integer.parseInt(st.nextToken())*2,
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }


            boolean endFlag = true;
            while (endFlag){
                Map<Integer, List<Integer>> explosionMap = new HashMap<>();
                int aliveCount = N;
                for(int i=0; i<N; i++){
                    Atom atom = atoms[i];
                    if (!atom.isAlive){
                        aliveCount--;
                        continue;
                    }
                    int[] direction = directions[atom.dir];
                    int nx = atom.x + direction[0];
                    int ny = atom.y + direction[1];

                    if(nx >2000 || ny > 2000 || nx < -2000 || ny < -2000){
                        atoms[i].isAlive = false;
                        aliveCount--;
                        continue;
                    }
                    atoms[i].x = nx;
                    atoms[i].y = ny;

                    int index = (nx + 2000) * 4001 + (ny + 2000);
                    List<Integer> explosionList = explosionMap.getOrDefault(index, new ArrayList<Integer>());
                    explosionList.add(i);
                    explosionMap.put(index, explosionList);
                }
                for(List<Integer> explosionList : explosionMap.values()){
                    if(explosionList.size() <= 1) continue;
                    for (int index : explosionList){
                        totalEnergy += atoms[index].energy;
                        atoms[index].isAlive = false;
                        aliveCount--;
                    }
                }
                if(aliveCount == 0) endFlag = false;
            }

            System.out.println("#" + t + " " + totalEnergy);





        }
    }
}
/*
2
4
-1000 0 3 5
1000 0 2 3
0 1000 1 7
0 -1000 0 9
4
-1 1 3 3
0 1 1 1
0 0 2 2
-1 0 0 9
 */