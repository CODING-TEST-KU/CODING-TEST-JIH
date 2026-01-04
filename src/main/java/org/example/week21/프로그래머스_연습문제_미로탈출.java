package org.example.week21;

import java.util.*;

class 프로그래머스_연습문제_미로탈출 {
    /*
    26/1/1 23:41 ~ 23:58
    
    탈출에 걸리는 최단시간을 구하자
    
    bfs를 이용하자
    
    답 = start -> 레버로 이동하는 시간 + 레버 -> exit로 이동하는 시간
    */
    private int[] start, lever, exit;
    
    public int solution(String[] maps) {
        init(maps);
        int minToLever = bfs(start, lever, maps);
        if(minToLever == -1){
            return -1;
        }
        int minToExit = -1;
        minToExit = bfs(lever, exit, maps);
        if(minToExit == -1){
            return -1;
        }
        return minToLever + minToExit;
    }
    
    private void init(String[] maps){
        for(int y = 0; y < maps.length; y++){
            for(int x = 0; x < maps[y].length(); x++){
                if(maps[y].charAt(x) == 'S'){
                    start = new int[] { x, y };
                }
                if(maps[y].charAt(x) == 'L'){
                    lever = new int[] { x, y };
                }
                if(maps[y].charAt(x) == 'E'){
                    exit = new int[] { x, y };
                }
            }
        }
    }
    
    private int bfs(int[] start, int[] end, String[] maps){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int ret = -1;
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        Queue<int[]> qu = new LinkedList<>();
        visited[start[1]][start[0]] = true;
        qu.add(new int[] { start[0], start[1], 0 });
        while(!qu.isEmpty()){
            int[] cur = qu.poll(); 
            for(int i = 0; i < 4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx < 0 || nx >= maps[0].length() || ny < 0 || ny >= maps.length){
                    continue;
                }
                if(visited[ny][nx]){
                    continue;
                }
                if(maps[ny].charAt(nx) == 'X'){
                    continue;
                }
                visited[ny][nx] = true;
                qu.add(new int[] { nx, ny, cur[2] + 1 });
                if(end[0] == nx && end[1] == ny){
                    ret = cur[2] + 1;
                    break;
                }
            }
        }
        return ret;
    }
}