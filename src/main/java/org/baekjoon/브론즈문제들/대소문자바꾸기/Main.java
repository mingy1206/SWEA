package org.baekjoon.브론즈문제들.대소문자바꾸기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for(int i = 0; i < str.length(); i++){
            int c = str.charAt(i);
            if(c<91) System.out.print((char)(c+32));
            else System.out.print((char)(c-32));

        }
    }
}
