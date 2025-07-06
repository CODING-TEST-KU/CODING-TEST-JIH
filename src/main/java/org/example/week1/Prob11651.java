package org.example.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob11651 {
    /*
    https://www.acmicpc.net/problem/11651
    
    25/07/02 12:24 ~ 12:38

    공부해볼 다른 풀이 : https://www.acmicpc.net/source/95865446
    - Comparator 사용법
    - Integer.compare()
    - List의 sort()

    공부할 내용 :
    - 왜 static class이어야 하나?
    - Comparable의 사용 방법 다시 정리
     */

    public static void main(String[] args) throws IOException{
        solution4();
    }

    private static void solution1() throws IOException {
        /*
        Comparable과 배열 이용
         */
        int N;
        Point[] points;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        String line = br.readLine();
        N = Integer.parseInt(line);
        points = new Point[N];
        for(int i = 0; i < N; i++){
            line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            points[i] = new Point();
            points[i].x = Integer.parseInt(st.nextToken());
            points[i].y = Integer.parseInt(st.nextToken());
        }

        // 배열을 정렬
        Arrays.sort(points);

        // 정렬된 배열을 출력
        for(int i = 0; i < N; i++){
            System.out.println(points[i].x + " " + points[i].y);
        }
    }

    private static void solution2() throws IOException{
        /*
        Comparator와 List 이용
         */
        int N;
        List<Point> points;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        String line = br.readLine();
        N = Integer.parseInt(line);
        points = new ArrayList<>();
        for(int i = 0; i < N; i++){
            line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            Point point = new Point();
            point.x = Integer.parseInt(st.nextToken());
            point.y = Integer.parseInt(st.nextToken());
            points.add(point);
        }

        // 배열을 정렬
        points.sort(new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2){
                if(p1.y == p2.y){
                    return Integer.compare(p1.x, p2.x);
                }
                return Integer.compare(p1.y, p2.y);
            }
        });

        // 정렬된 배열을 출력
        for(int i = 0; i < N; i++){
            System.out.println(points.get(i).x + " " + points.get(i).y);
        }
    }

    private static void solution3() throws IOException{
        /*
        List와 람다식
         */
        int N;
        List<Point> points;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        String line = br.readLine();
        N = Integer.parseInt(line);
        points = new ArrayList<>();
        for(int i = 0; i < N; i++){
            line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            Point point = new Point();
            point.x = Integer.parseInt(st.nextToken());
            point.y = Integer.parseInt(st.nextToken());
            points.add(point);
        }

        // 배열을 정렬
        points.sort((Point p1, Point p2) -> {
            if(p1.y == p2.y){
                return p1.x - p2.x;
            }
            return p1.y - p2.y;
        });

        // 정렬된 배열을 출력
        for(int i = 0; i < N; i++){
            System.out.println(points.get(i).x + " " + points.get(i).y);
        }
    }

    private static void solution4() throws IOException{
        /*
        List와 함수형 프로그래밍
         */
        int N;
        List<Point> points;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        String line = br.readLine();
        N = Integer.parseInt(line);
        points = new ArrayList<>();
        for(int i = 0; i < N; i++){
            line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            Point point = new Point();
            point.x = Integer.parseInt(st.nextToken());
            point.y = Integer.parseInt(st.nextToken());
            points.add(point);
        }

        // 배열을 정렬
        points.sort(Comparator.comparing(Point::getY).thenComparing(Point::getX)); // 제일 편한 방식

        // 정렬된 배열을 출력
        for(int i = 0; i < N; i++){
            System.out.println(points.get(i).x + " " + points.get(i).y);
        }
    }

    static class Point implements Comparable<Point> { // static 메서드 안에서 내부 클래스를 사용하려면, 그 내부 클래스 역시 static이어야 한다.
        int x;
        int y;

        public int getX(){
            return this.x;
        }

        public int getY(){
            return this.y;
        }

        @Override
        public int compareTo(Point o) { // compareTo를 오버라이드해야한다. 현재 객체가 작으면 음수, 현재 객체와 같으면 0, 그렇지 않으면 양수를 반환한다.
            int diff = this.y - o.y;
            if(diff == 0){
                return this.x - o.x;
            }
            return diff;
        }
    }
}