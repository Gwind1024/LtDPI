package com.tuoming.entity.iups;

import com.tuoming.tools.CommonUtils;
import com.tuoming.tools.RadixDeal;
import com.tuoming.tools.RedisUntil;
import com.tuoming.tools.SimpleDateDeal;

import java.io.Serializable;

/**
 * IuPS口公共接口
 * 注意：部分方法要根据自己逻辑进行重写！
 */
public abstract class CommonIuPS implements Serializable{

    public String voiceType = "";     //识别话单种类字段

    public String starttime = "";	    //TDR合成开始时间
    public String endtime = "";		//TDR合成结束时间
    public String eventType = "";		//事件类型
    public String ranType = "";		//接入网类型
    public String combFlag = "";		//合成标志
    public String phone = "";		//用户号码
    public String imsi = "";		//IMSI
    public String imei = "";		//IMEI
    public String userIP = "";		//用户IP
    public String hCountry = "";		//用户归属国家
    public String hProvince = "";		//用户归属的省份代码
    public String hCity = "";		//用户归属地市编码
    public String APN = "";		//接入点名称
    public String SGSN = "";		//SGSN
    public String GGSN = "";		//GGSN
    public String RNC = "";		//RNC
    public String RAC = "";		//RAC
    public String cell = "";		//CELL
    public String hOperator = "";		//用户归属运营商
    public String visitProv = "";		//拜访省
    public String visitCity = "";		//拜访市
    public String roamType = "0";		//用户漫游
    public String result = "";		    //主流程结果
    public String errorCode = "";		//失败原因
    public String releaserCode = "";		//释放阶段的原因编码
    public String failureMsg = "";		//失败消息代码
    public String failurepreMsg = "";		//失败前正常消息代码
    public String NTId = "";		    //NTID
    public String subevtType = "";	//事件子类型
    public String TMSI = "";		    //临时识别码
    public String TLLI = "";		    //TLLI
    public String delay_Idreq = "";	//IU口取标识请求时延
    public String delay_Idres = "";	//IU口取标识应答时延
    public int tryIds = 0;		    //取标识重发次数
    public String delay_Aucreq = "";	//鉴权请求时延
    public String delay_Aucres = "";	//鉴权请求成功时延
    public int tryAuths = 0;		//鉴权重发次数
    public int authTimes = 0;		//鉴权次数
    public String delay_Secreq = "";	//安全模式控制请求时延
    public String delay_Secres = "";	//安全模式控制成功时延
    public int trySecs = 0;		//安全模式重发次数
    public String delay_Relreq = "";		//Iu释放请求消息时延
    public String delay_Relcmd = "";		//Iu释放完成时延
    public String delay_Relcmp = "";		//Iu Release Complete消息同开始时间的差值
    public String result_Iurel = "";		//lu释放结果
    public String cause_Iurel = "";		//lu释放原因

    public void clear(){
        voiceType = "";
        starttime = "";
        endtime = "";
        eventType = "";
        ranType = "";
        combFlag = "";
        phone = "";
        imsi = "";
        imei = "";
        userIP = "";
        hCountry = "";
        hProvince = "";
        hCity = "";
        APN = "";
        SGSN = "";
        GGSN = "";
        RNC = "";
        RAC = "";
        cell = "";
        hOperator = "";
        visitProv = "";
        visitCity = "";
        roamType = "0";
        result = "";
        errorCode = "";
        releaserCode = "";
        failureMsg = "";
        failurepreMsg = "";
        NTId = "";
        subevtType = "";
        TMSI = "";
        TLLI = "";
        delay_Idreq = "";
        delay_Idres = "";
        tryIds = 0;
        delay_Aucreq = "";
        delay_Aucres = "";
        tryAuths = 0;
        authTimes = 0;
        delay_Secreq = "";
        delay_Secres = "";
        trySecs = 0;
        delay_Relreq = "";
        delay_Relcmd = "";
        delay_Relcmp = "";
        result_Iurel = "";
        cause_Iurel = "";
    }

    public void setVoiceType(String[] xdrSplits, int index){
        this.voiceType = xdrSplits[index];
    }

    public void setStarttime(String[] xdrSplits, int index) {
        this.starttime = xdrSplits[index];
    }

    public void setEndtime(String[] xdrSplits, int index) {
        this.endtime = xdrSplits[index];
    }
    //todo 与关联有关系 重载一个
    public void setEndtime(IUReleaseDecode iuReleaseDecode) {
        if("1".equals(iuReleaseDecode.getIUCode())){
            this.endtime = iuReleaseDecode.getEndTime();
        }
    }

    public void setEventType() {
        this.eventType = "";
    }

    public void setRanType(String[] xdrSplits, int index) {
        if("3".equals(xdrSplits[index])){
            this.ranType = "2";
        }else if("4".equals(xdrSplits[index])){
            this.ranType = "1";
        }
    }

    public void setCombFlag(String[] xdrSplits, int index) {
        if("2".equals(xdrSplits[index])){
            this.combFlag = "1";
        }else if("0".equals(xdrSplits[index]) || "1".equals(xdrSplits[index])){
            this.combFlag = "0";
        }
    }

    public void setPhone(String[] xdrSplits, int index) {
        this.phone = xdrSplits[index];
    }

    public void setImsi(String[] xdrSplits, int index) {
        this.imsi = xdrSplits[index];
    }

    public void setImei(String[] xdrSplits, int index) {
        this.imei = xdrSplits[index];
    }

    public void setUserIP(String[] xdrSplits, int index) {
        this.userIP = xdrSplits[index];
    }

    public void sethCountry(String[] xdrSplits, int index) {
        this.hCountry = xdrSplits[index];
    }

    public void sethProvince(String[] xdrSplits, int index) {
        this.hProvince = xdrSplits[index];
    }

    public void sethCity(String[] xdrSplits, int index) {
        this.hCity = xdrSplits[index];
    }

    public void setAPN(String[] xdrSplits, int index) {
        this.APN = xdrSplits[index];
    }

    public void setSGSN(String[] xdrSplits, int index) {
        String xdrSplit = xdrSplits[index];
        if(xdrSplit.contains(".")){
            this.SGSN = RadixDeal.IP2Decimal(xdrSplit);
        }else{
            this.SGSN = xdrSplit;
        }
    }

    public void setGGSN(String[] xdrSplits, int index) {
        String xdrSplit = xdrSplits[index];
        if(xdrSplit.contains(".")){
            this.GGSN = RadixDeal.IP2Decimal2(xdrSplit);
        }else{
            this.GGSN = xdrSplit;
        }
    }

    public void setRNC(String[] xdrSplits,int index) {
        String xdrSplit = xdrSplits[index];
        if(xdrSplit.contains(".")){
            this.RNC = RadixDeal.IP2Decimal(xdrSplit);
        }else{
            this.RNC = xdrSplit;
        }
    }

    public void setRAC(String[] xdrSplits,int MCC_index,int MNC_index,int LAC_index,int RAC_index) {

        if("65535".equals(xdrSplits[MCC_index]) || "".equals(xdrSplits[MCC_index])){
            this.RAC = "460";
        }else{
            this.RAC = xdrSplits[MCC_index];
        }
        if(xdrSplits[MNC_index].length() == 1){
            this.RAC = this.RAC+"0"+xdrSplits[MNC_index];
        }else if("255".equals(xdrSplits[MNC_index]) || "".equals(xdrSplits[MNC_index])){
            this.RAC = this.RAC+"01";
        }
        this.RAC = this.RAC+xdrSplits[LAC_index]+xdrSplits[RAC_index];
    }

    public void setCell(String[] xdrSplits, int MCC_index,int MNC_index,int LAC_index,int CELL_index) {
        if("65535".equals(xdrSplits[MCC_index]) || "".equals(xdrSplits[MCC_index])){
            this.cell = "460";
        }else{
            this.cell = xdrSplits[MCC_index];
        }
        if(xdrSplits[MNC_index].length() == 1){
            this.cell = this.cell+"0"+xdrSplits[MNC_index];
        }else if("255".equals(xdrSplits[MNC_index]) || "".equals(xdrSplits[MNC_index])){
            this.cell = this.cell+"01";
        }
        this.cell = this.cell+xdrSplits[LAC_index]+xdrSplits[CELL_index];
    }

    public void sethOperator() {
        this.hOperator = "中国联通";
    }

    public void setVisitProv(String[] xdrSplits, int index) {
        this.visitProv = xdrSplits[index];
    }

    public void setVisitCity(String[] xdrSplits, int index) {
        this.visitCity = xdrSplits[index];
    }

    public void setRoamType() {
        this.roamType = "0";
    }

    public void setResult(String[] xdrSplits, int R1_index,int R2_index) {
        if("0".equals(xdrSplits[R1_index])){
            this.result = "0";
        }else if("2".equals(xdrSplits[R1_index])){
            this.result = "3";
        }else if("1".equals(xdrSplits[R1_index])){
            Integer flag = CommonUtils.strToInteger(xdrSplits[R2_index]);
            if(flag == null){
                this.result = "";
            }else if(flag>=2&&flag<=9){
                this.result = "1";
            }else{
                this.result = "2";
            }
        }
    }

    public void setErrorCode(String[] xdrSplits, int R1_index,int R2_index) {
        if("1".equals(xdrSplits[R1_index]) || "2".equals(xdrSplits[R1_index])){
            this.errorCode = xdrSplits[R2_index];
        }
    }

    public void setReleaserCode(String[] xdrSplits, int index) {
        this.releaserCode = xdrSplits[index];
    }

    /*public void setReleaserCode(String[] xdrSplits, int Flag_index,int Use_index) {
        if("1".equals(xdrSplits[Flag_index])){
            this.releaserCode = xdrSplits[Use_index];
        }
    }*/

    public void setReleaserCode(IUReleaseDecode iuReleaseDecode) {
        if("1".equals(iuReleaseDecode.getIUCode())){
            this.releaserCode = iuReleaseDecode.getRANCause();
        }
    }


    public void setFailureMsg(String[] xdrSplits,  int R1_index,int R2_index) {
        if("1".equals(xdrSplits[R1_index])){
            this.failureMsg = xdrSplits[R2_index];
        }
    }

    public void setFailurepreMsg(String[] xdrSplits,int index) {
        if("1".equals(xdrSplits[index]) || "2".equals(xdrSplits[index])){
            this.failurepreMsg = "1";
        }
    }

    public void setNTId(String[] xdrSplits, int index) {
        this.NTId = xdrSplits[index];
    }
    /*
     * 该方法需要各自重写，数值不同
     */
    public void setSubevtType(String[] xdrSplits, int index) {
        this.subevtType = xdrSplits[index];
    }

    public void setTMSI(String[] xdrSplits, int index) {
        String xdrSplit = xdrSplits[index];
        if("0".equals(xdrSplit)){
            this.TMSI = "";
        }else{
            this.TMSI = xdrSplit;
        }
    }

    public void setTLLI(String[] xdrSplits, int index) {
        this.TLLI = xdrSplits[index];
    }
    //todo 与关联有关
    public void setDelay_Idreq(String[] xdrSplits, int index) {
        if(this.tryIds == 0){
            String identityStart = xdrSplits[index];
//            identityStart = identityStart.substring(0,identityStart.indexOf(".")+4);
            String attachStart = this.starttime;
//            attachStart = attachStart.substring(0,attachStart.indexOf(".")+4);
            this.delay_Idreq = (SimpleDateDeal.String2TimeStamp(identityStart)-SimpleDateDeal.String2TimeStamp(attachStart))+"";
        }
    }
    //todo 与关联有关
    public void setDelay_Idres(String[] xdrSplits, int index) {
        if(this.tryIds == 0){
            String identityEnd = xdrSplits[index];
//            identityEnd = identityEnd.substring(0,identityEnd.indexOf(".")+4);
            String attachStart = this.starttime;
//            attachStart = attachStart.substring(0,attachStart.indexOf(".")+4);
            this.delay_Idres = (SimpleDateDeal.String2TimeStamp(identityEnd)-SimpleDateDeal.String2TimeStamp(attachStart))+"";
        }
    }
    //todo 与关联有关
    public void setTryIds() {
        this.tryIds++;
    }
    //todo 与关联有关
    public void setDelay_Aucreq(String[] xdrSplits, int index) {
        if(this.tryAuths == 0 && this.authTimes == 0){
            String authStart = xdrSplits[index];
//            authStart = authStart.substring(0,authStart.indexOf(".")+4);
            String attachStart = this.starttime;
//            attachStart = attachStart.substring(0,attachStart.indexOf(".")+4);
            this.delay_Aucreq = (SimpleDateDeal.String2TimeStamp(authStart)-SimpleDateDeal.String2TimeStamp(attachStart))+"";
        }
    }
    //todo 与关联有关
    public void setDelay_Aucres(String[] xdrSplits, int index) {
        if(this.tryAuths == 0 && this.authTimes == 0){
            String authEnd = xdrSplits[index];
//            authEnd = authEnd.substring(0,authEnd.indexOf(".")+4);
            String attachStart = this.starttime;
//            attachStart = attachStart.substring(0,attachStart.indexOf(".")+4);
            this.delay_Aucres = (SimpleDateDeal.String2TimeStamp(authEnd)-SimpleDateDeal.String2TimeStamp(attachStart))+"";
        }
    }
    //todo 与关联有关
    public void setTryAuths(String[] xdrSplits, int index) {
        if("2".equals(xdrSplits[index])){
            this.tryAuths++;
        }
    }
    //todo 与关联有关
    public void setAuthTimes(String[] xdrSplits, int index) {
        if(!"2".equals(xdrSplits[index])){
            this.authTimes++;
        }
    }

    public void setDelay_Secreq(String[] xdrSplits, int index) {
        this.delay_Secreq = xdrSplits[index];
    }

    public void setDelay_Secres(String[] xdrSplits, int index) {
        this.delay_Secres = xdrSplits[index];
    }

    public void setTrySecs(String[] xdrSplits, int index) {
        this.trySecs = CommonUtils.strToInteger(xdrSplits[index]);
    }
    //todo 与关联有关
    public void setDelay_Relreq(IUReleaseDecode iuReleaseDecode) {
        if("11".equals(iuReleaseDecode.getIUCode())){
            String ranStart = iuReleaseDecode.getStartTime();
//            ranStart = ranStart.substring(0,ranStart.indexOf(".")+4);
            String attachStart = this.starttime;
//            attachStart = attachStart.substring(0,attachStart.indexOf(".")+4);
            this.delay_Relreq = (SimpleDateDeal.String2TimeStamp(ranStart)-SimpleDateDeal.String2TimeStamp(attachStart))+"";
        }
    }
    //todo 与关联有关
    public void setDelay_Relcmd(IUReleaseDecode iuReleaseDecode) {
        if("1".equals(iuReleaseDecode.getIUCode())){
            String ranStart = iuReleaseDecode.getStartTime();
//            ranStart = ranStart.substring(0,ranStart.indexOf(".")+4);
            String attachStart = this.starttime;
//            attachStart = attachStart.substring(0,attachStart.indexOf(".")+4);
            this.delay_Relcmd = (SimpleDateDeal.String2TimeStamp(ranStart)-SimpleDateDeal.String2TimeStamp(attachStart))+"";
        }
    }
    //todo 与关联有关
    public void setDelay_Relcmp(IUReleaseDecode iuReleaseDecode) {
        if("1".equals(iuReleaseDecode.getIUCode())){
            String ranEnd = iuReleaseDecode.getEndTime();
//            ranEnd = ranEnd.substring(0,ranEnd.indexOf(".")+4);
            String attachStart = this.starttime;
//            attachStart = attachStart.substring(0,attachStart.indexOf(".")+4);
            this.delay_Relcmp = (SimpleDateDeal.String2TimeStamp(ranEnd)-SimpleDateDeal.String2TimeStamp(attachStart))+"";
        }
    }
    //todo 与关联有关
    public void setResult_Iurel(IUReleaseDecode iuReleaseDecode) {
        if("1".equals(iuReleaseDecode.getIUCode()) || "11".equals(iuReleaseDecode.getIUCode())){
            Integer value = CommonUtils.strToInteger(iuReleaseDecode.getRANCause());
            if(value != null){
                if(value == 83||value == 11||value == 41||value == 16||value == 40){
                    this.result_Iurel = "0";
                }else{
                    this.result_Iurel = "1";
                }
            }
        }
    }
    //todo 与关联有关
    public void setCause_Iurel(IUReleaseDecode iuReleaseDecode) {
        if("1".equals(iuReleaseDecode.getIUCode()) || "11".equals(iuReleaseDecode.getIUCode())) {
            this.cause_Iurel = iuReleaseDecode.getRANCause();
        }
    }


    /*
     * 关联iu release话单
     */
    public void reIURelease(IUReleaseDecode iuReleaseDecode){
        setEndtime(iuReleaseDecode);
        setReleaserCode(iuReleaseDecode);
        setDelay_Relreq(iuReleaseDecode);
        setDelay_Relcmd(iuReleaseDecode);
        setDelay_Relcmp(iuReleaseDecode);
        setResult_Iurel(iuReleaseDecode);
        setCause_Iurel(iuReleaseDecode);
    }


    @Override
    public String toString() {
//        RedisUntil.backfill(this);
        return super.toString();
    }
}
