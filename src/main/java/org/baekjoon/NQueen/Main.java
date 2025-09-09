package org.baekjoon.NQueen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        backtracking(0, new int[N]);
        System.out.println(result);
    }
    private static void backtracking(int row, int[] board){
        if(row == N){
            result++;
            return;
        }

        for (int col = 0; col < N; col++){
            if(isSafe(row, col, board)){
                board[row] = col;
                backtracking(row + 1, board);
            }
        }
    }
    private static boolean isSafe(int row, int col, int[] board){
        for(int i = 0; i < row; i++){
            if(board[i] == col) return false;

            if(Math.abs(i - row) == Math.abs(board[i] - col)) return false;
        }
        return true;
    }
}
