package org.example.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob14890 {
    /*
    https://www.acmicpc.net/problem/14890

    25/8/3 17:09 ~ 18:18

    생각해보니 배열을 전치시켜서 입력으로 만들면 반복되는 코드를 하나의 함수로 줄일 수 있다.
    */

    public static void main(String[] args) throws IOException{
        solution1();
    }

    private static void solution1() throws IOException {
        int N, L;
        int[][] map;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int r = 0; r < N; r++){
            line = br.readLine();
            st = new StringTokenizer(line);
            for(int c = 0; c < N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 알고리즘
        // 가로 세로의 각 줄을 스캔
        // 모든 칸의 높이가 같으면 길로 추가
        // 길이를 증가
        // 칸을 오를 때는 이전에 길이가 L이상이고 높이 차가 1이어야 함
        // 만약 내려갔는 중이었다면 이전 길이가 2L이상이어야 함
        // 칸을 내려갈 때는 내려가는 중임을 저장
        // 두번 째 내려가는 거면 길이가 L이었는지 보고 길이를 1로 초기화

        int rowCount = 0;
        for(int r = 0; r < N; r++){
            int prevHeight = map[r][0];
            int prevLength = 1;
            boolean isRoad = true;
            boolean isDescending = false;

            for(int c = 1; c < N; c++){
                if(map[r][c] == prevHeight){
                    prevLength += 1;
                }
                else if(map[r][c] > prevHeight){
                    if(map[r][c] - prevHeight > 1 || isDescending && prevLength < 2 * L || !isDescending && prevLength < L){
                        isRoad = false;
                        break;
                    }
                    prevHeight = map[r][c];
                    prevLength = 1;
                    isDescending = false;
                }
                else{
                    if(prevHeight - map[r][c] > 1 || isDescending && prevLength < L){
                        isRoad = false;
                        break;
                    }
                    prevHeight = map[r][c];
                    prevLength = 1;
                    isDescending = true;
                }
            }
            if(isDescending && prevLength < L){
                isRoad = false;
            }

            if(isRoad) {
                rowCount += 1;
//                System.out.println("row = " + r);
            }
        }


        // 길의 개수를 출력
        int colCount = 0;
        for(int c = 0; c < N; c++){
            int prevHeight = map[0][c];
            int prevLength = 1;
            boolean isRoad = true;
            boolean isDescending = false;

            for(int r = 1; r < N; r++){
                if(map[r][c] == prevHeight){
                    prevLength += 1;
                }
                else if(map[r][c] > prevHeight){
                    if(map[r][c] - prevHeight > 1 || isDescending && prevLength < 2 * L || !isDescending && prevLength < L){
                        isRoad = false;
                        break;
                    }
                    prevHeight = map[r][c];
                    prevLength = 1;
                    isDescending = false;

                }
                else{
                    if(prevHeight - map[r][c] > 1 || isDescending && prevLength < L){
                        isRoad = false;
                        break;
                    }
                    prevHeight = map[r][c];
                    prevLength = 1;
                    isDescending = true;

                }
            }
            if(isDescending && prevLength < L){
                isRoad = false;
            }

            if(isRoad){
                colCount += 1;
//                System.out.println("col = " + c);
            }
        }

        System.out.println(rowCount + colCount);
    }
}