package org.example.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236 {
    /*
    25/11/2 23:25 ~ 00:37

    https://www.acmicpc.net/problem/16236

    아기 상어가 몇초까지 움직일 수 있는지 구하자

0 0 0 1
8 8 8 8
0 0 0 0
0 0 0 9
    */

    static int N;
    static int[][] map;
    static int sharkX, sharkY;
    static int sharkSize = 2;
    static int eatenFishes = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int y = 0; y < N; y++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int x = 0; x < N; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
                if(map[y][x] == 9){
                    sharkX = x;
                    sharkY = y;
                    map[y][x] = 0;
                }
            }
        }

        // 물고기가 없을때 까지
            // 상어의 현재 위치에서 가장 가까운 물고기들을 모두 찾는다
            // 그중에서 가장 위에 왼쪽에 있는 걸 고른다
            // 그 쪽으로 이동한다

        int sec = 0;
        while(fishExists()){
//            System.out.println();
//            System.out.println(" >> sec " + sec);

//            for(int i = 0; i < N; i++){
//                System.out.println(Arrays.toString(map[i]));
//            }

            int[] found = findClosestFish();
            move(found[0], found[1]);
            sec += found[2];
        }
        System.out.println(sec);
    }

    static boolean fishExists(){
        boolean ret = false;
        final int[] dx = { -1, 0, 1, 0 };
        final int[] dy = { 0, 1, 0, -1 };
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> qu = new LinkedList<>(); // x, y, dist
        qu.offer(new int[]{ sharkX, sharkY });
        while(!qu.isEmpty()) {
            int[] cur = qu.poll();
            for (int direct = 0; direct < 4; direct++) {
                int nx = cur[0] + dx[direct];
                int ny = cur[1] + dy[direct];
                if(nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1){
                    continue;
                }
                if(map[ny][nx] > sharkSize){
                    continue;
                }
                if(!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    qu.offer(new int[]{nx, ny});
                    if(isEatableFish(nx, ny)){
                        ret = true;
                    }
                }
            }
        }
        return ret;
    }

    static int[] findClosestFish(){
        // bfs로 가장 가까운 물고기들을 찾는다
        final int[] dx = { -1, 0, 1, 0 };
        final int[] dy = { 0, 1, 0, -1 };
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> qu = new LinkedList<>(); // x, y, dist
        qu.offer(new int[]{ sharkX, sharkY, 0 });
        visited[sharkY][sharkX] = true;
        int minDist = N * N + 1;
        PriorityQueue<int[]> candidates = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            for(int direct = 0; direct < 4; direct++){
                int nx = cur[0] + dx[direct];
                int ny = cur[1] + dy[direct];
                int nd = cur[2] + 1;
                if(nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1){
                    continue;
                }
                if(map[ny][nx] > sharkSize){
                    continue;
                }
                if(!visited[ny][nx]){
                    visited[ny][nx] = true;
                    qu.offer(new int[] { nx, ny, nd });

                    if(isEatableFish(nx, ny)){
                        if (nd < minDist){
                            minDist = nd;
                            candidates.clear();
                            candidates.add(new int[]{ nx, ny, nd });
//                            System.out.println(" >> found first fish ");
//                            System.out.println("x, y = " + nx + ", " + ny);
//                            System.out.println("minDist = " + minDist);
//                            System.out.println("cand size = " + candidates.size());
                        }
                        else if (nd == minDist){
                            candidates.add(new int[]{ nx, ny, nd});
//                            System.out.println(" >> found nth fish ");
//                            System.out.println("x, y = " + nx + ", " + ny);
//                            System.out.println("minDist = " + minDist);
//                            System.out.println("cand size = " + candidates.size());
                        }

                    }
                }
            }
        }

//        System.out.println("cand size = " + candidates.size());

        // 제일 위에 왼쪽에 있는 물고기 좌표를 반환한다
        return candidates.poll();
    }

    static boolean isEatableFish(int nx, int ny){
        return map[ny][nx] > 0 && map[ny][nx] < sharkSize;
    }

    static void move(int x, int y){
        // 좌표로 이동한다
        sharkX = x;
        sharkY = y;
        eatenFishes += 1;
        if(eatenFishes >= sharkSize){
            sharkSize += 1;
            eatenFishes = 0;
        }
        map[y][x] = 0;
//        System.out.println(" >> move ");
//        System.out.println("x, y = " + x + ", " + y);
//        System.out.println("sharkSize = " + sharkSize);
    }
}