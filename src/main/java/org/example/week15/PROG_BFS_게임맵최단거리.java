package org.example.week15;

import java.util.*;

class PROG_BFS_게임맵최단거리 {
    /*
    14:09 ~ 14:17
    
    BFS로 최단거리를 구한다
    
    도달할 수 없으면 -1을 반환한다
    */
    public int solution(int[][] maps) {
        int N = maps.length;
        int M = maps[0].length;
        int answer = -1;
        final int[] dx = { -1, 0, 1, 0 };
        final int[] dy = { 0, 1, 0, -1 };
        
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[]{ 0, 0, 1 }); // x, y, step
        visited[0][0] = true;
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            for(int i = 0; i < 4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx < 0 || nx >= M || ny < 0 || ny >= N){
                    continue;
                }
                if(maps[ny][nx] == 0){
                    continue;
                }
                if(visited[ny][nx]){
                    continue;
                }
                
                visited[ny][nx] = true;
                qu.add(new int[]{ nx, ny, cur[2] + 1});
                if(nx == M - 1 && ny == N - 1){
                    answer = cur[2] + 1;
                }
            }
        }
        return answer;
    }
}