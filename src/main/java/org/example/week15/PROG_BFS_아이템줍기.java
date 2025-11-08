package org.example.week15;

import java.util.*;

class PROG_BFS_아이템줍기 {
    /*
    16:39 ~ 
    
    17:09 ~ 
    
    50분 남음
    
    다각형 테두리만을 지날 때 아이템까지 가는 최단거리를 구하자
    
    입력된 직사각형을 어떻게 표현할까?
    
    입력 값을 가지고 그래프를 만들기는 쉽지 않아보임
    
    주어진 직사각형 정보를 가지고 시뮬레이션 해야하나?
    
    왼쪽 아래와 오른쪽 위가 주어짐
    
    한 직사각형의 경우 격자 그래프로 나타내자! 1만 따라가면 됨
    두개의 직사격형의 경우는 사각형 내부만 거르면 됨. 
    n개의 경우에도...?
    50프로 통과
    
    일부 테스트 케이스에서 틀림
    건너서는 안되는 길을 건너고 있음.
    간격이 좁아서 떨어져 있는 걸 표현 못함
    힌트를 보니 좌표의 스케일을 키워야 함!
    */
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        int ans = 0;
        int width = 0;
        int height = 0;
        for(int[] rect : rectangle){
            width = Math.max(width, rect[2] * 2);
            height = Math.max(height, rect[3] * 2);
        }

        int[][] map = new int[height + 1][width + 1];

        for(int rect = 0; rect < rectangle.length; rect++){
            int lbx = rectangle[rect][0] * 2;
            int lby = rectangle[rect][1] * 2;
            int rtx = rectangle[rect][2] * 2;
            int rty = rectangle[rect][3] * 2;

            for(int i = lbx; i <= rtx; i++){
                map[lby][i] = map[rty][i] = 1;
            }
            for(int i = lby ; i <= rty; i++){
                map[i][lbx] = map[i][rtx] = 1;
            }   
        }

        // for(int i = 0; i < map.length; i++){
        //     System.out.println(Arrays.toString(map[i]));
        // }

        final int[] dx = {-1, 0, 1, 0};
        final int[] dy = {0, 1, 0, -1};
        int[][] visited = new int[height + 1][width + 1];

        Queue<int[]> qu = new LinkedList<>();
        qu.add(new int[]{ characterX * 2, characterY * 2, 0});
        visited[characterY * 2][characterX * 2] = 1;
        while(!qu.isEmpty()){
            int[] cur = qu.poll();
            for(int i = 0; i < 4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx < 0 || nx > width || ny < 0 || ny > height){
                    continue;
                }
                if(map[ny][nx] == 0){
                    continue;
                }
                if(inRectangle(rectangle, nx, ny)){
                    continue;
                }
                if(visited[ny][nx] > 0){
                    continue;
                }
                visited[ny][nx] = cur[2] + 2;
                qu.add(new int[]{ nx, ny, cur[2] + 1});

                if(ny == itemY * 2 && nx == itemX * 2){
                    // System.out.println("visited = ");
                    // for(int j = 0; j < visited.length; j++){
                    //     System.out.println(Arrays.toString(visited[j]));
                    // }
                    ans = (cur[2] + 1) / 2;
                }
            }
        }

        return ans;
      
    }
    
    private boolean inRectangle(int[][] rects, int x, int y){
        for(int[] rect : rects){
            if(rect[0] * 2 < x && x < rect[2] * 2  && rect[1] * 2 < y && y < rect[3] *2){
                return true;
            }
        }
        return false;
    }
}