package org.swea.ColoringAPT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ColoringAPT_이민기 {
    private static Map<Integer, Integer> f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        f = new HashMap<>();
        f.put(0,0);
        f.put(1,2);
        f.put(2,3);
        // 노랑의 경우에는 f(N-1)개에 모두 칠할 수 있음
        // 파랑의 경우에눈 f(N-1)개에서 노랑이 위에 있는 경우에만 칠할 수 있음
        // 이때, f(N-1)의 노랑을 결정하는 요인은 f(N-2)이다.
        // 첫째줄에서 언급했듯이, 노랑의 f(?-1)에서 노랑은 전부 색칠 가능하기에 f(?)에서는 최상단 노랑의 개수와 동일하다.
        for(int i = 3; i <= N; i++){
            f.put(i, f.get(i-1) + f.get(i-2));
        }
        System.out.println(f.get(N));
    }
}
