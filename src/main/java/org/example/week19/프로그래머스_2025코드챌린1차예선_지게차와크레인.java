package org.example.week19;

import java.util.*;

class 프로그래머스_2025코드챌린1차예선_지게차와크레인 {
    /*
    25/12/3 18:00 ~ 18:50
    
    남은 컨테이너의 수를 구하자
    
    지게차는 어떻게 구현해야할까?
    컨테이너를 둘러싼 추가 공간을 넣고, bfs로 이번에 꺼낼 컨테이너를 찾는다?
    
    
    */
    private int W;
    private int H;
    private char[][] map;
    private boolean[][] removed;
    public int solution(String[] storage, String[] requests) {
        // 맵 생성
        H = storage.length;
        W = storage[0].length();
        map = new char[H + 2][W + 2];
        removed = new boolean[H + 2][W + 2];
        for(int i = 0; i < H + 2; i++)
            Arrays.fill(map[i], '.');
        
        // 맵 초기화
        for(int i = 1; i <= H; i++){
            String s = storage[i - 1];
            for(int j = 1; j <= W; j++){
                map[i][j] = s.charAt(j - 1);
            }
        }
        
        // 요청 수행
        for(String r : requests){
            if(r.length() == 1){
                removeSide(r.charAt(0));
            }
            else{
                removeAll(r.charAt(0)); 
            }
        }
        
        // 정답 출력
        int answer = 0;
        for(int i = 1; i <= H; i++){
            for(int j = 1; j <= W; j++){
                if(removed[i][j] == false){
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    private void removeSide(char ch){
        final int[] dx = { -1, 0, 1, 0 };
        final int[] dy = { 0, 1, 0, -1 };
        
        boolean[][] visited = new boolean[H + 2][W + 2];
        Queue<int[]> qu = new LinkedList<>();
        qu.offer(new int[] { 0, 0 });
        visited[0][0] = true;
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            int x = cur[1];
            int y = cur[0];
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx > W + 1 || ny < 0 || ny > H + 1){
                    continue;
                }
                if(visited[ny][nx]){
                    continue;
                }
                
                if(nx >= 1 && nx <= W && ny >= 1 && ny <= H){
                    visited[ny][nx] = true;
                    if(!removed[ny][nx]){
                        if(map[ny][nx] == ch) {
                            removed[ny][nx] = true;
                        }
                    }
                    else {
                        qu.add(new int[] { ny, nx });
                    }
                }
                else{
                    visited[ny][nx] = true;
                    qu.add(new int[] { ny, nx });
                }
            }
        }
    }
    
    private void removeAll(char ch){
        for(int i = 1; i <= H; i ++){
            for(int j = 1; j <= W; j++){
                if(map[i][j] == ch){
                    removed[i][j] = true;
                }
            }
        }   
    }
}