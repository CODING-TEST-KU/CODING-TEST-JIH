package org.example.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob14503 {
    /*
    https://www.acmicpc.net/problem/14503

    25/8/10 22:27 ~ 23:08
    */
    private static int[] dc = { 0, 1, 0, -1 };
    private static int [] dr = { -1, 0, 1, 0 };
    private static int N, M;

    public static void main(String[] args) throws IOException{
        int sr, sc, direct;
        int[][] map;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        line = br.readLine();
        st = new StringTokenizer(line);
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        direct = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int r = 0; r < N; r++){
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int c = 0; c < M; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 시뮬레이션 시작
        int cleanedArea = 0;
        int r = sr;
        int c = sc;
        boolean[][] cleaned = new boolean[N][M];
        while(true){
//            System.out.println("r = " + r + " c = " + c);
            if(map[r][c] == 0 && !cleaned[r][c]){
                // 청소
                cleaned[r][c] = true;
                cleanedArea++;
            }
            if(fourDirectionsAreCleaned(map, cleaned, r, c)){
                // 후진
                int nr = r - dr[direct];
                int nc = c - dc[direct];
                if(nr < 0 || nr >= N || nc < 0 || nc >= M){
                    break;
                }
                if(map[nr][nc] == 1){
                    break;
                }
                r = nr;
                c = nc;
            }
            else{
                // 반시계 90도 회전 후 전진
                while(true){
                    direct = (direct - 1 + 4) % 4;
                    int nr = r + dr[direct];
                    int nc = c + dc[direct];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= M){
                        continue;
                    }
                    if(map[nr][nc] == 1){
                        continue;
                    }
                    if(!cleaned[nr][nc]){
                        r = nr;
                        c = nc;
                        break;
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(cleanedArea);
    }

    private static boolean fourDirectionsAreCleaned(int[][] map, boolean[][] cleaned, int r, int c){
        for(int direct = 0; direct < 4; direct++){
            int nr = r + dr[direct];
            int nc = c + dc[direct];
            if(nr < 0 || nr >= N || nc < 0 || nc >= M){
                continue;
            }
            if(!cleaned[nr][nc] && map[nr][nc] == 0){
                return false;
            }
        }
        return true;
    }
}