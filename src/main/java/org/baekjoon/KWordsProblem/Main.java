package org.baekjoon.KWordsProblem;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        int N = Integer.parseInt(line.trim());
        for(int n = 0; n < N; n++){
            String sentence = br.readLine();

            List<String> words = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(sentence, " ");
            while (st.hasMoreTokens()) {
                words.add(st.nextToken());
            }

            // 조건2 시작
            for (int i = 0; i <= words.size() - 3; ) {
                String first = words.get(i);
                String second = words.get(i + 1);
                String third = words.get(i + 2);

                String[] firstParts = splitPunct(first);
                String[] thirdParts = splitPunct(third);

                if (second.equals("of")
                    && thirdParts[0].equals("Korea")
                    && firstParts[1].isEmpty()) {

                    String newWord = "K-" + capitalize(firstParts[0]) + thirdParts[1];

                    words.set(i, newWord);
                    words.remove(i + 2);
                    words.remove(i + 1);

                } else {
                    i++;
                }
            }

            // 조건1 시작
            for (int i = words.size() - 2; i >= 0; i--) {
                String current = words.get(i);

                if (!current.equals("Korea")) continue;

                String newWord = "K-" + capitalize(words.get(i + 1));

                words.set(i, newWord);
                words.remove(i + 1);
            }

            for (String w : words) System.out.println(w);
        }
    }

    private static String[] splitPunct(String s) {
        if (s.isEmpty()) return new String[]{"", ""};

        char last = s.charAt(s.length() - 1);
        if ("!?,.".indexOf(last) != -1) {
            return new String[]{s.substring(0, s.length() - 1), String.valueOf(last)};
        }
        return new String[]{s, ""};
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        if (Character.isLowerCase(s.charAt(0))) {
            return Character.toUpperCase(s.charAt(0)) + s.substring(1);
        }
        return s;
    }
}