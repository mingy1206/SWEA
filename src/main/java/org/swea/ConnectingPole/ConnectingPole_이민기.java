package org.swea.ConnectingPole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ConnectingPole_이민기 {
    private static Map<Integer, Integer> f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        f = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        f.put(0,0);
        f.put(1,2);
        f.put(2,5);
        for(int i = 3; i <= N; i++){
            // 1cm 늘려야 할 때는 f(N-1)에 f(1)(1cm) 경우의 수를 구해주기
            // 2cm 늘려야 할 때는 f(N-2)에 f(2)(2cm) 경우의 수를 구해주기
            f.put(i, 2*f.get(i-1) + f.get(i-2));
        }
        System.out.println(f.get(N));
    }
}
