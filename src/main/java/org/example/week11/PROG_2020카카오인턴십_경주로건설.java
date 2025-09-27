package org.example.week11;

import java.util.*;

class PROG_2020카카오인턴십_경주로건설 {
    /*
    
    25/9/26
    
    21:22 ~ 21:42 
    21:48 ~ 22:30
    
    25/9/27
    16:25 ~ 
    
    내가 처음에는 dfs를 이용하여서 문제를 해결하려고 했다.
    근데 88점을 넘지를 못한다
    dfs는 최단경로를 찾는데 유용하지 않다는 것이 판명된 듯하다
    
    경주로는 코너를 만드느냐 만들지 않느냐에 따라 비용이 달라지므로 
    현재 cost만 가지고 가지치기 하는 것은 적절하지 않은 듯 하다
    
    그렇다면 어떻게 해야할까?
    
    힌트를 참고하니 BFS는 안된다고 한다. 
    하긴 엣지의 가중치가 다른 상황에서 BFS는 최단 경로를 보장하지 못할 것이다.
    
    그렇다면 엣지의 가중치가 다른 상황에서 최단 경로를 구하려면 어떻게 해야할까?
    힌트를 보니 다익스트라를 떠올려야 한다!
    
    다익스트라를 적용하려면 주어진 맵을 가중치 그래프로 변환해야하나?
    그래프를 만들 필요가 없다!
    
    이미 주어진 정보가 그래프를 구성하기에 충분하다. 그런 느낌이긴 했음
    근데 문제는 그래프를 정의하는 것이다.
    그래프의 노드는 좌표가 아니라 좌표 + (들어온 방향)이다.
    들어온 방향에 따라서 두 칸 사이의 거리가 달라지므로 노드를 이렇게 정의해야 한다.
    
    와 어렵다ㅏㅏㅏ
    
    */
    private int answer = 25 * 25 * 1000 + 1;
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1, 0, -1};
    private int[][][] dist;
    private int INF = 25 * 25 * 1000 + 1;
    
    public int solution(int[][] board) {        
        dijkstra(board);
        return answer;
    }
    
    private void dijkstra(int[][] board){
        dist = new int[board.length][board.length][4];
        for(int i = 0; i < 4; i++){
            dist[0][0][i] = 0;
        }
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board.length; j++)
                Arrays.fill(dist[i][j], INF);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b)-> a.dist - b.dist);
        if(board[1][0] == 0) pq.offer(new Node(0, 1, 1, 100));
        if(board[0][1] == 0) pq.offer(new Node(1, 0, 2, 100));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(dist[cur.y][cur.x][cur.direct] < cur.dist){
                continue;
            }
            for(int i = 0; i < 4; i++){
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i]; 
                int nextDist = (cur.direct == i) ? cur.dist + 100 : cur.dist + 600;
                if(ny < 0 || ny >= board.length || nx < 0 || nx >= board.length) {
                    continue;
                }
                if(board[ny][nx] == 1){
                    continue;
                }
                if(dist[ny][nx][i] > nextDist){
                    dist[ny][nx][i] = nextDist;
                    pq.offer(new Node(nx, ny, i, nextDist));
                }
            }
        }
        for(int i = 0; i < 4; i++){
            answer = Math.min(answer, dist[board.length - 1][board.length - 1][i]);
        }
    }
    
    
    static class Node{
        int x;
        int y;
        int direct;
        int dist;
        public Node(int x, int y, int direct, int dist){
            this.x = x;
            this.y = y;
            this.direct = direct;
            this.dist = dist;
        }
    }
    
    
    static class Failed2{
        private boolean [][] visited;
        private int[] dx = {-1, 0, 1, 0};
        private int[] dy = {0, 1, 0, -1};
        private List<Node>[] graph;
        
        private void makeGraph(int [][] board){
            // bfs로 맵을 탐색해서 그래프를 만든다
            // 각 칸의 노드 번호는 위에서 오른쪽으로 이동할 수록 증가
            // 각 칸의 노드 번호는 (행 * n + 열)이다!
            graph = new ArrayList[board.length * board.length + 1];
            for(int i = 1; i <= board.length * board.length; i++){
                graph[i] = new ArrayList<>();
            }

            visited = new boolean[board.length][board.length];
            Queue<Cell> qu = new LinkedList<>();
            qu.offer(new Cell(0, 0, 0, 0));
            visited[0][0] = true;

            while(!qu.isEmpty()){
                Cell poll = qu.poll();
                for(int i = 0; i < 4; i++){
                    int ny = poll.y + dy[i];
                    int nx = poll.x + dx[i];
                    int dist = 0;
                    if(poll.x == 0 && poll.y == 0 || dx[i] == poll.dx && dy[i] == poll.dy){
                        dist = 100;
                    }
                    else{
                        dist = 600;
                    }

                    if(nx < 0 || nx >= board.length || ny < 0 || ny >= board.length){
                        continue;
                    }

                    int s = poll.y * board.length + poll.x + 1;
                    int e = ny * board.length + nx + 1;

                    // System.out.println("s e " + s + " " + e);

                    graph[s].add(new Node(e, dist));

                    if(visited[ny][nx]){
                        continue;
                    }

                    visited[ny][nx] = true;

                    qu.offer(new Cell(nx, ny, dx[i], dy[i]));
                }
            }
        
        }
        
        static class Node{
                int next;
                int dist;
                public Node(int next, int dist){
                    this.next = next;
                    this.dist = dist;
                }
        }

        static class Cell{
            int x;
            int y;
            int dx;
            int dy;
            public Cell(int x, int y, int dx, int dy){
                this.x = x;
                this.y = y;
            }
        }
    }
    static class Failed1{
        private int answer = 25 * 25 * 1000 + 1;
        private int [][] visited;
        private int[] dx = {-1, 0, 1, 0};
        private int[] dy = {0, 1, 0, -1};
        
        public int solution(int[][] board) {
            visited = new int[board.length][board.length];
            for(int i = 0; i < board.length; i++)
                Arrays.fill(visited[i], 25 * 25 * 1000 + 1);
            dfs(0, 0, 0, 0, board, board.length, 0);
            return answer;
        }
    
        private void dfs(int x, int y, int prevDx, int prevDy, int[][] board, int n, int cost){
            if(x < 0 || x >= n || y < 0 || y >= n){
                return;
            }
            if(visited[y][x] < cost){
                return;
            }
            if(board[y][x] == 1){
                return;
            }
            // System.out.println(x + " " + y + " " + cost);
            if(x == n - 1 && y == n - 1){

                // for(int i = 0; i < n; i++){
                //     for(int j = 0; j < n; j++){
                //         System.out.print(visited[i][j] + " ");
                //     }
                //     System.out.println();
                // }
                System.out.println("update " + cost);

                answer = Math.min(answer, cost);
                return;
            }
            visited[y][x] = cost;
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nc = 0;
                if(x == 0 && y == 0 || dx[i] == prevDx && dy[i] == prevDy){
                    nc = cost + 100;
                }
                else{
                    nc = cost + 600;
                }
                dfs(nx, ny, dx[i], dy[i], board, n, nc);
            }
        }
    }
    
}