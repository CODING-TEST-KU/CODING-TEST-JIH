package org.example.week20;

import java.util.*;

class 프로그래머스_PCCP기출_석유시추 {
    /*
    25/12/23 20:38 ~ 
    
    뽑을 수 있는 가장 많은 석유량을 구하자
    
    각 위치마다 석유량을 구해서 최댓값을 구할 수 있음
    m * (n * m) 정도의 복잡도
    
    */
    
    private boolean[][] visited;
    
    public int solution(int[][] land) {
        
        // 석유 덩어리의 크기를 계산해서 저장
        visited = new boolean[land.length][land[0].length];
        int groupNo = 1;
        Map<Integer, Integer> groupMap = new HashMap<>();
        groupMap.put(0, 0);
        for(int r = 0; r < land.length; r++){
            for(int c = 0; c < land[0].length; c++){
                if(!visited[r][c] && land[r][c] != 0){
                    int amount = bfs(c, r, land, groupNo);
                    groupMap.put(groupNo, amount);
                    groupNo++;
                }
            }
        }
        
        // for(int i = 0; i < land.length; i++){
        //     System.out.println(Arrays.toString(land[i]));
        // }
        
        // 각 위치에서 시추했을 때 얻을 수 있는 석유량 중 최댓값 찾기
        int answer = 0;
        for(int c = 0; c < land[0].length; c++){
            Set<Integer> set = new HashSet<>();
            int sum = 0;
            boolean skip = false;
            for(int r = 0; r < land.length; r++){
                if(!set.contains(land[r][c])){
                    set.add(land[r][c]);
                    sum += groupMap.get(land[r][c]);
                }
            }
            answer = Math.max(answer, sum);
        }
        
        // 정답 출력
        return answer;
    }
    
    private int bfs(int sx, int sy, int[][] land, int groupNo){
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        int oil = 1;
        Queue<int[]> group = new LinkedList<>();
        Queue<int[]> qu = new LinkedList<>();
        visited[sy][sx] = true;
        qu.add(new int[]{ sx, sy });
        group.add(new int[] { sx, sy });
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            for(int i = 0; i < 4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx < 0 || nx >= land[0].length || ny < 0 || ny >= land.length){
                    continue;
                }
                if(visited[ny][nx] || land[ny][nx] == 0){
                    continue;
                }
                visited[ny][nx] = true;
                qu.add(new int[] { nx, ny });
                group.add(new int[] { nx, ny });
                oil++;
            }
        }
        while(!group.isEmpty()){
            int[] cell = group.poll();
            land[cell[1]][cell[0]] = groupNo;
        }
        return oil;
    }
}