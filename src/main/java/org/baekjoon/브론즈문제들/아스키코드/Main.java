package org.baekjoon.브론즈문제들.아스키코드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = br.readLine().charAt(0);
        System.out.println(c);
    }
}
