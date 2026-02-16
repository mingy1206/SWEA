package org.baekjoon.상어초등학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static Map<Integer, List<Integer>> student_like_student = new HashMap<>();
    private static int[][] class_room;
    private static boolean[][] fixed;
    private static int result = 0;
    private static final int[][] directions = {{0,1},{1,0},{-1,0},{0,-1}};
    private static class Student{
        int r;
        int c;
        int blank;
        int around_like_students;
        Student(){
            r = 401;
            c = 401;
            blank = -1;
            around_like_students = -1;
        }
        Student(int r, int c, int blank, int around_like_students){
            this.r = r;
            this.c = c;
            this.blank = blank;
            this.around_like_students = around_like_students;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        class_room = new int[N+1][N+1];
        fixed = new boolean[N+1][N+1];
        int[] order = new int[N*N];
        for(int i = 0; i < N*N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            order[i] = x;
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < 4; j++) list.add(Integer.parseInt(st.nextToken()));
            student_like_student.put(x,list);
        }

//        for (int i = 1; i <= N * N; i++) {
//            System.out.println(Arrays.toString(new List[]{student_like_student.get(i)}));
//        }

        setting(order);
//        for (int i = 0; i < N + 1; i++) {
//            System.out.println(Arrays.toString(class_room[i]));
//        }
        checking();

        System.out.println(result);

    }
    private static void setting(int[] order){
        for(int s :  order){
            Student student = new Student();
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(fixed[i][j]) continue;
                    int not_blank = 0;
                    int around_like_students = 0;
                    for(int[] direction : directions){
                        int nr = i + direction[0];
                        int nc = j + direction[1];
                        if ((nr > 0 && nr <= N) && (nc > 0 && nc <= N)){
                            if(fixed[nr][nc]){
                                not_blank++;
                                if(student_like_student.get(s).contains(class_room[nr][nc])){
                                    around_like_students++;
                                }
                            }
                        }else{
                            not_blank++;
                        }
                    }
                    Student tempStudent = new Student(i, j,4-not_blank, around_like_students);

                    if(student.around_like_students < tempStudent.around_like_students){
                        student = tempStudent;
                    } else if (student.around_like_students == tempStudent.around_like_students) {
                        if(student.blank < tempStudent.blank){
                            student = tempStudent;
                        } else if (student.blank == tempStudent.blank) {
                            if(student.r > tempStudent.r){
                                student = tempStudent;
                            } else if (student.r == tempStudent.r) {
                                if(student.c > tempStudent.c){
                                    student = tempStudent;
                                }
                            }
                        }
                    }
                }
            }
            class_room[student.r][student.c] = s;
            fixed[student.r][student.c] = true;
        }
    }

    private static void checking(){
        for(int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int s = class_room[i][j];
                int like_count = 0;
                for (int[] direction : directions) {
                    int nr = i + direction[0];
                    int nc = j + direction[1];
                    if ((nr > 0 && nr <= N) && (nc > 0 && nc <= N)) {
                        if (student_like_student.get(s).contains(class_room[nr][nc])) {
                            like_count++;
                        }
                    }
                }
                if(like_count == 0) result += 0;
                else if(like_count == 1) result += 1;
                else if(like_count == 2) result += 10;
                else if(like_count == 3) result += 100;
                else if(like_count == 4) result += 1000;
            }
        }
    }



}