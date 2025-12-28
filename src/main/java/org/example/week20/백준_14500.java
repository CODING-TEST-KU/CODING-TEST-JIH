package org.example.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14500 {
    /*
    https://www.acmicpc.net/problem/14500

    25/12/23 08:12 ~ 08:52

    테트로미노 아래에 있는 수의 합의 최댓값을 구하자

    테트로미노는 모양은 5가지고, 회전, 대칭 가능

    N, M <= 500
    테트로미노는 5개
    각 테트로미노당 회전과 대칭은 8가지 경우 존재
    브루트포스 가능!

    */

    private static int[][] paper;
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        int[][][] tetrominos = {
                {{1, 1, 1, 1}},
                {{1, 1}, {1, 1}},
                {{1, 0}, {1, 0}, {1, 1}},
                {{1, 0}, {1, 1}, {0, 1}},
                {{1, 1, 1}, {0, 1, 0}}
        };
        // 각 테트로미노에 대해서 반복
        for(int[][] tetro : tetrominos){
            // 4번 회전하며 모든 위치에 놓아보면서 최댓값 갱신
            for(int i = 0; i < 4; i++){
                int maxSum = place(tetro);
                answer = Math.max(answer, maxSum);
                tetro = rotate(tetro);

//                for(int ii = 0; ii < tetro.length; ii++){
//                    System.out.println(Arrays.toString(tetro[ii]));
//                }
//                System.out.println();
            }
            // y축 대칭
            tetro = reflect(tetro);

//            for(int ii = 0; ii < tetro.length; ii++){
//                System.out.println(Arrays.toString(tetro[ii]));
//            }
//            System.out.println();

            // 4번 회전하며 모든 위치에 놓아보면서 최댓값 갱신
            for(int i = 0; i < 4; i++){
                int maxSum = place(tetro);
                answer = Math.max(answer, maxSum);
                tetro = rotate(tetro);

//                for(int ii = 0; ii < tetro.length; ii++){
//                    System.out.println(Arrays.toString(tetro[ii]));
//                }
//                System.out.println();
            }
        }

        // 정답 리턴
        System.out.println(answer);
    }

    private static int place(int[][] tetro){
        int maxSum = 0;
        for(int i = 0; i <= paper.length - tetro.length; i++){
            for(int j = 0; j <= paper[0].length - tetro[0].length; j++){
                int sum = 0;
                for(int ii = 0; ii < tetro.length; ii++){
                    for(int jj = 0; jj < tetro[0].length; jj++){
                        if(tetro[ii][jj] == 1){
                            sum += paper[i + ii][j + jj];
                        }
                    }
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    private static int[][] rotate(int[][] tetro){
        int[][] newTetro = new int[tetro[0].length][tetro.length];
        for(int i = 0; i < tetro.length; i++){
            for(int j = 0; j < tetro[0].length; j++){
                newTetro[j][tetro.length - i - 1] = tetro[i][j];
            }
        }
        return newTetro;
    }

    private static int[][] reflect(int[][] tetro){
        int[][] newTetro = new int[tetro.length][tetro[0].length];
        for(int i = 0; i < tetro.length; i++){
            for(int j = 0; j < tetro[0].length; j++){
                newTetro[i][tetro[0].length - j - 1] = tetro[i][j];
            }
        }
        return newTetro;
    }
}