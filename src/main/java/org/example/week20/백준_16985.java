package org.example.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_16985 {
    /*

    https://www.acmicpc.net/problem/16985

    25/12/23 23:38 ~ 00:44

    주어진 판을 이용해서 만들 수 있는 미로의 최단경로의 길이를 구하자

    최단경로가 없으면 -1을 반환
    각 판은 회전 가능함
    판의 순서도 바꿀 수 있음

    완전탐색으로 풀어야할 것 같음
    */
    private static int[][][] boards;
    private static boolean[] selected;
    private static int[][][] cube;
    private static int answer = 126;
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boards = new int[5][5][5];
        for(int board = 0; board < 5; board++){
            for(int r = 0; r < 5; r++){
                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line);
                for(int c = 0; c < 5; c++){
                    boards[board][r][c] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // 판을 쌓아올릴 경우의 수를 계산
        cube = new int[5][5][5];
        selected = new boolean[5];
//        for(int i = 0; i < 5; i++){
//            selected[i] = true;
//            copy(cube[0], boards[i]);
//            stackBoards(1);
//            selected[i] = false;
//        }
        stackBoards(0);


        if(answer == 126){
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    private static void copy(int[][] dst, int[][] src){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                dst[i][j] = src[i][j];
            }
        }
    }

    private static void stackBoards(int depth){
        if(depth >= 5){
            int[] sy = { 0, 0, 4, 4 };
            int[] sx = { 0, 4, 0, 4 };
            int[] ey = { 4, 4, 0, 0 };
            int[] ex = { 4, 0, 4, 0 };
            for(int i = 0; i < 4; i++){
                if(cube[0][sy[i]][sx[i]] == 1){
//                    for(int k = 0; k < 2; k++){
//                        for(int j = 0; j < 5; j++){
//                            System.out.println(Arrays.toString(cube[k][j]));
//                        }
//                    }
//                    System.out.println();

                    int minDist = bfs(sx[i], sy[i], ex[i], ey[i]);
                    answer = Math.min(answer, minDist);
                }
            }
            return;
        }
        for(int i = 0; i < 5; i++){
            if(selected[i]){
                continue;
            }
            selected[i] = true;
//            System.out.println("select " + i);
            copy(cube[depth], boards[i]);
            for(int j = 0; j < 4; j++){
                stackBoards(depth + 1);
                rotate(cube[depth]);
            }
            selected[i] = false;
        }
    }

    private static int bfs(int sx, int sy, int ex, int ey){
        int[] dx = {-1, 0, 1, 0, 0, 0};
        int[] dy = {0, 1, 0, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};
        boolean[][][] visited = new boolean[5][5][5];
        Queue<int[]> qu = new LinkedList<>();
        visited[0][sy][sx] = true;
        qu.add(new int[]{ sx, sy, 0, 0 });
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            for(int i = 0; i < 6; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int nz = cur[2] + dz[i];
                if(nx < 0 || nx > 4 || ny < 0 || ny > 4 || nz < 0 || nz > 4){
                    continue;
                }
                if(visited[nz][ny][nx]){
                    continue;
                }
                if(cube[nz][ny][nx] == 0){
                    continue;
                }
                visited[nz][ny][nx] = true;
                qu.add(new int[]{ nx, ny, nz, cur[3] + 1 });
                if(nz == 4 && ny == ey && nx == ex){

                    return cur[3] + 1;
                }
            }
        }
        return 126;
    }

    private static void rotate(int[][] board){
        int[][] temp = new int[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                temp[j][4 - i] = board[i][j];
            }
        }
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                board[i][j] = temp[i][j];
            }
        }
    }
}