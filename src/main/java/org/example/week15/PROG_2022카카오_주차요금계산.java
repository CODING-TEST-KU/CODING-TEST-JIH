package org.example.week15;

import java.util.*;

class PROG_2022카카오_주차요금계산 {
    /*
    09:50 ~ 10:57 부분 성공
    
    오버플로우에 주의해야하나?
    
    나오지 못한 차는 23:59에 출차하는 것을 가정
    
    24 * 60 - 1 = 1439
    */
    public int[] solution(int[] fees, String[] records) {
        // 기록을 탐색
            // IN이면 
                // Map arriveTime에 저장
                // List remains에 저장
            // OUT이면 
                // arriveTime으로 누적 시간을 계산해서 Map accumTime에 저장
        // remain에 남은 차량을 23:59분에 출차 처리하여 accumTime에 저장
        
        // 각 누적 시간에 대해서
            // 총 요금 = 기본 요금 + ceil(초과시간) * 단위요금을 result에 저장
        
        // result를 배열로 변환
        List<String> cars = new ArrayList<>();
        Map<String, Integer> accumTimes = new HashMap<>();
        for(String record : records){
            String[] token = record.split(" ");
            String car = token[1];
            if(!cars.contains(car)) {
                cars.add(car);
                accumTimes.put(car, 0);
            }
        }
        cars.sort((a, b) -> a.compareTo(b));
        
        // System.out.println(Arrays.toString(cars.toArray(new String[0])));
            
        Map<String, Integer> arriveTimes = new HashMap<>();
        for(String record : records){
            String[] token = record.split(" ");
            int time = timeToMin(token[0]);
            String car = token[1];
            String event = token[2];
            
            if(event.equals("IN")){
                arriveTimes.put(car, time);
                continue;
            }
            
            Integer arrival = arriveTimes.get(car);
            arriveTimes.remove(car);
            
            int stayTime = time - arrival;
            
            System.out.println("car=" + car + " stayTime = " + stayTime);
            
            accumTimes.put(car, accumTimes.get(car) + stayTime);
        }
        for(String car : arriveTimes.keySet()){
            int arrival = arriveTimes.get(car);
            int left = timeToMin("23:59");
            // System.out.println("left = " + left);
            int stayTime = left - arrival;
            accumTimes.put(car, accumTimes.get(car) + stayTime);
        }
        
        int[] answer = new int[cars.size()];
        for(int i = 0; i < cars.size(); i++){
            String car = cars.get(i);
            int accum = accumTimes.get(car);
            int payment = calcPayment(accum, fees);
            
            System.out.println("car=" + car + ", accum=" + accum + ", payment=" + payment);
            answer[i] = payment;
        }
        
        return answer;
    }
    
    private int timeToMin(String time){
        String[] tokens = time.split(":");
        int hour = Integer.parseInt(tokens[0]);
        int min = Integer.parseInt(tokens[1]);
        return hour * 60 + min;
    }
    
    private int calcPayment(int accumTime, int[] fees){
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        
        if(accumTime <= baseTime){
            return baseFee;
        }
        double addTime = Math.ceil((double)(accumTime - baseTime) / unitTime);
        System.out.println("addTime=" + addTime);
        return baseFee + (int) addTime * unitFee;
    }
}