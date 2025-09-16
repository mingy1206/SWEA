package org.swea.원자소멸시뮬레이션;

<<<<<<< Updated upstream
import java.awt.*;
import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.List;
=======
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
>>>>>>> Stashed changes

public class Solution {
<<<<<<< Updated upstream
    private static final int[][] directions = {{0,1},{0,-1},{-1,0},{1,0}};//상하좌우 (dx,dy)로 저장
    static class Atom{
        int x, y, energy, dir;
        boolean isAlive;
        Atom(int x, int y, int dir, int energy){
            this.x = x; this.y = y; this.dir = dir; this. energy = energy;
            isAlive = true;
=======

    // 수정: 배열/행렬 좌표계에 맞게 방향 재정의 {y변화량, x변화량}
    // 0:상(y--), 1:하(y++), 2:좌(x--), 3:우(x++)
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Atom {
        int y, x, dir, energy;

        public Atom(int y, int x, int dir, int energy) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.energy = energy;
>>>>>>> Stashed changes
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
<<<<<<< Updated upstream
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
=======

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            List<Atom> atomList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                // 수정: 불필요한 convertedDir 변수 및 로직 제거
                atomList.add(new Atom(y, x, dir, energy));
            }

            long answer = 0;
>>>>>>> Stashed changes

            while (atomList.size() > 1) {
                Map<Point, List<Atom>> map = new HashMap<>();

<<<<<<< Updated upstream


=======
                for (Atom atom : atomList) {
                    // 수정: atom.dir을 직접 사용
                    int ny = atom.y + directions[atom.dir][0];
                    int nx = atom.x + directions[atom.dir][1];

                    if (ny < -2000 || ny > 2000 || nx < -2000 || nx > 2000) {
                        continue;
                    }

                    map.computeIfAbsent(new Point(ny, nx), k -> new ArrayList<>()).add(new Atom(ny, nx, atom.dir, atom.energy));
                }

                List<Atom> nextAtomList = new ArrayList<>();

                for (List<Atom> group : map.values()) {
                    if (group.size() > 1) {
                        for (Atom atom : group) {
                            answer += atom.energy;
                        }
                    } else {
                        nextAtomList.add(group.get(0));
                    }
                }

                atomList = nextAtomList;
            }
>>>>>>> Stashed changes

            System.out.println("#" + test_case + " " + answer);
        }
    }
}

