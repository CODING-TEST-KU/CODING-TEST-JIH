package org.example.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Prob18808 {
    /*
    https://www.acmicpc.net/problem/18808

    25/07/02 20:59 ~

    0 1
    1 1
    0 1
    1 1

    1 0 1 0
    1 1 1 1

    1 1
    1 0
    1 1
    1 0

    어려웠던 점 : 시계 방향 90도 회전을 어떻게 구현해야할지 생각해내는 것이 어려웠음
     */

    public static void main(String[] args) throws IOException{
        int N, M, K, R, C;
        int [][] notebook;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력을 받는다
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<Sticker> stickers = new ArrayList();
        for(int i = 0; i < K; i++){
            line = br.readLine();
            st = new StringTokenizer(line);
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            int [][] shape = new int[R][C];
            for(int r = 0; r < R; r++){
                line = br.readLine();
                st = new StringTokenizer(line);
                for(int c = 0; c < C; c++){
                    shape[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            stickers.add(new Sticker(shape));
        }

        // 각 스티커에 대해서 반복
        notebook = new int[N][M];
        for(Sticker sticker : stickers){
            for(int i = 1; i <= 4; i++){
                int[][] target = sticker.shape;

                // 왼쪽 위부터 배치
                if(placeSticker(target, notebook)){
                    break;
                }

                // 회전
                sticker.rotate();
            }
        }
        System.out.println(countStickerArea(notebook));
    }

    private static boolean canPlace(int topY, int topX, int[][] sticker, int[][] notebook){
        for(int y = 0; y < sticker.length; y++){
            for(int x = 0; x < sticker[0].length; x++){
                if (sticker[y][x] == 1 && notebook[topY + y][topX + x] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean placeSticker(int[][] target, int[][] notebook){
        for(int topY = 0; topY + target.length <= notebook.length; topY++){
            for(int topX = 0; topX + target[0].length <= notebook[0].length; topX++) {
                if (canPlace(topY, topX, target, notebook)) {
                    place(topY, topX, target, notebook);
                    return true;
                }
            }
        }
        return false;
    }


    private static void place(int topY, int topX, int[][] sticker, int[][] notebook){
        for(int y = 0; y < sticker.length; y++){
            for(int x = 0; x < sticker[0].length; x++){
                if(sticker[y][x] == 1){
                    notebook[topY + y][topX + x] = 1;
                }
            }
        }
    }

    private static int countStickerArea(int[][] notebook){
        int area = 0;
        for(int y = 0; y < notebook.length; y++){
            for(int x = 0; x < notebook[0].length; x++){
                if(notebook[y][x] == 1){
                    area++;
                }
            }
        }
        return area;
    }

    static class Sticker{
        int [][] shape;

        public Sticker(int[][] shape){
            this.shape = shape;
        }

        private void rotate(){
            int[][] rotatedShape = new int [shape[0].length][shape.length];


            for(int c = 0; c < shape[0].length; c++){
                for(int r = 0; r < shape.length; r++){
                    rotatedShape[c][shape.length - 1 - r] = shape[r][c];
                }
            }

            this.shape = rotatedShape;
        }
    }
}