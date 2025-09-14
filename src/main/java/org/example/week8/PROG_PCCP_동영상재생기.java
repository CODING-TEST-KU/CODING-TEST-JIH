package org.example.week8;

class PROG_PCCP_동영상재생기 {
    /*
    25/9/6 14:09 ~

    */
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) { 
        // 현재 위치가 오프닝 구간이면 끝으로 이동
        if(isBetween(pos, op_start, op_end)){
            pos = op_end;
        }
        
        // 모든 커맨드에 대해서
            // 명령어에 따라서 동영상 범위에 따라서 시간 조정
            // 현재 위치가 오프닝 구간이면 끝으로 이동
        for(String command : commands){
            if(command.equals("prev")){
                String[] tokens = pos.split(":");
                int curHour = Integer.parseInt(tokens[0]);
                int curMin = Integer.parseInt(tokens[1]);
                if(curHour == 0 && curMin <= 10){
                    pos = "00:00";
                    
                }
                else{
                    curMin -= 10;
                    if(curMin < 0){
                        curHour -= 1;
                        curMin += 60;
                    }
                    String newHour = curHour / 10 > 0 ? String.valueOf(curHour) : "0" + String.valueOf(curHour);
                    String newMin = curMin / 10 > 0 ? String.valueOf(curMin) : "0" + String.valueOf(curMin);
                    pos = newHour + ":" + newMin;
                }
            }
            else{
                String[] tokens = pos.split(":");
                int curHour = Integer.parseInt(tokens[0]);
                int curMin = Integer.parseInt(tokens[1]);
                
                tokens = video_len.split(":");
                int endHour = Integer.parseInt(tokens[0]);
                int endMin = Integer.parseInt(tokens[1]);
               
                curMin += 10;
                if(curMin >= 60){
                    curHour += 1;
                    curMin -= 60;
                }
                
                if(curHour == endHour && curMin >= endMin || curHour > endHour){
                    pos = video_len;
                }
                else{
                      String newHour = curHour / 10 > 0 ? String.valueOf(curHour) : "0" + String.valueOf(curHour);
                    String newMin = curMin / 10 > 0 ? String.valueOf(curMin) : "0" + String.valueOf(curMin);
                    pos = newHour + ":" + newMin;
                }
            }
            
            if(isBetween(pos, op_start, op_end)){
                pos = op_end;
            }
            
            System.out.println(pos);
        }
        return pos;
    }
    
    private boolean isBetween(String cur, String start, String end){
        int curHour = Integer.parseInt(cur.split(":")[0]);
        int curMin = Integer.parseInt(cur.split(":")[1]);
        int startHour = Integer.parseInt(start.split(":")[0]);
        int startMin = Integer.parseInt(start.split(":")[1]);
        int endHour = Integer.parseInt(end.split(":")[0]);
        int endMin = Integer.parseInt(end.split(":")[1]);
        if((curHour == startHour && curMin >= startMin || curHour > startHour) && (curHour == endHour && curMin <= endMin || curHour < endHour)){
            return true;
        }
        return false;
    }
}