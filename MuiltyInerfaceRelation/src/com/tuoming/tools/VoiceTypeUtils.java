package com.tuoming.tools;

public class VoiceTypeUtils {
    /**
     * 判断是否为需要输出的大流程
     * @return  true：是大流程  false：不是大流程
     */
    public static boolean isOutVoiceType(int type){
        if(type == 11 || type == 13 || type == 27 ){
            return false;
        }
        return true;
    }

    /**
     * 判断是否为需要关联的大流程（排除iurelease话单）
     * @return  true：是大流程  false：不是大流程
     */
    public static boolean isRelationType(int type){
        if(type == 11 || type == 13 || type == 27 || type == 28){
            return false;
        }
        return true;
    }
}
