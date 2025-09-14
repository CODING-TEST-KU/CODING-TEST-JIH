package org.example.week8;

class PROG_연습문제_바탕화면정리 {
    public int[] solution(String[] wallpaper) {
        int leftMost = 100;
        int rightMost = -1;
        int upMost = 100;
        int downMost = -1;
        
        // 가장 좌우상하에 위치한 파일의 위치를 각각 구한다
        for(int i = 0; i < wallpaper.length; i++){
            for(int j = 0; j < wallpaper[i].length(); j++){
                if(wallpaper[i].charAt(j) == '#'){
                    leftMost = Math.min(leftMost, j);
                    rightMost = Math.max(rightMost, j);
                    upMost = Math.min(upMost, i);
                    downMost = Math.max(downMost, i);
                    System.out.println("lux " + leftMost + " luy " + upMost + " rdx " + rightMost + " rdy " + downMost);
                }
            }
        }
        
        // 4개의 위치를 토대로 드래그 영역을 구한다
        int[] answer = { upMost, leftMost, downMost + 1, rightMost + 1 };
        return answer;
    }
}