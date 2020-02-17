package com.tuoming.entity.s1udns;

import com.tuoming.common.CommonDecode;
import com.tuoming.common.RedisUntil;
import com.tuoming.entity.common.typeDecode;
import com.tuoming.tools.SimpleDateDeal;


public class S1udnsDecode extends CommonDecode {

    public String uTYPE = "";//话单类型
    public String starttime = "";//开始时间
    public String endtime = "";//结束时间
    public String cdrstat = "";//单据状态
    public String imsi = "";//IMSI
    public String imei = "";//终端类型
    public String msisdn = "";//手机号码
    public String enbSGSNIP = "";//SGSN或enodeB用户面IP
    public String enbSGSNGTPTEID = "";//SGSN或enodeB用户面TEID
    public String sgwGGSNIP = "";//GGSN或SGW用户面IP
    public String sgwGGSNGTPTEID = "";//GGSN或SGW用户面TEID
    public String rac = "";//路由区编码
    public String lacTAC = "";//位置区或跟踪区编码
    public String cgiECI = "";//小区号码
    public String rat = "";//网络类型
    public String apn = "";//APN
    public String ulDURARION = "";//上行持续时长
    public String dlDURARION = "";//下行持续时长
    public String ulIPPACKET = "";//上行IP包数
    public String dlIPPACKET = "";//下行IP包数
    public String upTRAFFIC = "";//上行流量
    public String downTRAFFIC = "";//下行流量
    public String clientIP = "";//终端IP
    public String srcPORT = "";//终端端口
    public String destIP = "";//访问IP
    public String destPORT = "";//服务器端口
    public String servname = "";//流量类型
    public String appSTATUS = "";//App Status
    public String appTYPEEXT1 = "";//应用类别扩展字段
    public String appTYPEEXT2 = "";//应用类别扩展字段
    public String appTYPEEXT3 = "";//应用类别扩展字段
    public String l4PROTOCAL = "";//4层协议类型
    public String requestQUERYDOMAIN = "";//请求查询的s1uS1udns域名
    public String queryRESULTIP = "";//查询结果IP
    public String responseCODE = "";//s1uS1udns响应码
    public String requestTIMES = "";//s1uS1udns的请求次数
    public String responseNUMBER = "";//响应数目
    public String contentNUMBER = "";//授权内容数目
    public String extraCONTENTNUMBER = "";//附加内容数目

    public S1udnsDecode() {

    }


    @Override
    public String getEndTime() {
        return endTime;
    }

    @Override
    public String getMmeIp() {
        return sgwGGSNIP;
    }

    public void getUTYPE(String[] str, int uTYPE_Index) {
        this.uTYPE = "3";
    }

    public void getSTARTTIME(String[] str, int starttime_Index) {
        this.starttime = SimpleDateDeal.getFormatDate(str[starttime_Index]);
    }

    public void getENDTIME(String[] str, int endtime_Index) {
        this.endtime = SimpleDateDeal.getFormatDate(str[endtime_Index]);
        super.endTime = SimpleDateDeal.getFormatDate(str[endtime_Index]);
    }

    public void getCDRSTAT(String[] str, int cdrstat_Index, int appSTATUS_Index) {
        this.cdrstat = str[cdrstat_Index];
        if (str[appSTATUS_Index] == "2") {
            this.cdrstat = "3";
        }
    }

    public void getIMSI(String[] str, int imsi_Index) {
        this.imsi = str[imsi_Index];
    }

    public void getIMEI(String[] str, int imei_Index) {
        this.imei = str[imei_Index];
    }

    public void getMSISDN(String[] str, int msisdn_Index) {
        this.msisdn = str[msisdn_Index];
    }

    public void getENBSGSNIP(String[] str, int enbSGSNIP_Index) {
        this.enbSGSNIP = str[enbSGSNIP_Index];
    }

    public void getENBSGSNGTPTEID(String[] str, int enbSGSNGTPTEID_Index) {
        this.enbSGSNGTPTEID = str[enbSGSNGTPTEID_Index];
    }

    public void getSGWGGSNIP(String[] str, int sgwGGSNIP_Index) {
        this.sgwGGSNIP = str[sgwGGSNIP_Index];
        super.machineIP = str[sgwGGSNIP_Index];
    }

    public void getSGWGGSNGTPTEID(String[] str, int sgwGGSNGTPTEID_Index) {
        this.sgwGGSNGTPTEID = str[sgwGGSNGTPTEID_Index];
    }

    public void getRAC(String[] str, int rac_Index) {
        this.rac = "";
    }

    public void getLACTAC(String[] str, int lacTAC_Index) {
        this.lacTAC = str[lacTAC_Index];
        //长度不够5就补0在前面
        this.lacTAC = SimpleDateDeal.leftSupply0(this.lacTAC, 5);
    }

    public void getCGIECI(String[] str, int cgiECI_Index) {
        this.cgiECI = str[cgiECI_Index];
    }

    public void getRAT(String[] str, int rat_Index) {
        this.rat = str[rat_Index];
    }

    public void getAPN(String[] str, int apn_Index) {
        this.apn = str[apn_Index];
    }

    public void getULDURARION(String[] str, int ulDURARION_Index) {
        this.ulDURARION = SimpleDateDeal.divide(str[ulDURARION_Index], "1000");
    }

    public void getDLDURARION(String[] str, int dlDURARION_Index) {
        this.ulDURARION = SimpleDateDeal.divide(str[dlDURARION_Index], "1000");
    }

    public void getULIPPACKET(String[] str, int ulIPPACKET_Index) {
        this.ulIPPACKET = str[ulIPPACKET_Index];
    }

    public void getDLIPPACKET(String[] str, int dlIPPACKET_Index) {
        this.dlIPPACKET = str[dlIPPACKET_Index];
    }

    public void getUPTRAFFIC(String[] str, int upTRAFFIC_Index) {
        this.upTRAFFIC = str[upTRAFFIC_Index];
    }

    public void getDOWNTRAFFIC(String[] str, int downTRAFFIC_Index) {
        this.downTRAFFIC = str[downTRAFFIC_Index];
    }

    public void getCLIENTIP(String[] str, int clientIP_Index, int clientIP_Index2, int machineIPAddtype_Index) {
        if (!str[clientIP_Index].equals("")) this.clientIP = str[clientIP_Index];
        else this.clientIP = str[clientIP_Index2];
    }

    public void getSRCPORT(String[] str, int srcPORT_Index) {
        this.srcPORT = str[srcPORT_Index];
    }

    public void getDESTIP(String[] str, int destIP_Index, int destIP_Index2, int machineIPAddtype_Index) {
        this.destIP = str[destIP_Index];
        if (!str[destIP_Index].equals("")) this.destIP = str[destIP_Index];
        else this.destIP = str[destIP_Index2];
    }

    public void getDESTPORT(String[] str, int destPORT_Index) {
        this.destPORT = str[destPORT_Index];
    }

    public void getSERVNAME(String[] str, int servname_Index) {
        this.servname = typeDecode.SERVNAME(str[servname_Index]);
    }

    public void getAPPSTATUS(String[] str, int appSTATUS_Index) {
        this.appSTATUS = str[appSTATUS_Index];
    }

    public void getAPPTYPEEXT1(String[] str, int appTYPEEXT1_Index) {
        this.appTYPEEXT1 = "";
    }

    public void getAPPTYPEEXT2(String[] str, int appTYPEEXT2_Index) {
        this.appTYPEEXT2 = "";
    }

    public void getAPPTYPEEXT3(String[] str, int appTYPEEXT3_Index) {
        this.appTYPEEXT3 = "";
    }

    public void getL4PROTOCAL(String[] str, int l4PROTOCAL_Index) {
        this.l4PROTOCAL = str[l4PROTOCAL_Index];
    }

    public void getREQUESTQUERYDOMAIN(String[] str, int requestQUERYDOMAIN_Index) {
        this.requestQUERYDOMAIN = str[requestQUERYDOMAIN_Index];
    }

    public void getQUERYRESULTIP(String[] str, int queryRESULTIP_Index) {
        this.queryRESULTIP = str[queryRESULTIP_Index];
    }

    public void getRESPONSECODE(String[] str, int responseCODE_Index) {
        this.responseCODE = str[responseCODE_Index];
    }

    public void getREQUESTTIMES(String[] str, int requestTIMES_Index) {
        this.requestTIMES = str[requestTIMES_Index];
    }

    public void getRESPONSENUMBER(String[] str, int responseNUMBER_Index) {
        this.responseNUMBER = str[responseNUMBER_Index];
    }

    public void getCONTENTNUMBER(String[] str, int contentNUMBER_Index) {
        this.contentNUMBER = str[contentNUMBER_Index];
    }

    public void getEXTRACONTENTNUMBER(String[] str, int extraCONTENTNUMBER_Index) {
        this.extraCONTENTNUMBER = str[extraCONTENTNUMBER_Index];
    }


    @Override
    public void decode(String[] str) {
        getUTYPE(str, S1udnsIndex.uTYPE_Index);
        getSTARTTIME(str, S1udnsIndex.starttime_Index);
        getENDTIME(str, S1udnsIndex.endtime_Index);
        getCDRSTAT(str, S1udnsIndex.cdrstat_Index, S1udnsIndex.appSTATUS_Index);
        getIMSI(str, S1udnsIndex.imsi_Index);
        getIMEI(str, S1udnsIndex.imei_Index);
        getMSISDN(str, S1udnsIndex.msisdn_Index);
        getMsisdn(str, S1udnsIndex.msisdn_Index);
        getENBSGSNIP(str, S1udnsIndex.enbSGSNIP_Index);
        getENBSGSNGTPTEID(str, S1udnsIndex.enbSGSNGTPTEID_Index);
        getSGWGGSNIP(str, S1udnsIndex.sgwGGSNIP_Index);
        getSGWGGSNGTPTEID(str, S1udnsIndex.sgwGGSNGTPTEID_Index);
        getRAC(str, S1udnsIndex.rac_Index);
        getLACTAC(str, S1udnsIndex.lacTAC_Index);
        getCGIECI(str, S1udnsIndex.cgiECI_Index);
        getRAT(str, S1udnsIndex.rat_Index);
        getAPN(str, S1udnsIndex.apn_Index);
        getULDURARION(str, S1udnsIndex.ulDURARION_Index);
        getDLDURARION(str, S1udnsIndex.dlDURARION_Index);
        getULIPPACKET(str, S1udnsIndex.ulIPPACKET_Index);
        getDLIPPACKET(str, S1udnsIndex.dlIPPACKET_Index);
        getUPTRAFFIC(str, S1udnsIndex.upTRAFFIC_Index);
        getDOWNTRAFFIC(str, S1udnsIndex.downTRAFFIC_Index);
        getCLIENTIP(str, S1udnsIndex.clientIP_Index, S1udnsIndex.clientIP_Index, S1udnsIndex.machineIPAddtype_Index);
        getSRCPORT(str, S1udnsIndex.srcPORT_Index);
        getDESTIP(str, S1udnsIndex.destIP_Index, S1udnsIndex.destIP_Index2, S1udnsIndex.machineIPAddtype_Index);
        getDESTPORT(str, S1udnsIndex.destPORT_Index);
        getSERVNAME(str, S1udnsIndex.servname_Index);
        getAPPSTATUS(str, S1udnsIndex.appSTATUS_Index);
        getAPPTYPEEXT1(str, S1udnsIndex.appTYPEEXT1_Index);
        getAPPTYPEEXT2(str, S1udnsIndex.appTYPEEXT2_Index);
        getAPPTYPEEXT3(str, S1udnsIndex.appTYPEEXT3_Index);
        getL4PROTOCAL(str, S1udnsIndex.l4PROTOCAL_Index);
        getREQUESTQUERYDOMAIN(str, S1udnsIndex.requestQUERYDOMAIN_Index);
        getQUERYRESULTIP(str, S1udnsIndex.queryRESULTIP_Index);
        getRESPONSECODE(str, S1udnsIndex.responseCODE_Index);
        getREQUESTTIMES(str, S1udnsIndex.requestTIMES_Index);
        getRESPONSENUMBER(str, S1udnsIndex.responseNUMBER_Index);
        getCONTENTNUMBER(str, S1udnsIndex.contentNUMBER_Index);
        getEXTRACONTENTNUMBER(str, S1udnsIndex.extraCONTENTNUMBER_Index);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(uTYPE).append("|")
                .append(starttime).append("|")
                .append(endtime).append("|")
                .append(cdrstat).append("|")
                .append(imsi).append("|")
                .append(imei).append("|")
                .append(msisdn).append("|")
                .append(enbSGSNIP).append("|")
                .append(enbSGSNGTPTEID).append("|")
                .append(sgwGGSNIP).append("|")
                .append(sgwGGSNGTPTEID).append("|")
                .append(rac).append("|")
                .append(lacTAC).append("|")
                .append(cgiECI).append("|")
                .append(rat).append("|")
                .append(apn).append("|")
                .append(ulDURARION).append("|")
                .append(dlDURARION).append("|")
                .append(ulIPPACKET).append("|")
                .append(dlIPPACKET).append("|")
                .append(upTRAFFIC).append("|")
                .append(downTRAFFIC).append("|")
                .append(clientIP).append("|")
                .append(srcPORT).append("|")
                .append(destIP).append("|")
                .append(destPORT).append("|")
                .append(servname).append("|")
                .append(appSTATUS).append("|")
                .append(appTYPEEXT1).append("|")
                .append(appTYPEEXT2).append("|")
                .append(appTYPEEXT3).append("|")
                .append(l4PROTOCAL).append("|")
                .append(requestQUERYDOMAIN).append("|")
                .append(queryRESULTIP).append("|")
                .append(responseCODE).append("|")
                .append(requestTIMES).append("|")
                .append(responseNUMBER).append("|")
                .append(contentNUMBER).append("|")
                .append(extraCONTENTNUMBER);
        return result.toString();
    }
}
