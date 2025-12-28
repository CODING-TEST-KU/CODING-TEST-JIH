package org.example.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_15684 {
    /*
    https://www.acmicpc.net/problem/15684

    25/12/26 22:56 ~ 23:52

    i번 세로선의 결과가 i번이 되도록 만들기 위해 추가해야할 가로선의 최소갯수
    3 초과거나 그런 가로선을 만들 수 업으면 -1

    브루트포스를 이용할 수 있을까?
    남은 점선에 가로선을 추가하는 모든 경우를 보아야함
    가로선은 1~3개만 사용 가능

    N <= 10, H <= 30
    남은 점선의 개수 <= 300

    300 C 3 < 300 * 300 * 300

    브루트포스 가능할 것 같은 느낌?

    */
    static int[][] ladder;
    static int answer = 4;
    static int[][] points;

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M, H;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H][N];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a - 1][b - 1] = 1;
            ladder[a - 1][b] = -1;
        }

        points = new int[H * (N - 1)][2];
        int index = 0;
        for(int i = 0; i < H; i++){
            for(int j = 0; j < N - 1; j++){
                points[index][0] = i;
                points[index][1] = j;
                index++;
            }
        }

        //가로선을 추가하는 모든 경우의 수
        for(int lineNumber = 0; lineNumber <= 3; lineNumber++){
//            System.out.println("lineNumber = " + lineNumber);
            placeLine(-1, lineNumber, lineNumber);
        }

        if(answer == 4){
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    static void placeLine(int prev, int lineToDraw, int lineNumber){
        if(lineToDraw <= 0){
            playLadder(lineNumber);
            return;
        }
        for(int i = prev + 1; i < points.length; i++){
            if(!canPlace(points[i])){
//                System.out.println("point " + i + "skip");
                continue;
            }
            drawLine(points[i]);
            placeLine(i, lineToDraw - 1, lineNumber);
            removeLine(points[i]);
        }
    }

    static void playLadder(int lineNumber){
//        for(int i = 0; i < ladder.length; i++){
//            System.out.println(Arrays.toString(ladder[i]));
//        }
//        System.out.println();

        for(int start = 0; start < ladder[0].length; start++){
            int cur = start;
            for(int i = 0; i < ladder.length; i++){
                cur += ladder[i][cur];
            }
            if(cur != start){
                return;
            }
        }
        answer = Math.min(answer, lineNumber);
    }

    static boolean canPlace(int[] point){
        return ladder[point[0]][point[1]] == 0 && ladder[point[0]][point[1] + 1] == 0;
    }

    static void drawLine(int[] point){
        ladder[point[0]][point[1]] = 1;
        ladder[point[0]][point[1] + 1] = -1;
    }

    static void removeLine(int[] point){
        ladder[point[0]][point[1]] = 0;
        ladder[point[0]][point[1] + 1] = 0;
    }
}