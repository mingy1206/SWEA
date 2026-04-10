package org.baekjoon.단어공부;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] arr = new int[26];

        for(int i = 0; i < str.length(); i++) arr[Character.toLowerCase(str.charAt(i))-'a']++;

        int maxValue = 0;
        int index = 0;
        for(int i = 0; i < 26; i++){
            if(arr[i] > maxValue){
                maxValue = arr[i];
                index = i;
            }
        }

        int cnt = 0;
        for(int i = 0; i < 26; i++) {
            if(maxValue == arr[i]) cnt++;
        }

        if (cnt >= 2) {
            System.out.println("?");
        }else{
            System.out.println((char)(index+'A'));
        }


    }

}
