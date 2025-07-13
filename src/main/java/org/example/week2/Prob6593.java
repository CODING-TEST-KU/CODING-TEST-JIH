package org.example.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob6593 {
    /*
    https://www.acmicpc.net/problem/6593

    25/7/12 15:36 ~ 16:09


    3차원 공간에서 S에서 E로 가는 최단거리를 구하면 된다
     */

    private static int L, R, C;
    private static char [][][] building;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            // 입력 처리
            String line = br.readLine();
            if(line.equals("0 0 0")){
                break;
            }

            StringTokenizer st = new StringTokenizer(line);
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            int sfloor = -1;
            int srow = -1;
            int scol = -1;
            building = new char[L + 1][R + 1][C + 1];
            for(int floor = 1; floor <= L; floor++){
                for(int row = 1; row <= R; row++){
                    line = br.readLine();
                    for(int col = 1; col <= C; col++){
                        building[floor][row][col] = line.charAt(col - 1);
                        if(building[floor][row][col] == 'S'){
                            sfloor = floor;
                            srow = row;
                            scol = col;
                        }
                    }
                }
                br.readLine();
            }

            // 빌딩의 S에서 bfs 수행하여 최단거리를 구한다
            int minDist = bfs(sfloor, srow, scol);

            // E에 도달했으면 최단거리를 출력
            if(minDist == -1){
                sb.append("Trapped!\n");
            }
            else{
                sb.append("Escaped in " + minDist + " minute(s).\n");
            }
        }

        // 최종 출력
        System.out.println(sb.toString());
    }

    private static int bfs(int sf, int sr, int sc){
        final int [] df = {0, 0, 0, 0, 1, -1};
        final int [] dr = {0, 0, -1, 1, 0, 0};
        final int [] dc = {1, -1, 0, 0, 0, 0};
        boolean[][][] visited = new boolean[L + 1][R + 1][C + 1];
        Queue<Node> qu = new LinkedList<>();
        qu.add(new Node(sf, sr, sc, 0));

        while(!qu.isEmpty()){
            Node current = qu.poll();

            if(visited[current.floor][current.row][current.col]) continue;
            if(building[current.floor][current.row][current.col] == 'E') {
                return current.step;
            }

            visited[current.floor][current.row][current.col] = true;

//            System.out.println("===debug====");
//            System.out.println("building = ");
//            for(int floor = 1; floor <= L; floor++){
//                for(int row = 1; row <= R; row++){
//                    for(int col = 1; col <= C; col++){
//                        System.out.print(building[floor][row][col]);
//                    }
//                    System.out.println();
//                }
//                System.out.println();
//            }
//
//            System.out.println("visited = ");
//            for(int floor = 1; floor <= L; floor++){
//                for(int row = 1; row <= R; row++){
//                    for(int col = 1; col <= C; col++){
//                        System.out.print(visited[floor][row][col]);
//                    }
//                    System.out.println();
//                }
//                System.out.println();
//            }

            for(int direct = 0; direct < 6; direct++){
                int nf = current.floor + df[direct];
                int nr = current.row + dr[direct];
                int nc = current.col + dc[direct];
                if(nf < 1 || nf > L || nr < 1 || nr > R || nc < 1 || nc > C) continue;
                if(building[nf][nr][nc] == '#') continue;
                qu.add(new Node(nf, nr, nc, current.step + 1));
            }
        }

        return -1;
    }

    static class Node{
        int floor;
        int row;
        int col;
        int step;
        public Node(int floor, int row, int col, int step){
            this.floor = floor;
            this.row = row;
            this.col = col;
            this.step = step;
        }
    }
}