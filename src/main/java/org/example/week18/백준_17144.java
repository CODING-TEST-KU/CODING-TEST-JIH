package org.example.week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_17144 {
    /*

    https://www.acmicpc.net/problem/17144

    11/29 19:52 ~ 20:40

    시뮬레이션 유형이니 지문 그대로 구현
    */

    private static int R, C, T;
    private static int[][] room;
    private static int filterUpperR, filterLowerR;

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        filterUpperR = 0;
        filterLowerR = 0;
        room = new int[R][C];
        for(int r = 0; r < R; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++){
                int dust = Integer.parseInt(st.nextToken());
                if(dust > 0 ){
                    room[r][c] = dust;
                }
                if(dust == -1){
                    if(filterUpperR == 0){
                        filterUpperR = r;
                    }
                    else {
                        filterLowerR = r;
                    }
                }
            }
        }

        // T초동안 시뮬
        for(int t = 1; t <= T; t++){
            // 미세먼지 확산
            spread();

//            for(int i = 0; i < R; i++){
//                System.out.println(Arrays.toString(room[i]));
//            }
//            System.out.println();

            // 청정기 가동
            filter();

//            for(int i = 0; i < R; i++){
//                System.out.println(Arrays.toString(room[i]));
//            }
//            System.out.println();

        }

        int answer = 0;
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                answer += room[r][c];
            }
        }

        // 미세먼지 합 구하기
        System.out.println(answer);
    }

    private static void spread(){
        final int[] dr = {-1, 0, 1, 0};
        final int[] dc = {0, -1, 0, 1};
        int[][] temp = new int[room.length][room[0].length];
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                if(room[r][c] > 0){
                    int amount = room[r][c] / 5;
                    int spreaded = 0;
                    for(int i = 0; i < 4; i++){
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if(nr < 0 || nr >= R || nc < 0 || nc >= C){
                            continue;
                        }
                        if(nc == 0 && (nr == filterUpperR || nr == filterLowerR)){
                            continue;
                        }
                        temp[nr][nc] += amount;
                        spreaded++;
                    }
                    temp[r][c] += room[r][c] - amount * spreaded;
                }
            }
        }
        for(int r = 0; r < R; r++){
            for(int c = 0; c < C; c++){
                room[r][c] = temp[r][c];
            }
        }
    }

    private static void filter(){
        int in = room[filterUpperR][0];
        int temp;
        int r = filterUpperR;
        int c = 1;
        while(!(r == filterUpperR && c == 0)){
            temp = room[r][c];
            room[r][c] = in;
            in = temp;
            if(r == filterUpperR && c < C - 1){
                c++;
            }
            else if(c == C - 1 && r > 0){
                r--;
            }
            else if(r == 0 && c > 0){
                c--;
            }
            else if(c == 0 && r < filterUpperR){
                r++;
            }
        }

        in = room[filterLowerR][0];
        r = filterLowerR;
        c = 1;
        while(!(r == filterLowerR && c == 0)){
            temp = room[r][c];
            room[r][c] = in;
            in = temp;
            if(r == filterLowerR && c < C - 1){
                c++;
            }
            else if(c == C - 1 && r < R - 1){
                r++;
            }
            else if(r == R - 1 && c > 0){
                c--;
            }
            else if(c == 0 && r > filterLowerR){
                r--;
            }
        }
    }
}