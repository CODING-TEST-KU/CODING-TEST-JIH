package org.example.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob2206 {
    /*
    https://www.acmicpc.net/problem/2206

    25/7/13 10:17 ~ 10:53

     벽 한개는 뚫고 지나갈 수 있다
     가장 짧은 경로를 구해야 한다

     벽을 뚫을 수 있는 케이스를 잘 생각해보아야 한다
     */

    private static int N, M;
    private static int[][] map;
    private static final int INF = 2000000;

    private static final int[] dx = { 1, -1, 0, 0 };
    private static final int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        for(int y = 1; y <= N; y++){
            line = br.readLine();
            for(int x = 1; x <= M; x++){
                if(line.charAt(x - 1) == '1'){
                    map[y][x] = 1;
                }
                else{
                    map[y][x] = 0;
                }
            }
        }

        // 출발지에서 bfs를 수행해서 최단 경로 후보를 구한다
        int[][] distFromStart = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(distFromStart[i], INF);
        }
        bfs(1, 1, distFromStart);

//        System.out.println("distFromStart");
//        for(int i = 1; i <= N; i++){
//            for(int j = 1; j <= M; j++){
//                System.out.print(distFromStart[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();


        // 도착지에서 bfs를 수행한다
        int[][] distFromEnd = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            Arrays.fill(distFromEnd[i], INF);
        }
        bfs(M, N, distFromEnd);

//        System.out.println("distFromEnd=");
//        for(int i = 1; i <= N; i++){
//            for(int j = 1; j <= M; j++){
//                System.out.print(distFromEnd[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        // 출발지와 도착지 사이에 있는 벽들에 대해서 벽을 뚫을 수 있는 모든 경우에 대해서 점검해서 최단 경로 후보를 교체한다
        int minDist = decideMinimumDist(distFromStart, distFromEnd);

        // 정답을 출력한다.
        System.out.println(minDist);
    }

    private static int decideMinimumDist(int[][] distFromStart, int[][] distFromEnd){
        int minDistCandidate = distFromStart[N][M];
        for(int y = 1; y <= N; y++){
            for(int x = 1; x <= M; x++){
                if(map[y][x] == 1){
                    int minDistFromStart = INF;
                    for(int direct = 0; direct < 4; direct++){
                        int nx = x + dx[direct];
                        int ny = y + dy[direct];
                        if(nx < 1 || nx > M || ny < 1 || ny > N){
                            continue;
                        }
                        minDistFromStart = Math.min(minDistFromStart, distFromStart[ny][nx]);
                    }

                    int minDistFromEnd = INF;
                    for(int direct = 0; direct < 4; direct++){
                        int nx = x + dx[direct];
                        int ny = y + dy[direct];
                        if(nx < 1 || nx > M || ny < 1 || ny > N){
                            continue;
                        }
                        minDistFromEnd = Math.min(minDistFromEnd, distFromEnd[ny][nx]);
                    }

                    minDistCandidate = Math.min(minDistCandidate, minDistFromStart +  minDistFromEnd + 1);

//                    System.out.println("minDistCadidate = " + minDistCandidate);
                }
            }
        }

        if(minDistCandidate >= INF){
            return -1;
        }
        return minDistCandidate;
    }

    private static void bfs(int sx, int sy, int[][] distMap){
        boolean[][] visited = new boolean[N + 1][M + 1];
        Queue<Node> qu = new LinkedList<>();
        qu.add(new Node(sx, sy, 1));

        while(!qu.isEmpty()){
            Node current = qu.poll();
            if(visited[current.y][current.x]) continue;
            visited[current.y][current.x] = true;
            distMap[current.y][current.x] = current.step;

            for(int direct = 0; direct < 4; direct++){
                int nx = current.x + dx[direct];
                int ny = current.y + dy[direct];
                if(nx < 1 || nx > M || ny < 1 || ny > N) continue;
                if(map[ny][nx] == 1) continue;
                qu.add(new Node(nx, ny, current.step + 1));
            }
        }
    }

    static class Node{
        int x;
        int y;
        int step;

        public Node(int x, int y, int step){
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}