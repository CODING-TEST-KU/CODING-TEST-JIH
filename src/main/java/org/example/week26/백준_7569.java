package org.example.week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_7569 {
    /*
    https://www.acmicpc.net/problem/7569

    26/2/8 18:13 ~ 18:47

    토마토가 모두 익을 때까지 걸리는 시간의 최솟 일수를 구하자
    모두 익어 있는 상태면 0을, 모두 익지 못하는 상황이면 -1을 반환

    토마토가 익는 과정은 bfs와 유사하다. bfs가 끝나는 시점이 최소 일수이다
    다만 모든 토마토가 익었는지 검사해야한다.
    */

    public static void main(String[] args) throws IOException {
        백준_7569 main = new 백준_7569();
        main.solution();
    }

    private void solution() throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int H, N, M;
        int[][][] tomatoes;
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tomatoes = new int[H][N][M];
        for(int h = 0; h < H; h++){
            for(int n = 0; n < N; n++){
                st = new StringTokenizer(br.readLine());
                for(int m = 0; m < M; m++){
                    tomatoes[h][n][m] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // 모든 토마토가 익었는지 확인한다. 맞으면 0 반환
        List<int[]> starts = new ArrayList<>();
        boolean allRipe = true;
        for(int h = 0; h < H; h++){
            for(int n = 0; n < N; n++){
                for(int m = 0; m < M; m++){
                    if(tomatoes[h][n][m] == 0){
                        allRipe = false;
                    }
                    if(tomatoes[h][n][m] == 1) {
                        starts.add(new int[]{h, n, m});
                    }
                }
            }
        }

        if(allRipe){
            System.out.println(0);
            return;
        }

        // bfs로 토마토 상자를 순회한다
        int[] dh = { 1, -1, 0, 0, 0, 0 };
        int[] dn = {  0, 0, 1, -1, 0, 0 };
        int[] dm = { 0, 0, 0, 0, -1, 1 };
        Queue<int[]> qu = new LinkedList<>(); // 일수, h, n, m
        boolean[][][] visited = new boolean[H][N][M];
        for(int[] start : starts){
            qu.add(new int[] { 0, start[0], start[1], start[2] });
            visited[start[0]][start[1]][start[2]] = true;
        }

        int answer = 0;
        while(!qu.isEmpty()){
            int[] cur = qu.poll();

//            System.out.println(Arrays.toString(cur));

            for(int direct = 0; direct < 6; direct++){
                int nh = cur[1] + dh[direct];
                int nn = cur[2] + dn[direct];
                int nm = cur[3] + dm[direct];

                if(nh < 0 || nh >= H || nn < 0 || nn >= N || nm < 0 || nm >= M){
                    continue;
                }
                if(visited[nh][nn][nm]){
                    continue;
                }
                if(tomatoes[nh][nn][nm] != 0){
                    continue;
                }

                tomatoes[nh][nn][nm] = 1;
                visited[nh][nn][nm] = true;
                qu.add(new int [] { cur[0] + 1, nh, nn, nm });
                if(answer < cur[0] + 1){
                    answer = cur[0] + 1;
                }
            }
        }

        // 모든 토마토가 익었는지 검사한다. 모두 익지 않았으면 -1 반환
        allRipe = true;
        for(int h = 0; h < H; h++){
            for(int n = 0; n < N; n++){
                for(int m = 0; m < M; m++){
                    if(tomatoes[h][n][m] == 0){
                        allRipe = false;
                        break;
                    }
                }
            }
        }
        if(!allRipe){
            System.out.println(-1);
            return;
        }

        // 정답을 출력
        System.out.println(answer);
    }
}
