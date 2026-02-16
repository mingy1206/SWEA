package org.baekjoon.alphabet;

import java.io.*;
import java.util.*;
// 문제 정의: 모두 소문자인 단어 s에 대해서 각 알파벳의 처음 위치
// 문제 풀이: 배열 index와 아스키 코드를 이용


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] alphabet = new int[26];
        for(int i = 0; i < 26; i++) alphabet[i] = -1;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int index = c-'a';
            if(alphabet[index] == -1) alphabet[index] = i;
        }

        for (int i = 0; i < 26; i++) System.out.print(alphabet[i] + " ");


    }

}
