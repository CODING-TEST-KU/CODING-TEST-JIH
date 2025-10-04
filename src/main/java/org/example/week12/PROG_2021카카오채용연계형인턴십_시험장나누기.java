package org.example.week12;

import java.util.*;

class PROG_2021카카오채용연계형인턴십_시험장나누기 {
    /*
    25/9/28 18:30 ~ 18:35
    20:55 ~ 
    
    고사장 개수를 보니 완전탐색은 불가
    느낌상 이진탐색처럼 보이긴하는 데 확신은 안듬
    
    파라매트릭 서치를 써야하나?
    트래픽이 t를 넘지 않도록 분할할때 t의 최솟값을 찾는 문제로 변환하면 어떨까
    
    이진트리에서 하나의 트래픽으로 묶는 방법은?
    부모를 포함하면 무조건 자식들도 같은 트래픽에 묶인다
    a
    | \
    b  c
    
    트래픽을 나눌때 어떤 순으로 잘라야할까?
    
    트리를 탐색하면서 합을 구하려니까 케이스를 따지기 어려움
    각 자식의 합을 미리 알 수 있다면 좋을 듯


    */
    public int solution(int k, int[] num, int[][] links) {
        int maxVal = num[0];
        for(int i = 1; i < num.length; i++){
            maxVal = Math.max(maxVal, num[i]);
        }
        int sum = 0;
        for(int i = 0; i < num.length; i++){
            sum += num[i];
        }
        // 노드 개수 = k면 노드 중 최댓값을 출력
        if(k == num.length){
            return maxVal;
        }
        // k가 1이면 모든 노드의 합을 출력
        if(k == 1){ 
            return sum;
        }
        // 그 외에는 최솟값을 찾아야 한다
        // 최대 트래픽이 t가 되도록 만들 수 있는지 확인한다
        // t의 최솟값을 파라매트릭 서치로 찾는다
        
        boolean[] isRoot = new boolean[num.length];
        Arrays.fill(isRoot, true);
        for(int i = 0; i < num.length; i++){
            if(links[i][0] != -1) isRoot[links[i][0]] = false;
            if(links[i][1] != -1) isRoot[links[i][1]] = false;
        }
        int root = -1;
        for(int i = 0; i < isRoot.length; i++){
            if(isRoot[i]){
                root = i;
            }
        }
        // System.out.println("root " + root );
        
        int lo = maxVal - 1;
        int hi = sum + 1;
        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(dividable(mid, root, k, num, links)){
                hi = mid;
            }
            else{
                lo = mid;
            }
        }
        return hi;
    }
    
    private int group = 0;
    
    private boolean dividable(int t, int root, int k, int[] num, int[][] links){
        group = 0;
        if(dfs(root, t, num, links) > 0){
            group++;
        }
        if(group <= k) {
            return true;
        }
        return false;
    }
    
    // 각 노드를 방문하면서 트래픽을 만든다
    private int dfs(int cur, int t, int[] num, int[][] links){
        // 왼쪽 서브트리에서 남은 합과 오른쪽 서브트리에서 남은 합을 구한 후에
        // 현재를 왼쪽과 합칠 수 있으면 합치고 합칠 수 없으면 왼쪽에서 자른다
        // 오른쪽과 합칠 수 있으면 합치고 합칠 수 없으면 오른쪽에서 자른다
        if(cur == -1){
            return 0;
        }
        int left = links[cur][0];
        int right = links[cur][1];
        int curSum = num[cur];
        int leftSum = dfs(left, t, num, links);
        int rightSum = dfs(right, t, num, links);
        
        if(leftSum + curSum <= t && rightSum + curSum <= t && leftSum + rightSum + curSum > t){
            if(leftSum < rightSum){
                curSum += leftSum;
                group++;
                return curSum;
            }
            curSum += rightSum;
            group++;
            return curSum;
        }
        
        if(leftSum + curSum < t){
            curSum += leftSum;
        }
        else if(leftSum + curSum == t){
            curSum = 0;
            group++;
        }
        else{
            group++;
        }
        
        if(rightSum + curSum < t){
            curSum += rightSum;
        }
        else if(rightSum + curSum == t){
            curSum = 0;
            group++;
        }
        else{
            group++;
        }

        // System.out.println("t " + t + " cur " + cur + " curSum " + curSum + " group " + group);
        return curSum;
    }
    
    class PartiallyCorrect98{
         // 각 노드를 방문하면서 트래픽을 만든다
        private int dfs(int cur, int t, int[] num, int[][] links){
            // 왼쪽 서브트리에서 남은 합과 오른쪽 서브트리에서 남은 합을 구한 후에
            // 현재를 왼쪽과 합칠 수 있으면 합치고 합칠 수 없으면 왼쪽에서 자른다
            // 오른쪽과 합칠 수 있으면 합치고 합칠 수 없으면 오른쪽에서 자른다
            if(cur == -1){
                return 0;
            }
            int left = links[cur][0];
            int right = links[cur][1];
            int curSum = num[cur];
            int leftSum = dfs(left, t, num, links);
            int rightSum = dfs(right, t, num, links);
            if(leftSum + curSum < t && rightSum + curSum < t && leftSum + rightSum + curSum > t){
                if(leftSum < rightSum){
                    curSum += leftSum;
                    group++;
                    return curSum;
                }
                curSum += rightSum;
                group++;
                return curSum;
            }
            if(leftSum + curSum < t){
                curSum += leftSum;
            }
            else if(leftSum + curSum == t){
                curSum = 0;
                group++;
            }
            else{
                group++;
            }
            if(rightSum + curSum < t){
                curSum += rightSum;
            }
            else if(rightSum + curSum == t){
                curSum = 0;
                group++;
            }
            else{
                group++;
            }

            // System.out.println("t " + t + " cur " + cur + " curSum " + curSum + " group " + group);
            return curSum;
        }
    }
    
    class Failed{
         public int solution(int k, int[] num, int[][] links) {
            int maxVal = num[0];
            for(int i = 1; i < num.length; i++){
                maxVal = Math.max(maxVal, num[i]);
            }
            int sum = 0;
            for(int i = 0; i < num.length; i++){
                sum += num[i];
            }
            // 노드 개수 = k면 노드 중 최댓값을 출력
            if(k == num.length){
                return maxVal;
            }
            // k가 1이면 모든 노드의 합을 출력
            if(k == 1){ 
                return sum;
            }
            // 그 외에는 최솟값을 찾아야 한다
            // 최대 트래픽이 t가 되도록 만들 수 있는지 확인한다
            // t의 최솟값을 파라매트릭 서치로 찾는다

            boolean[] isRoot = new boolean[num.length];
            Arrays.fill(isRoot, true);
            for(int i = 0; i < num.length; i++){
                if(links[i][0] != -1) isRoot[links[i][0]] = false;
                if(links[i][1] != -1) isRoot[links[i][1]] = false;
            }
            int root = -1;
            for(int i = 0; i < isRoot.length; i++){
                if(isRoot[i]){
                    root = i;
                }
            }
            // System.out.println("root " + root );

            subSum = new int[num.length];
            dfs1(root, num, links);

            int lo = maxVal - 1;
            int hi = sum + 1;
            while(lo + 1 < hi){
                int mid = (lo + hi) / 2;
                if(dividable(mid, root, k, num, links)){
                    hi = mid;
                }
                else{
                    lo = mid;
                }
            }
            return hi;
        }

        private int[] subSum;

        private int dfs1(int cur, int[] num, int[][] links){
            int left = links[cur][0];
            int right = links[cur][1];
            if(left == -1 && right == -1){
                return num[cur];
            }
            int sum = num[cur];
            if(left != -1) sum += dfs1(left, num, links);
            if(right != -1) sum += dfs1(right, num, links);
            return subSum[cur] = sum;
        };

        private int traffic = 0;
        private int group = 0;

        private boolean dividable(int t, int root, int k, int[] num, int[][] links){
            traffic = 0;
            group = 0;
            dfs(root, t, num, links);
            if(group > k) {
                return false;
            }
            return true;
        }

        private void dfs(int cur, int t, int[] num, int[][] links){
            int left = links[cur][0];
            int right = links[cur][1];
            if(left != -1) {
                if(traffic + subSum[left] > t){
                    dfs(left, t, num, links); 
                }
                else{
                    traffic += subSum[left];
                }
            }
            if(traffic + num[cur] > t){
                group++;
                traffic = num[cur];
            }
            else{
                traffic += num[cur];
            }
            System.out.println("t " + t + " cur " + cur + " traffic " + traffic);
            if(right != -1) {
                if(traffic + subSum[right] > t){
                    dfs(right, t, num, links); 
                }
                else{
                    traffic += subSum[right];
                    if(links[right][0] == -1 &&links[right][1] == -1){
                        group++;
                        traffic = 0;
                    }
                }

            }
        }
    }
}