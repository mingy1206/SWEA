package org.swea.원자소멸시뮬레이션;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {

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
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            List<Atom> atomList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                // (y, x) 좌표 순서로 Atom 객체 생성
                atomList.add(new Atom(y, x, dir, energy));
            }

            long answer = 0; // 에너지 총합은 long 타입이 안전

            // 시뮬레이션: 원자가 1개 이하로 남을 때까지 반복
            while (atomList.size() > 1) {
                Map<Point, List<Atom>> map = new HashMap<>();

                // 1. 모든 원자 이동
                for (Atom atom : atomList) {
                    int ny = atom.y + directions[atom.dir][0];
                    int nx = atom.x + directions[atom.dir][1];

                    // 2. 맵 범위를 벗어난 원자는 소멸 (map에 추가하지 않음)
                    if (ny < -2000 || ny > 2000 || nx < -2000 || nx > 2000) {
                        continue;
                    }

                    // 3. 다음 위치(Point)를 기준으로 map에 추가
                    map.computeIfAbsent(new Point(ny, nx), k -> new ArrayList<>()).add(new Atom(ny, nx, atom.dir, atom.energy));
                }

                List<Atom> nextAtomList = new ArrayList<>(); // 다음 턴에 살아남을 원자 리스트

                // 4. 충돌 처리
                for (List<Atom> group : map.values()) {
                    if (group.size() > 1) {
                        // 2개 이상 모였으면 충돌
                        for (Atom atom : group) {
                            answer += atom.energy;
                        }
                    } else {
                        // 1개만 있으면 생존
                        nextAtomList.add(group.get(0));
                    }
                }

                // 5. 다음 시뮬레이션을 위해 리스트 교체
                atomList = nextAtomList;
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }
}