package com.tuoming.tools;

import com.tuoming.entity.iups.IUPSDecode;

import java.util.List;
import java.util.Map;
import java.util.Queue;


public class TimeManager {

    private long timeGrain = 60000;      //队列粒度 单位：ms

    public TimeManager(){
    }

    /**
     * 循环 判断超时
     * @param headIuPS 第一个话单
     * @param nowTime 当前话单时间
     */
    public IUPSDecode OvertimeJugle(IUPSDecode headIuPS, long nowTime, Map<String,IUPSDecode> groupMap, Queue<IUPSDecode> resultBuffer){
        //超时就一直循环，直到遇到第一条不超时停止
        while(nowTime - headIuPS.getOvertime() > timeGrain){
            //1.写出到缓冲区
            resultBuffer.add(headIuPS);
            //2.置空
            groupMap.remove(headIuPS.getImsi());

            headIuPS = headIuPS.getNextIUPSDecode();
            if(headIuPS == null){
                return null;
            }
            headIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
            headIuPS.setPreIUPSDecode(null);
        }
        return headIuPS;
    }
}
