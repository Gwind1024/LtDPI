package com.tuoming.tools;

import com.tuoming.entity.iups.*;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public class IuPSRelation {
    /**
     * 关联方法主体
     * @param   preIuPS :以存在话单  thisIuPS：新来的话单
     * @return  0：只移除子话单 1：头节点关联 2：中间节点关联 3：尾节点关联 4：头节点写出 5：中间节点写出
     */
    public static int relations(IUPSDecode preIuPS,IUPSDecode thisIuPS,Map<String,IUPSDecode> groupMap,Queue<IUPSDecode> resultBuffer){
//        System.out.println("关联！");
        long s1 = System.nanoTime();
        int voiceTypePre = preIuPS.getVoiceType();         //旧话单类型
        int voiceTypeThis = thisIuPS.getVoiceType();         //新话单类型

        //新旧话单都是IU Release(特殊情况的处理)
        if(voiceTypePre == 28 && VoiceTypeUtils.isOutVoiceType(voiceTypeThis)){
            //1.写出之前大流程
            resultBuffer.add(preIuPS);
            //2.从map移除
            groupMap.remove(preIuPS.getImsi());
            groupMap.put(thisIuPS.getImsi(),thisIuPS);
            //2.修改链表
            //2.1.如果旧话单是头节点
            if(preIuPS.getPreIUPSDecode() == null){
//                preIuPS.getNextIUPSDecode().setPreIUPSDecode(null);
                return 4;
            }else{
                //2.2.旧话单非头节点
                preIuPS.getPreIUPSDecode().setNextIUPSDecode(preIuPS.getNextIUPSDecode());
                preIuPS.getNextIUPSDecode().setPreIUPSDecode(preIuPS.getPreIUPSDecode());
                preIuPS.setPreIUPSDecode(null);
                preIuPS.setNextIUPSDecode(null);
//                preIuPS.clear();
//                preIuPS = null;
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
                return 5;
            }
        }

//        long s2 = System.nanoTime();
//        System.out.println("前置："+(s2-s1));
        //如果新话单是大流程
        if(VoiceTypeUtils.isRelationType(voiceTypeThis)){
            //1.写出之前大流程

            resultBuffer.add(preIuPS);
//            System.out.println("清理1："+(System.nanoTime()-s2));
            //2.从map移除
            groupMap.remove(preIuPS.getImsi());
//            System.out.println("清理2："+(System.nanoTime()-s2));
            groupMap.put(thisIuPS.getImsi(),thisIuPS);
            //2.修改链表
            //2.1.如果旧话单是头节点
//            long s3 = System.nanoTime();
//            System.out.println("清理："+(s3-s2));
            if(preIuPS.getPreIUPSDecode() == null){
//                preIuPS.getNextIUPSDecode().setPreIUPSDecode(null);
                return 4;
            }else{
                //2.2.旧话单非头节点
                preIuPS.getPreIUPSDecode().setNextIUPSDecode(preIuPS.getNextIUPSDecode());
                preIuPS.getNextIUPSDecode().setPreIUPSDecode(preIuPS.getPreIUPSDecode());
                preIuPS.setPreIUPSDecode(null);
                preIuPS.setNextIUPSDecode(null);
//                preIuPS.clear();
//                preIuPS = null;
//                System.out.println("后期："+(System.nanoTime()-s3));
//                System.out.println("=============================================");
                return 5;
            }
        }else{
            //新话单不是大流程
            //1.时间不在之前主流程内，直接舍弃
            if((preIuPS.getStarttime() > thisIuPS.getStarttime()) || (thisIuPS.getStarttime() > preIuPS.getEndtime())){
                thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
//                thisIuPS.setPreIUPSDecode(null);
                thisIuPS.clear();
                thisIuPS = null;
                return 0;
            }
            //2.时间在之前主流程内，进行关联
            switch (voiceTypePre){
                //2.1.附着话单，可关联子话单：鉴权、取标识、IU Release
                case 10 :
                    AttachDecode attachDecode = preIuPS.getAttachDecode();
                    //2.1.1.关联鉴权
                    if(voiceTypeThis == 11){
                        attachDecode.reAuthentication(thisIuPS.getOtherVoice());
                        return modifyList(preIuPS,thisIuPS);
                    }
                    //2.1.2.关联取标识
                    else if(voiceTypeThis == 13){
                        attachDecode.reIdentity(thisIuPS.getOtherVoice());
                        return modifyList(preIuPS,thisIuPS);
                    }
                    //2.1.3.关联IU Release
                    else if(voiceTypeThis == 28){
                        attachDecode.reIURelease(thisIuPS.getIuReleaseDecode());
                        return iuReleaseEnd(preIuPS,thisIuPS,groupMap,resultBuffer);
                    }
                    //2.1.4.无法关联的子话单，直接舍弃
                    else{
                        thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                        thisIuPS.setPreIUPSDecode(null);
                        thisIuPS = null;
                    }
                    break;
                //2.2.去附着话单(用户侧、网络侧)， 可关联子话单：IU Release
                case 14 :
                case 20 :
                    DetachDecode detachDecode = preIuPS.getDetachDecode();
                    //2.2.1.关联IU Release
                    if(voiceTypeThis == 28){
                        detachDecode.reIURelease(thisIuPS.getIuReleaseDecode());
                        return iuReleaseEnd(preIuPS,thisIuPS,groupMap,resultBuffer);
                    }
                    //2.2.2.无法关联的子话单，直接舍弃
                    else{
                        thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                        thisIuPS.setPreIUPSDecode(null);
                        thisIuPS = null;
                    }
                    break;
                //2.3.pdp激活话单(用户侧、用户侧二次、网络侧、网络侧二次)， 可关联子话单：RAB话单、IU Release
                case 15 :
                case 18 :
                case 21 :
                case 24 :
                    PDPActDecode pdpActDecode = preIuPS.getPdpActDecode();
                    //2.3.1.关联RAB话单
                    if(voiceTypeThis == 27){
                        pdpActDecode.relRABRelease(thisIuPS.getOtherVoice());
                        return modifyList(preIuPS,thisIuPS);
                    }
                    //2.3.2.关联IU Release
                    else if(voiceTypeThis == 28){
                        pdpActDecode.reIURelease(thisIuPS.getIuReleaseDecode());
                        return iuReleaseEnd(preIuPS,thisIuPS,groupMap,resultBuffer);
                    }
                    //2.3.3.无法关联的子话单，直接舍弃
                    else{
                        thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                        thisIuPS.setPreIUPSDecode(null);
                        thisIuPS = null;
                    }
                    break;
                //2.4.pdp去激活话单(用户侧、网络侧)， 可关联子话单：IU Release
                case 16 :
                case 22 :
                    PDPDeActDecode pdpDeActDecode = preIuPS.getPdpDeActDecode();
                    //2.4.1.关联IU Release
                    if(voiceTypeThis == 28){
                        pdpDeActDecode.reIURelease(thisIuPS.getIuReleaseDecode());
                        return iuReleaseEnd(preIuPS,thisIuPS,groupMap,resultBuffer);
                    }
                    //2.4.2.无法关联的子话单，直接舍弃
                    else{
                        thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                        thisIuPS.setPreIUPSDecode(null);
                        thisIuPS = null;
                    }
                    break;
                //2.5.pdp修改话单(用户侧、网络侧)， 可关联子话单：RAB话单、IU Release
                case 17 :
                case 23 :
                    PDPModifyDecode pdpModifyDecode = preIuPS.getPdpModifyDecode();
                    //2.5.1.关联RAB话单
                    if(voiceTypeThis == 27){
                        pdpModifyDecode.relRABRelease(thisIuPS.getOtherVoice());
                        return modifyList(preIuPS,thisIuPS);
                    }
                    //2.5.2.关联IU Release
                    else if(voiceTypeThis == 28){
                        pdpModifyDecode.reIURelease(thisIuPS.getIuReleaseDecode());
                        return iuReleaseEnd(preIuPS,thisIuPS,groupMap,resultBuffer);
                    }
                    //2.5.3.无法关联的子话单，直接舍弃
                    else{
                        thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                        thisIuPS.setPreIUPSDecode(null);
                        thisIuPS = null;
                    }
                    break;
                //2.6.寻呼和业务话单(业务话单分用户侧、网络侧)，可关联子话单：IU Release
                case 19 :
                case 25 :
                    PagingSerReqDecode pagingSerReqDecode = preIuPS.getPagingSerReqDecode();
                    //2.6.1.关联IU Release
                    if(voiceTypeThis == 28){
                        pagingSerReqDecode.reIURelease(thisIuPS.getIuReleaseDecode());
                        return iuReleaseEnd(preIuPS,thisIuPS,groupMap,resultBuffer);
                    }
                    //2.6.2.无法关联的子话单，直接舍弃
                    else{
                        thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                        thisIuPS.setPreIUPSDecode(null);
                        thisIuPS = null;
                    }
                    break;
                case 26 :
                    thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                    thisIuPS.setPreIUPSDecode(null);
                    thisIuPS = null;
                    break;
                //2.7.RANAP话单，即IU Release，不关联,直接将子话单舍弃
                case 28 :
                    thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                    thisIuPS.setPreIUPSDecode(null);
                    thisIuPS = null;
                    break;
                //2.8.路由更新话单，可关联子话单：鉴权、取标识、IU Release
                case 29 :
                    RauUpdateDecode rauUpdateDecode = preIuPS.getRauUpdateDecode();
                    //2.8.1.关联鉴权子话单
                    if(voiceTypeThis == 11){
                        rauUpdateDecode.reAuthentication(thisIuPS.getOtherVoice());
                        return modifyList(preIuPS,thisIuPS);
                    }
                    //2.8.2.关联取标识子话单
                    else if(voiceTypeThis == 13){
                        rauUpdateDecode.reIdentity(thisIuPS.getOtherVoice());
                        return modifyList(preIuPS,thisIuPS);
                    }
                    //2.8.3.关联IU Release
                    else if(voiceTypeThis == 28){
                        rauUpdateDecode.reIURelease(thisIuPS.getIuReleaseDecode());
                        return iuReleaseEnd(preIuPS,thisIuPS,groupMap,resultBuffer);
                    }
                    //2.8.4.无法关联的子话单，直接舍弃
                    else{
                        thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                        thisIuPS.setPreIUPSDecode(null);
                        thisIuPS = null;
                    }
                    break;
                //2.9.重定位话单，可关联子话单：IU Release
                case 30 :
                    RelocationDecode relocationDecode = preIuPS.getRelocationDecode();
                    //2.9.1.关联IU Release
                    if(voiceTypeThis == 28){
                        relocationDecode.reIURelease(thisIuPS.getIuReleaseDecode());
                        return iuReleaseEnd(preIuPS,thisIuPS,groupMap,resultBuffer);
                    }
                    //2.9.2.无法关联的子话单，直接舍弃
                    else{
                        thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                        thisIuPS.setPreIUPSDecode(null);
                        thisIuPS = null;
                    }
                    break;
                default:
                    System.out.println("话单类型错误！");
            }
            return 0;
        }
    }

    /**
     * 若关联上，修改链表指向及超时时间
     * @param preIuPS
     * @param thisIuPS
     * @return int 1:关联头节点 2:关联中间节点 3：关联尾节点
     */
    public static int modifyList(IUPSDecode preIuPS,IUPSDecode thisIuPS){
        preIuPS.setOvertime(thisIuPS.getOvertime());
        //1.关联的原话单是头节点，并且不是只有一个节点
        if(preIuPS.getPreIUPSDecode() == null && preIuPS.getNextIUPSDecode() != thisIuPS){
//            preIuPS.getNextIUPSDecode().setPreIUPSDecode(null);
            preIuPS.setPreIUPSDecode(thisIuPS.getPreIUPSDecode());
            thisIuPS.getPreIUPSDecode().setNextIUPSDecode(preIuPS);

            thisIuPS.clear();
            thisIuPS = null;
            return 1;
        }
        //2.关联的原话单是尾节点
        if(preIuPS.getNextIUPSDecode() == thisIuPS){
            thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
            thisIuPS.clear();
            thisIuPS = null;
            return 3;
        }
        //3.关联的原话单是中间节点
        preIuPS.getPreIUPSDecode().setNextIUPSDecode(preIuPS.getNextIUPSDecode());
        preIuPS.getNextIUPSDecode().setPreIUPSDecode(preIuPS.getPreIUPSDecode());
        preIuPS.setPreIUPSDecode(thisIuPS.getPreIUPSDecode());
        preIuPS.setNextIUPSDecode(null);
        thisIuPS.getPreIUPSDecode().setNextIUPSDecode(preIuPS);
        thisIuPS.setPreIUPSDecode(null);
        thisIuPS = null;
        return 2;
    }

    /**
     * 关联到iurelease代表主流程结束
     * 主话单正常写出
     * @param preIuPS
     * @param thisIuPS
     */
    public static int iuReleaseEnd(IUPSDecode preIuPS,IUPSDecode thisIuPS,Map<String,IUPSDecode> groupMap,Queue<IUPSDecode> resultBuffer){
        resultBuffer.add(preIuPS);
        groupMap.remove(preIuPS.getImsi());
        if(preIuPS.getPreIUPSDecode() == null && preIuPS.getNextIUPSDecode() == thisIuPS){
            return 6;
        }
        if(preIuPS.getPreIUPSDecode() == null && preIuPS.getNextIUPSDecode() != thisIuPS){
            thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
            thisIuPS.clear();
            thisIuPS = null;

            return 7;
        }
        if(preIuPS.getPreIUPSDecode() != null && preIuPS.getNextIUPSDecode() == thisIuPS){
            thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
            thisIuPS.clear();
            thisIuPS = null;
            return 8;
        }
        preIuPS.getPreIUPSDecode().setNextIUPSDecode(preIuPS.getNextIUPSDecode());
        preIuPS.getNextIUPSDecode().setPreIUPSDecode(preIuPS.getPreIUPSDecode());
        preIuPS.setPreIUPSDecode(null);
        preIuPS.setNextIUPSDecode(null);
        thisIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
        thisIuPS.clear();
        thisIuPS = null;
        return 9;
    }
}
