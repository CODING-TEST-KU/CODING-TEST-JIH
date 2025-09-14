package org.example.week8;

/*
25/9/6 14:00 ~ 
*/
class PROG_PCCE_이웃한칸 {
    public int solution(String[][] board, int h, int w) {
        String color = board[h][w];
        int[] dh = { -1, 1, 0, 0 };
        int[] dw = { 0, 0, -1, 1 };
        int count = 0;
        for(int direct = 0; direct < 4; direct++){
            int nh = h + dh[direct];
            int nw = w + dw[direct];
            if(nh < 0 || nh >= board.length || nw < 0 || nw >= board.length){
                continue;
            }
            if(color.equals(board[nh][nw])){
                count++;
            }
        }
        return count;
    }
}