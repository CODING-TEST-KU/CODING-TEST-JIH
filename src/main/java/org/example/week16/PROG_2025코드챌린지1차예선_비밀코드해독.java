package org.example.week16;

class PROG_2025코드챌린지1차예선_비밀코드해독 {
    /*
    11:06 ~ 11:25
    
    비밀코드로 가능한 조합의 수를 구하자
    
    n <= 30이고 길이가 5이니 브루트포스가 가능할 듯
    */
    private int answer = 0;
    private int[] seq;
    
    public int solution(int n, int[][] q, int[] ans) {
        
        // 각 조합에 대해서 
            // q의 원소와 일치하는 수를 계산
            // ans의 원소와 일치하는지 확인
            // 모두 일치하면 answer를 증가
        seq = new int[5];
        findPossibles(0, 1, n, q, ans);
        return answer;
    }
    

    
    private void findPossibles(int depth, int start, int n, int[][] querys, int[] ans){
        if(depth == 5){
            int[] matched = new int[querys.length];
            for(int i = 0; i < querys.length; i++){
                for(int s : seq){
                    for(int j = 0; j < 5; j++){
                        if(querys[i][j] == s){
                            matched[i]++;
                        }
                    }
                }
            }
            
            boolean isPossible = true;
            for(int i = 0; i < ans.length; i++){
                if(ans[i] != matched[i]){
                    isPossible = false;
                }
            }
            if(isPossible){
                answer++;
            }
            return;    
        }
        for(int i = start; i <= n; i++){
            seq[depth] = i;
            findPossibles(depth + 1, i + 1, n, querys, ans);   
        }
    }
    
    
}