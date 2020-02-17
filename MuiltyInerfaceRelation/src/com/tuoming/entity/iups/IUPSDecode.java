package com.tuoming.entity.iups;

import com.tuoming.tools.CommonUtils;
import com.tuoming.tools.SimpleDateDeal;
import redis.clients.jedis.Jedis;

import java.io.Serializable;

/**
 * iups口统一结构体！
 */
public class IUPSDecode implements Serializable{
    private int voiceType = -1;
    private long starttime = 0L;
    private long endtime = 0L;
    private long overtime = 0L;         //超时时间
    private String imsi = "";
    private AttachDecode attachDecode = null;
    private DetachDecode detachDecode = null;
    private PDPActDecode pdpActDecode = null;
    private PDPDeActDecode pdpDeActDecode = null;
    private PDPModifyDecode pdpModifyDecode = null;
    private RauUpdateDecode rauUpdateDecode = null;
    private IUReleaseDecode iuReleaseDecode = null;
    private RelocationDecode relocationDecode = null;
    private PagingSerReqDecode pagingSerReqDecode = null;
    private String[] otherVoice = null;
    private IUPSDecode preIUPSDecode = null;        //指向上一个
    private IUPSDecode nextIUPSDecode = null;       //指向下一个
    private int size;

    public static Jedis jedis;

    public void clear(){
        voiceType = -1;
        starttime = 0L;
        endtime = 0L;
        overtime = 0L;         //超时时间
        imsi = "";
        attachDecode = null;
        detachDecode = null;
        pdpActDecode = null;
        pdpDeActDecode = null;
        pdpModifyDecode = null;
        rauUpdateDecode = null;
        iuReleaseDecode = null;
        relocationDecode = null;
        pagingSerReqDecode = null;
        otherVoice = null;
        preIUPSDecode = null;        //指向上一个
        nextIUPSDecode = null;       //指向下一个
    }

    public IUPSDecode(){}

    //attach表关联Identity Request Authentication request方法
    public void decode(String[] xdrSplits){
        Integer type = CommonUtils.strToInteger(xdrSplits[0]);
        if(type != 0){
            voiceType = type;
            switch (type){
                case 10 :
                    //iups attach附着话单
                    if(xdrSplits.length < 27){
                        System.err.println("构建Attach话单错误！length<27！");
                        return;
                    }
                    attachDecode = new AttachDecode();
                    attachDecode.setAttachObj(xdrSplits);
                    starttime = SimpleDateDeal.String2TimeStamp(attachDecode.starttime);
                    endtime = SimpleDateDeal.String2TimeStamp(attachDecode.endtime);
                    imsi = attachDecode.imsi;
                    break;
                case 11 :
                    //iups 鉴权话单
                    if(xdrSplits.length < 27){
                        System.err.println("构建Authentication话单错误！length<27！");
                        return;
                    }
                    otherVoice = xdrSplits;
                    starttime = SimpleDateDeal.String2TimeStamp(xdrSplits[AuthIndex.StartTime]);
                    endtime = SimpleDateDeal.String2TimeStamp(xdrSplits[AuthIndex.EndTime]);
                    imsi = otherVoice[AuthIndex.IMSI];
                    break;
                case 13 :
                    //iups 取标识话单
                    if(xdrSplits.length < 25){
                        System.err.println("构建Identity话单错误！length<25！");
                        return;
                    }
                    otherVoice = xdrSplits;
                    starttime = SimpleDateDeal.String2TimeStamp(xdrSplits[IdentityIndex.StartTime]);
                    endtime = SimpleDateDeal.String2TimeStamp(xdrSplits[IdentityIndex.EndTime]);
                    imsi = otherVoice[IdentityIndex.IMSI];
                    break;
                case 14 :
                    //iups 用户侧去附着
                case 20 :
                    //iups 网络侧去附着
                    if(xdrSplits.length < 26){
                        System.err.println("构建Detach话单错误！length<26！");
                        return;
                    }
                    detachDecode = new DetachDecode();
                    detachDecode.setDetachObj(xdrSplits);
                    starttime = SimpleDateDeal.String2TimeStamp(detachDecode.starttime);
                    endtime = SimpleDateDeal.String2TimeStamp(detachDecode.endtime);
                    imsi = detachDecode.imsi;
                    break;
                case 15 :
                    //iups 用户pdp激活话单
                case 18 :
                    //iups 用户pdp二次激活
                case 21 :
                    //iups 网络侧pdp激活
                case 24 :
                    //iups 网络侧二次激活
                    if(xdrSplits.length < 35){
                        System.err.println("构建PDP Activate话单错误！length<35！");
                        return;
                    }
                    pdpActDecode = new PDPActDecode();
                    pdpActDecode.setPDPActiveObj(xdrSplits);
                    starttime = SimpleDateDeal.String2TimeStamp(pdpActDecode.starttime);
                    endtime = SimpleDateDeal.String2TimeStamp(pdpActDecode.endtime);
                    imsi = pdpActDecode.imsi;
                    break;
                case 16 :
                    //iups 用户pdp去激活话单
                case 22 :
                    //iups 网络pdp去激活话单
                    if(xdrSplits.length < 25){
                        System.err.println("构建PDP DeActivate话单错误！length<25！");
                        return;
                    }
                    pdpDeActDecode = new PDPDeActDecode();
                    pdpDeActDecode.setPDPDeActiveObj(xdrSplits);
                    starttime = SimpleDateDeal.String2TimeStamp(pdpDeActDecode.starttime);
                    endtime = SimpleDateDeal.String2TimeStamp(pdpDeActDecode.endtime);
                    imsi = pdpDeActDecode.imsi;
                    break;
                case 17 :
                    //iups 用户pdp修改话单
                case 23 :
                    //iups 网络pdp修改话单
                    if(xdrSplits.length < 34){
                        System.err.println("构建PDP Modify话单错误！length<34！");
                        return;
                    }
                    pdpModifyDecode = new PDPModifyDecode();
                    pdpModifyDecode.setPDPModifyObj(xdrSplits);
                    starttime = SimpleDateDeal.String2TimeStamp(pdpModifyDecode.starttime);
                    endtime = SimpleDateDeal.String2TimeStamp(pdpModifyDecode.endtime);
                    imsi = pdpModifyDecode.imsi;
                    break;
                case 19 :
                    //iups 用户service话单
                case 25 :
                    //iups 网络service话单
                case 26 :
                    //iups paging寻呼话单
                    if(xdrSplits.length < 32){
                        System.err.println("构建Paging Service话单错误！length<32！");
                        return;
                    }
                    pagingSerReqDecode = new PagingSerReqDecode();
                    pagingSerReqDecode.setPagingSerReqObj(xdrSplits);
                    starttime = SimpleDateDeal.String2TimeStamp(pagingSerReqDecode.starttime);
                    endtime = SimpleDateDeal.String2TimeStamp(pagingSerReqDecode.endtime);
                    imsi = pagingSerReqDecode.imsi;
                    break;
                case 27 :
                    //iups RAB话单
                    if(xdrSplits.length < 25){
                        System.err.println("构建RAB话单错误！length<25！");
                        return;
                    }
                    otherVoice = xdrSplits;
                    starttime = SimpleDateDeal.String2TimeStamp(xdrSplits[RABIndex.StartTime]);
                    endtime = SimpleDateDeal.String2TimeStamp(xdrSplits[RABIndex.EndTime]);
                    imsi = otherVoice[RABIndex.IMSI];
                    break;
                case 28 :
                    //iups ranap话单
                    if(xdrSplits.length < 29){
                        System.err.println("构建RANAP话单错误！length<29！");
                        return;
                    }
                    iuReleaseDecode = new IUReleaseDecode();
                    iuReleaseDecode.setIUReleaseObj(xdrSplits);
                    starttime = SimpleDateDeal.String2TimeStamp(iuReleaseDecode.startTime);
                    endtime = SimpleDateDeal.String2TimeStamp(iuReleaseDecode.endTime);
                    imsi = iuReleaseDecode.imsi;
                    break;
                case 29 :
                    //iups rau路由话单
                    if(xdrSplits.length < 27){
                        System.err.println("构建RAU话单错误！length<27！");
                        return;
                    }
                    rauUpdateDecode = new RauUpdateDecode();
                    rauUpdateDecode.setRauUpadteObj(xdrSplits);
                    starttime = SimpleDateDeal.String2TimeStamp(rauUpdateDecode.starttime);
                    endtime = SimpleDateDeal.String2TimeStamp(rauUpdateDecode.endtime);
                    imsi = rauUpdateDecode.imsi;
                    break;
                case 30 :
                    //iups 重定位话单
                    if(xdrSplits.length < 28){
                        System.err.println("构建Relocation话单错误！length<28！");
                        return;
                    }
                    relocationDecode = new RelocationDecode();
                    relocationDecode.setRelocationObj(xdrSplits);
                    starttime = SimpleDateDeal.String2TimeStamp(relocationDecode.starttime);
                    endtime = SimpleDateDeal.String2TimeStamp(relocationDecode.endtime);
                    imsi = relocationDecode.imsi;
                    break;
                default:
                    System.out.println("不存在该话单！");
                    return;
            }
            this.overtime = this.starttime;
        }
    }

    public int getVoiceType() {
        return voiceType;
    }

    public long getStarttime() {
        return starttime;
    }

    public long getEndtime(){
        return endtime;
    }

    public String getImsi(){
        return imsi;
    }

    public long getOvertime() {
        return overtime;
    }

    public AttachDecode getAttachDecode() {
        return attachDecode;
    }

    public DetachDecode getDetachDecode() {
        return detachDecode;
    }

    public PDPActDecode getPdpActDecode() {
        return pdpActDecode;
    }

    public PDPDeActDecode getPdpDeActDecode() {
        return pdpDeActDecode;
    }

    public PDPModifyDecode getPdpModifyDecode() {
        return pdpModifyDecode;
    }

    public RauUpdateDecode getRauUpdateDecode() {
        return rauUpdateDecode;
    }

    public IUReleaseDecode getIuReleaseDecode() {
        return iuReleaseDecode;
    }

    public RelocationDecode getRelocationDecode() {
        return relocationDecode;
    }

    public PagingSerReqDecode getPagingSerReqDecode() {
        return pagingSerReqDecode;
    }

    public String[] getOtherVoice() {
        return otherVoice;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    public void setOvertime(long overtime) {
        this.overtime = overtime;
    }

    public void setPreIUPSDecode(IUPSDecode preIUPSDecode) {
        this.preIUPSDecode = preIUPSDecode;
    }

    public void setNextIUPSDecode(IUPSDecode nextIUPSDecode) {
        this.nextIUPSDecode = nextIUPSDecode;
    }

    public IUPSDecode getPreIUPSDecode() {
        return preIUPSDecode;
    }

    public IUPSDecode getNextIUPSDecode() {
        return nextIUPSDecode;
    }


    public String toString(){
        switch (this.voiceType){
            case 10 : return attachDecode.endtime + "|" + attachDecode.toString();
            case 14 :
            case 20 : return detachDecode.endtime + "|" + detachDecode.toString();
            case 15 :
            case 18 :
            case 21 :
            case 24 : return pdpActDecode.endtime + "|" + pdpActDecode.toString();
            case 16 :
            case 22 : return pdpDeActDecode.endtime + "|" + pdpDeActDecode.toString();
            case 17 :
            case 23 : return pdpModifyDecode.endtime + "|" + pdpModifyDecode.toString();
            case 19 :
            case 25 :
            case 26 : return pagingSerReqDecode.endtime + "|" + pagingSerReqDecode.toString();
            case 28 : return iuReleaseDecode.getEndTime() + "|" + iuReleaseDecode.toString();
            case 29 : return rauUpdateDecode.endtime + "|" + rauUpdateDecode.toString();
            case 30 : return relocationDecode.endtime + "|" + relocationDecode.toString();
            default: return "";
        }
    }
}
