package com.tuoming.entity.a_iucs;

/**
 * a_iucs语音呼叫业务结构
 */
public class VoiceCallDecode {

    private String OUT_SEPARATOR = "|";

    private Integer m_iSpecFlag = null;			//特殊过程指示
    private Integer m_iCFType = null;				//附加业务类型
    private String m_sEndCGI = "";				//呼叫结束CGI/SAI
    private Integer m_iRRANType = null;			//对端接入网类型
    private String m_sRMSC = "";				    //对端MSC
    private String m_sRMSC_GT = "";				//对端MSC_GT
    private Integer m_iRDirection = null;			//对端局向
    private String m_sMSRN = "";				    //漫游号码
    private String m_sRPrefix = "";				//对端号码字冠
    private String m_sRPhone = "";				//对端号码
    private String m_sRIMSI = "";				    //对端IMSI
    private Integer m_iROperator = null;			//对端归属运营商
    private String m_sOCalled = "";			    //原被叫号码
    private String m_sCalledNum = "";			    //原始被叫号码
    private Long m_lSCP = null;			        //SCP
    private String m_sSCP_GT = "";			    //SCP_GT
    private String m_sCIC = "";			        //CIC
    private Long m_lTalkLen = null;			    //通话时长
    private Integer m_iCallDropFlag = null;		//掉话标志
    private Integer m_iVideoBackCause = null;	//可视电话回落原因
    private Long m_lDelay_CMReq = null;			//业务请求时间戳
    private Long m_lDelay_CMRes = null;			//业务接受时间戳
    private Integer m_iPagingTimes = null;		//寻呼次数
    private Long m_lDelay_PagingReq_1st = null;			//首次寻呼请求时间戳
    private Long m_lDelay_PagingReq_2nd = null;			//二次寻呼请求时间戳
    private Long m_lDelay_PagingReq_3rd = null;			//三次寻呼请求时间戳
    private Long m_lDelay_PagingReq_More = null;			//后续寻呼请求时间戳
    private Long m_lDelay_PagingRes = null;			//寻呼响应时间戳/LAU时间戳
    private Long m_lDelay_Paging_Net = null;			//网络寻呼时延
    private Integer m_iGlobalFlag = null;			    //全局寻呼标志
    private Integer m_iDelay_Setup = null;			//呼叫建立时延
    private Integer m_iDelay_CallProc = null;		//呼叫处理时延
    private Integer m_iDelay_IDP = null;			    //IDP时延
    private Integer m_iDelay_Connect_CAP = null;			//continue/connect时延
    private Integer m_iDelay_Release_CAP = null;			//智能网Release时延
    private Integer m_iDelay_AssignReq = null;			//指配请求时延
    private Integer m_iDelay_AssignCmp = null;			//指配完成时延
    private Integer m_iDelay_SRI = null;			//SRI请求时延
    private Integer m_iDelay_SRIRes = null;		//SRI返回时延
    private Integer m_iDelay_PRN = null;			//PRN请求时延
    private Integer m_iDelay_PRNRes = null;		//PRN返回时延
    private Integer m_iDelay_IAM = null;			//IAM时延
    private Integer m_iDelay_ACM = null;			//ACM时延
    private Integer m_iDelay_Alerting = null;	//Alerting时延
    private Integer m_iDelay_ANM = null;			//ANM时延
    private Integer m_iDelay_Connect = null;			//连接时延
    private Integer m_iDelay_ConnectAck = null;		//连接确认时延
    private Integer m_iDelay_Disconnect = null;		//断连时延
    private Integer m_iDelay_REL = null;			//REL时延
    private Integer m_iDelay_RLC = null;			//RLC时延
    private Integer m_iDelay_Release = null;		//释放时延
    private Integer m_iDelay_ReleaseCmp = null;		//释放完成时延
    private Integer m_iDelay_ClearReq = null;		//清除请求时延
    private Integer m_iDelay_ClearCmd = null;		//清除命令时延
    private Integer m_iDelay_ClearCmp = null;		//清除完成时延
    private Integer m_iExCallResult = null;			//扩展呼叫结果
    private Integer m_iCallInterval = null;			//呼叫间隔
    private Integer m_iRelDirection = null;			//释放方向
    private Integer m_iHODuration = null;			//切换持续时长
    private Integer m_iHOTimes = null;			//切换次数
    private Integer m_iAuthTimes = null;			//鉴权次数
    private Integer m_iDelay_AuthReq_1st = null;			//第一次鉴权请求时延
    private Integer m_iDelay_AuthReq_2nd = null;			//第二次鉴权请求时延
    private Integer m_iDelay_AuthRes = null;			//鉴权响应时延
    private Integer m_iAuthResult_1st = null;		//第一次鉴权失败结果
    private Long m_lAuthEroorCode_1st = null;		//第一次鉴权失败原因
    private Integer m_iAucVecCount = null;			//取鉴权向量组数
    private Integer m_iDelay_AucVecReq = null;		//取鉴权向量请求时延
    private Integer m_iDelay_AucVecRes = null;		//取鉴权向量响应时延
    private Integer m_iEncryAlgorithm = null;		//加密算法
    private Integer m_iDelay_CIPCmd = null;			//加密命令时延
    private Integer m_iDelay_CIPCmp = null;			//加密命令完成时延
    private Integer m_iDelay_TMSICmd = null;			//TMSI重分配命令时延
    private Integer m_iDelay_TMSICmp = null;			//TMSI重分配完成时延
    private Integer m_iIdentityType = null;			//取ID类型
    private Integer m_iDelay_IDReq = null;			//取ID请求时延
    private Integer m_iDelay_IDRes = null;			//取ID返回时延
    private Integer m_iHLR = null;			    //HLR
    private String m_sHLR_GT = "";			//HLRGT码
    private Integer m_iErrorPhaseInd = null;		//失败阶段指示
    private Integer m_iRANType = null;			//接入网类型
    private Integer m_iMSC = null;			//MSC
    private String m_sMSC_GT = "";		//MSC或VLR的GT码
    private String m_sMSCIP = "";			//MSCIP
    private String m_sMGWIP = "";			//MGWIP
    private Integer m_iBSC = null;			//BSC/RNC
    private String m_sLAI = "";			//LAI
    private String m_sCGI = "";			//CGI/SAI
    private String m_sTMSI = "";			//TMSI
    private String m_sIMEI = "";			//IMEI/IMEISV
    private Integer m_iIMEIRanType = null;		//终端网络模式
    private Integer m_iVProvince = null;			//拜访省
    private Integer m_iVCity = null;			    //拜访地市
    private Integer m_iVCountry = null;			//拜访县
    private String m_sStartTime = "";			//开始时间
    private String m_sEndTime = "";			//结束时间
    private Integer m_iEventType = null;			//事件类型
    private Integer m_iSubevtType = null;			//事件子类型
    private Integer m_iCombFlag = null;			//合成标志
    private Integer m_iUserARD = null;			//用户接入类型
    private Integer m_iUserType = null;			//用户类型
    private String m_sPhonePrefix = "";			//用户字冠
    private String m_sPhone = "";			//用户号码
    private String m_sIMSI = "";			//用户IMSI
    private Integer m_iHCountry = null;		//归属国家
    private Integer m_iHProvince = null;		//归属省
    private Integer m_iHCity = null;			//归属地市
    private Integer m_iRoamType = null;		//漫游类型
    private Integer m_iHOperator = null;		//归属运营商
    private Integer m_iResult = null;			//过程结果
    private Integer m_iErrorCode = null;			//失败原因
    private Integer m_iReleaserCode = null;		//释放原因
    private Integer m_iFailureMsg = null;			//失败消息代码
    private Integer m_iFailurePreMsg = null;		//失败前正常消息
    private String m_sTDRID = "";			//单接口事件关联标识
    private String m_sCallID = "";		//多接口事件关联标识
    private String m_sTest1 = "";			//用于后台调试程序使用。
    private String m_sTest2 = "";			//用于后台调试程序使用。
    private Integer m_iDelay_Setup2 = null;		//setup时延2
    private Integer m_iCSMO = null;			    //是否4G回落主叫
    private Integer m_iEndOfCSFB = null;			//CSFB呼叫结束
    private Integer m_iFddSupport = null;			//是否支持FDD
    private Integer m_iTddSupport = null;			//是否支持TDD
    private String m_sRAND = "";			//鉴权随机数
    private String m_sAUTN = "";			//鉴权向量


    public void setM_iSpecFlag(String[] xdrSplits, int index) {
        this.m_iSpecFlag = m_iSpecFlag;
    }

    public void setM_iCFType(String[] xdrSplits, int index) {
        this.m_iCFType = m_iCFType;
    }

    public void setM_sEndCGI(String[] xdrSplits, int index) {
        this.m_sEndCGI = m_sEndCGI;
    }

    public void setM_iRRANType(String[] xdrSplits, int index) {
        this.m_iRRANType = m_iRRANType;
    }

    public void setM_sRMSC(String[] xdrSplits, int index) {
        this.m_sRMSC = m_sRMSC;
    }

    public void setM_sRMSC_GT(String[] xdrSplits, int index) {
        this.m_sRMSC_GT = m_sRMSC_GT;
    }

    public void setM_iRDirection(String[] xdrSplits, int index) {
        this.m_iRDirection = m_iRDirection;
    }

    public void setM_sMSRN(String[] xdrSplits, int index) {
        this.m_sMSRN = m_sMSRN;
    }

    public void setM_sRPrefix(String[] xdrSplits, int index) {
        this.m_sRPrefix = m_sRPrefix;
    }

    public void setM_sRPhone(String[] xdrSplits, int index) {
        this.m_sRPhone = m_sRPhone;
    }

    public void setM_sRIMSI(String[] xdrSplits, int index) {
        this.m_sRIMSI = m_sRIMSI;
    }

    public void setM_iROperator(String[] xdrSplits, int index) {
        this.m_iROperator = m_iROperator;
    }

    public void setM_sOCalled(String[] xdrSplits, int index) {
        this.m_sOCalled = m_sOCalled;
    }

    public void setM_sCalledNum(String[] xdrSplits, int index) {
        this.m_sCalledNum = m_sCalledNum;
    }

    public void setM_lSCP(String[] xdrSplits, int index) {
        this.m_lSCP = m_lSCP;
    }

    public void setM_sSCP_GT(String[] xdrSplits, int index) {
        this.m_sSCP_GT = m_sSCP_GT;
    }

    public void setM_sCIC(String[] xdrSplits, int index) {
        this.m_sCIC = m_sCIC;
    }

    public void setM_lTalkLen(String[] xdrSplits, int index) {
        this.m_lTalkLen = m_lTalkLen;
    }

    public void setM_iCallDropFlag(String[] xdrSplits, int index) {
        this.m_iCallDropFlag = m_iCallDropFlag;
    }

    public void setM_iVideoBackCause(String[] xdrSplits, int index) {
        this.m_iVideoBackCause = m_iVideoBackCause;
    }

    public void setM_lDelay_CMReq(String[] xdrSplits, int index) {
        this.m_lDelay_CMReq = m_lDelay_CMReq;
    }

    public void setM_lDelay_CMRes(String[] xdrSplits, int index) {
        this.m_lDelay_CMRes = m_lDelay_CMRes;
    }

    public void setM_iPagingTimes(String[] xdrSplits, int index) {
        this.m_iPagingTimes = m_iPagingTimes;
    }

    public void setM_lDelay_PagingReq_1st(String[] xdrSplits, int index) {
        this.m_lDelay_PagingReq_1st = m_lDelay_PagingReq_1st;
    }

    public void setM_lDelay_PagingReq_2nd(String[] xdrSplits, int index) {
        this.m_lDelay_PagingReq_2nd = m_lDelay_PagingReq_2nd;
    }

    public void setM_lDelay_PagingReq_3rd(String[] xdrSplits, int index) {
        this.m_lDelay_PagingReq_3rd = m_lDelay_PagingReq_3rd;
    }

    public void setM_lDelay_PagingReq_More(String[] xdrSplits, int index) {
        this.m_lDelay_PagingReq_More = m_lDelay_PagingReq_More;
    }

    public void setM_lDelay_PagingRes(String[] xdrSplits, int index) {
        this.m_lDelay_PagingRes = m_lDelay_PagingRes;
    }

    public void setM_lDelay_Paging_Net(String[] xdrSplits, int index) {
        this.m_lDelay_Paging_Net = m_lDelay_Paging_Net;
    }

    public void setM_iGlobalFlag(String[] xdrSplits, int index) {
        this.m_iGlobalFlag = m_iGlobalFlag;
    }

    public void setM_iDelay_Setup(String[] xdrSplits, int index) {
        this.m_iDelay_Setup = m_iDelay_Setup;
    }

    public void setM_iDelay_CallProc(String[] xdrSplits, int index) {
        this.m_iDelay_CallProc = m_iDelay_CallProc;
    }

    public void setM_iDelay_IDP(String[] xdrSplits, int index) {
        this.m_iDelay_IDP = m_iDelay_IDP;
    }

    public void setM_iDelay_Connect_CAP(String[] xdrSplits, int index) {
        this.m_iDelay_Connect_CAP = m_iDelay_Connect_CAP;
    }

    public void setM_iDelay_Release_CAP(String[] xdrSplits, int index) {
        this.m_iDelay_Release_CAP = m_iDelay_Release_CAP;
    }

    public void setM_iDelay_AssignReq(String[] xdrSplits, int index) {
        this.m_iDelay_AssignReq = m_iDelay_AssignReq;
    }

    public void setM_iDelay_AssignCmp(String[] xdrSplits, int index) {
        this.m_iDelay_AssignCmp = m_iDelay_AssignCmp;
    }

    public void setM_iDelay_SRI(String[] xdrSplits, int index) {
        this.m_iDelay_SRI = m_iDelay_SRI;
    }

    public void setM_iDelay_SRIRes(String[] xdrSplits, int index) {
        this.m_iDelay_SRIRes = m_iDelay_SRIRes;
    }

    public void setM_iDelay_PRN(String[] xdrSplits, int index) {
        this.m_iDelay_PRN = m_iDelay_PRN;
    }

    public void setM_iDelay_PRNRes(String[] xdrSplits, int index) {
        this.m_iDelay_PRNRes = m_iDelay_PRNRes;
    }

    public void setM_iDelay_IAM(String[] xdrSplits, int index) {
        this.m_iDelay_IAM = m_iDelay_IAM;
    }

    public void setM_iDelay_ACM(String[] xdrSplits, int index) {
        this.m_iDelay_ACM = m_iDelay_ACM;
    }

    public void setM_iDelay_Alerting(String[] xdrSplits, int index) {
        this.m_iDelay_Alerting = m_iDelay_Alerting;
    }

    public void setM_iDelay_ANM(String[] xdrSplits, int index) {
        this.m_iDelay_ANM = m_iDelay_ANM;
    }

    public void setM_iDelay_Connect(String[] xdrSplits, int index) {
        this.m_iDelay_Connect = m_iDelay_Connect;
    }

    public void setM_iDelay_ConnectAck(String[] xdrSplits, int index) {
        this.m_iDelay_ConnectAck = m_iDelay_ConnectAck;
    }

    public void setM_iDelay_Disconnect(String[] xdrSplits, int index) {
        this.m_iDelay_Disconnect = m_iDelay_Disconnect;
    }

    public void setM_iDelay_REL(String[] xdrSplits, int index) {
        this.m_iDelay_REL = m_iDelay_REL;
    }

    public void setM_iDelay_RLC(String[] xdrSplits, int index) {
        this.m_iDelay_RLC = m_iDelay_RLC;
    }

    public void setM_iDelay_Release(String[] xdrSplits, int index) {
        this.m_iDelay_Release = m_iDelay_Release;
    }

    public void setM_iDelay_ReleaseCmp(String[] xdrSplits, int index) {
        this.m_iDelay_ReleaseCmp = m_iDelay_ReleaseCmp;
    }

    public void setM_iDelay_ClearReq(String[] xdrSplits, int index) {
        this.m_iDelay_ClearReq = m_iDelay_ClearReq;
    }

    public void setM_iDelay_ClearCmd(String[] xdrSplits, int index) {
        this.m_iDelay_ClearCmd = m_iDelay_ClearCmd;
    }

    public void setM_iDelay_ClearCmp(String[] xdrSplits, int index) {
        this.m_iDelay_ClearCmp = m_iDelay_ClearCmp;
    }

    public void setM_iExCallResult(String[] xdrSplits, int index) {
        this.m_iExCallResult = m_iExCallResult;
    }

    public void setM_iCallInterval(String[] xdrSplits, int index) {
        this.m_iCallInterval = m_iCallInterval;
    }

    public void setM_iRelDirection(String[] xdrSplits, int index) {
        this.m_iRelDirection = m_iRelDirection;
    }

    public void setM_iHODuration(String[] xdrSplits, int index) {
        this.m_iHODuration = m_iHODuration;
    }

    public void setM_iHOTimes(String[] xdrSplits, int index) {
        this.m_iHOTimes = m_iHOTimes;
    }

    public void setM_iAuthTimes(String[] xdrSplits, int index) {
        this.m_iAuthTimes = m_iAuthTimes;
    }

    public void setM_iDelay_AuthReq_1st(String[] xdrSplits, int index) {
        this.m_iDelay_AuthReq_1st = m_iDelay_AuthReq_1st;
    }

    public void setM_iDelay_AuthReq_2nd(String[] xdrSplits, int index) {
        this.m_iDelay_AuthReq_2nd = m_iDelay_AuthReq_2nd;
    }

    public void setM_iDelay_AuthRes(String[] xdrSplits, int index) {
        this.m_iDelay_AuthRes = m_iDelay_AuthRes;
    }

    public void setM_iAuthResult_1st(String[] xdrSplits, int index) {
        this.m_iAuthResult_1st = m_iAuthResult_1st;
    }

    public void setM_lAuthEroorCode_1st(String[] xdrSplits, int index) {
        this.m_lAuthEroorCode_1st = m_lAuthEroorCode_1st;
    }

    public void setM_iAucVecCount(String[] xdrSplits, int index) {
        this.m_iAucVecCount = m_iAucVecCount;
    }

    public void setM_iDelay_AucVecReq(String[] xdrSplits, int index) {
        this.m_iDelay_AucVecReq = m_iDelay_AucVecReq;
    }

    public void setM_iDelay_AucVecRes(String[] xdrSplits, int index) {
        this.m_iDelay_AucVecRes = m_iDelay_AucVecRes;
    }

    public void setM_iEncryAlgorithm(String[] xdrSplits, int index) {
        this.m_iEncryAlgorithm = m_iEncryAlgorithm;
    }

    public void setM_iDelay_CIPCmd(String[] xdrSplits, int index) {
        this.m_iDelay_CIPCmd = m_iDelay_CIPCmd;
    }

    public void setM_iDelay_CIPCmp(String[] xdrSplits, int index) {
        this.m_iDelay_CIPCmp = m_iDelay_CIPCmp;
    }

    public void setM_iDelay_TMSICmd(String[] xdrSplits, int index) {
        this.m_iDelay_TMSICmd = m_iDelay_TMSICmd;
    }

    public void setM_iDelay_TMSICmp(String[] xdrSplits, int index) {
        this.m_iDelay_TMSICmp = m_iDelay_TMSICmp;
    }

    public void setM_iIdentityType(String[] xdrSplits, int index) {
        this.m_iIdentityType = m_iIdentityType;
    }

    public void setM_iDelay_IDReq(String[] xdrSplits, int index) {
        this.m_iDelay_IDReq = m_iDelay_IDReq;
    }

    public void setM_iDelay_IDRes(String[] xdrSplits, int index) {
        this.m_iDelay_IDRes = m_iDelay_IDRes;
    }

    public void setM_iHLR(String[] xdrSplits, int index) {
        this.m_iHLR = m_iHLR;
    }

    public void setM_sHLR_GT(String[] xdrSplits, int index) {
        this.m_sHLR_GT = m_sHLR_GT;
    }

    public void setM_iErrorPhaseInd(String[] xdrSplits, int index) {
        this.m_iErrorPhaseInd = m_iErrorPhaseInd;
    }

    public void setM_iRANType(String[] xdrSplits, int index) {
        this.m_iRANType = m_iRANType;
    }

    public void setM_iMSC(String[] xdrSplits, int index) {
        this.m_iMSC = m_iMSC;
    }

    public void setM_sMSC_GT(String[] xdrSplits, int index) {
        this.m_sMSC_GT = m_sMSC_GT;
    }

    public void setM_sMSCIP(String[] xdrSplits, int index) {
        this.m_sMSCIP = m_sMSCIP;
    }

    public void setM_sMGWIP(String[] xdrSplits, int index) {
        this.m_sMGWIP = m_sMGWIP;
    }

    public void setM_iBSC(String[] xdrSplits, int index) {
        this.m_iBSC = m_iBSC;
    }

    public void setM_sLAI(String[] xdrSplits, int index) {
        this.m_sLAI = m_sLAI;
    }

    public void setM_sCGI(String[] xdrSplits, int index) {
        this.m_sCGI = m_sCGI;
    }

    public void setM_sTMSI(String[] xdrSplits, int index) {
        this.m_sTMSI = m_sTMSI;
    }

    public void setM_sIMEI(String[] xdrSplits, int index) {
        this.m_sIMEI = m_sIMEI;
    }

    public void setM_iIMEIRanType(String[] xdrSplits, int index) {
        this.m_iIMEIRanType = m_iIMEIRanType;
    }

    public void setM_iVProvince(String[] xdrSplits, int index) {
        this.m_iVProvince = m_iVProvince;
    }

    public void setM_iVCity(String[] xdrSplits, int index) {
        this.m_iVCity = m_iVCity;
    }

    public void setM_iVCountry(String[] xdrSplits, int index) {
        this.m_iVCountry = m_iVCountry;
    }

    public void setM_sStartTime(String[] xdrSplits, int index) {
        this.m_sStartTime = m_sStartTime;
    }

    public void setM_sEndTime(String[] xdrSplits, int index) {
        this.m_sEndTime = m_sEndTime;
    }

    public void setM_iEventType(String[] xdrSplits, int index) {
        this.m_iEventType = m_iEventType;
    }

    public void setM_iSubevtType(String[] xdrSplits, int index) {
        this.m_iSubevtType = m_iSubevtType;
    }

    public void setM_iCombFlag(String[] xdrSplits, int index) {
        this.m_iCombFlag = m_iCombFlag;
    }

    public void setM_iUserARD(String[] xdrSplits, int index) {
        this.m_iUserARD = m_iUserARD;
    }

    public void setM_iUserType(String[] xdrSplits, int index) {
        this.m_iUserType = m_iUserType;
    }

    public void setM_sPhonePrefix(String[] xdrSplits, int index) {
        this.m_sPhonePrefix = m_sPhonePrefix;
    }

    public void setM_sPhone(String[] xdrSplits, int index) {
        this.m_sPhone = m_sPhone;
    }

    public void setM_sIMSI(String[] xdrSplits, int index) {
        this.m_sIMSI = m_sIMSI;
    }

    public void setM_iHCountry(String[] xdrSplits, int index) {
        this.m_iHCountry = m_iHCountry;
    }

    public void setM_iHProvince(String[] xdrSplits, int index) {
        this.m_iHProvince = m_iHProvince;
    }

    public void setM_iHCity(String[] xdrSplits, int index) {
        this.m_iHCity = m_iHCity;
    }

    public void setM_iRoamType(String[] xdrSplits, int index) {
        this.m_iRoamType = m_iRoamType;
    }

    public void setM_iHOperator(String[] xdrSplits, int index) {
        this.m_iHOperator = m_iHOperator;
    }

    public void setM_iResult(String[] xdrSplits, int index) {
        this.m_iResult = m_iResult;
    }

    public void setM_iErrorCode(String[] xdrSplits, int index) {
        this.m_iErrorCode = m_iErrorCode;
    }

    public void setM_iReleaserCode(String[] xdrSplits, int index) {
        this.m_iReleaserCode = m_iReleaserCode;
    }

    public void setM_iFailureMsg(String[] xdrSplits, int index) {
        this.m_iFailureMsg = m_iFailureMsg;
    }

    public void setM_iFailurePreMsg(String[] xdrSplits, int index) {
        this.m_iFailurePreMsg = m_iFailurePreMsg;
    }

    public void setM_sTDRID(String[] xdrSplits, int index) {
        this.m_sTDRID = m_sTDRID;
    }

    public void setM_sCallID(String[] xdrSplits, int index) {
        this.m_sCallID = m_sCallID;
    }

    public void setM_sTest1(String[] xdrSplits, int index) {
        this.m_sTest1 = m_sTest1;
    }

    public void setM_sTest2(String[] xdrSplits, int index) {
        this.m_sTest2 = m_sTest2;
    }

    public void setM_iDelay_Setup2(String[] xdrSplits, int index) {
        this.m_iDelay_Setup2 = m_iDelay_Setup2;
    }

    public void setM_iCSMO(String[] xdrSplits, int index) {
        this.m_iCSMO = m_iCSMO;
    }

    public void setM_iEndOfCSFB(String[] xdrSplits, int index) {
        this.m_iEndOfCSFB = m_iEndOfCSFB;
    }

    public void setM_iFddSupport(String[] xdrSplits, int index) {
        this.m_iFddSupport = m_iFddSupport;
    }

    public void setM_iTddSupport(String[] xdrSplits, int index) {
        this.m_iTddSupport = m_iTddSupport;
    }

    public void setM_sRAND(String[] xdrSplits, int index) {
        this.m_sRAND = m_sRAND;
    }

    public void setM_sAUTN(String[] xdrSplits, int index) {
        this.m_sAUTN = m_sAUTN;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(m_iSpecFlag == null?"":m_iSpecFlag).append(OUT_SEPARATOR).
                append(m_iCFType == null?"":m_iCFType).append(OUT_SEPARATOR).
                append(m_sEndCGI).append(OUT_SEPARATOR).
                append(m_iRRANType == null?"":m_iRRANType).append(OUT_SEPARATOR).
                append(m_sRMSC).append(OUT_SEPARATOR).
                append(m_sRMSC_GT).append(OUT_SEPARATOR).
                append(m_iRDirection == null?"":m_iRDirection).append(OUT_SEPARATOR).
                append(m_sMSRN).append(OUT_SEPARATOR).
                append(m_sRPrefix).append(OUT_SEPARATOR).
                append(m_sRPhone).append(OUT_SEPARATOR).
                append(m_sRIMSI).append(OUT_SEPARATOR).
                append(m_iROperator == null?"":m_iROperator).append(OUT_SEPARATOR).
                append(m_sOCalled).append(OUT_SEPARATOR).
                append(m_sCalledNum).append(OUT_SEPARATOR).
                append(m_lSCP == null?"":m_lSCP).append(OUT_SEPARATOR).
                append(m_sSCP_GT).append(OUT_SEPARATOR).
                append(m_sCIC).append(OUT_SEPARATOR).
                append(m_lTalkLen == null?"":m_lTalkLen).append(OUT_SEPARATOR).
                append(m_iCallDropFlag == null?"":m_iCallDropFlag).append(OUT_SEPARATOR).
                append(m_iVideoBackCause == null?"":m_iVideoBackCause).append(OUT_SEPARATOR).
                append(m_lDelay_CMReq == null?"":m_lDelay_CMReq).append(OUT_SEPARATOR).
                append(m_lDelay_CMRes == null?"":m_lDelay_CMRes).append(OUT_SEPARATOR).
                append(m_iPagingTimes == null?"":m_iPagingTimes).append(OUT_SEPARATOR).
                append(m_lDelay_PagingReq_1st == null?"":m_lDelay_PagingReq_1st).append(OUT_SEPARATOR).
                append(m_lDelay_PagingReq_2nd == null?"":m_lDelay_PagingReq_2nd).append(OUT_SEPARATOR).
                append(m_lDelay_PagingReq_3rd == null?"":m_lDelay_PagingReq_3rd).append(OUT_SEPARATOR).
                append(m_lDelay_PagingReq_More == null?"":m_lDelay_PagingReq_More).append(OUT_SEPARATOR).
                append(m_lDelay_PagingRes == null?"":m_lDelay_PagingRes).append(OUT_SEPARATOR).
                append(m_lDelay_Paging_Net == null?"":m_lDelay_Paging_Net).append(OUT_SEPARATOR).
                append(m_iGlobalFlag == null?"":m_iGlobalFlag).append(OUT_SEPARATOR).
                append(m_iDelay_Setup == null?"":m_iDelay_Setup).append(OUT_SEPARATOR).
                append(m_iDelay_CallProc == null?"":m_iDelay_CallProc).append(OUT_SEPARATOR).
                append(m_iDelay_IDP == null?"":m_iDelay_IDP).append(OUT_SEPARATOR).
                append(m_iDelay_Connect_CAP == null?"":m_iDelay_Connect_CAP).append(OUT_SEPARATOR).
                append(m_iDelay_Release_CAP == null?"":m_iDelay_Release_CAP).append(OUT_SEPARATOR).
                append(m_iDelay_AssignReq == null?"":m_iDelay_AssignReq).append(OUT_SEPARATOR).
                append(m_iDelay_AssignCmp == null?"":m_iDelay_AssignCmp).append(OUT_SEPARATOR).
                append(m_iDelay_SRI == null?"":m_iDelay_SRI).append(OUT_SEPARATOR).
                append(m_iDelay_SRIRes == null?"":m_iDelay_SRIRes).append(OUT_SEPARATOR).
                append(m_iDelay_PRN == null?"":m_iDelay_PRN).append(OUT_SEPARATOR).
                append(m_iDelay_PRNRes == null?"":m_iDelay_PRNRes).append(OUT_SEPARATOR).
                append(m_iDelay_IAM == null?"":m_iDelay_IAM).append(OUT_SEPARATOR).
                append(m_iDelay_ACM == null?"":m_iDelay_ACM).append(OUT_SEPARATOR).
                append(m_iDelay_Alerting == null?"":m_iDelay_Alerting).append(OUT_SEPARATOR).
                append(m_iDelay_ANM == null?"":m_iDelay_ANM).append(OUT_SEPARATOR).
                append(m_iDelay_Connect == null?"":m_iDelay_Connect).append(OUT_SEPARATOR).
                append(m_iDelay_ConnectAck == null?"":m_iDelay_ConnectAck).append(OUT_SEPARATOR).
                append(m_iDelay_Disconnect == null?"":m_iDelay_Disconnect).append(OUT_SEPARATOR).
                append(m_iDelay_REL == null?"":m_iDelay_REL).append(OUT_SEPARATOR).
                append(m_iDelay_RLC == null?"":m_iDelay_RLC).append(OUT_SEPARATOR).
                append(m_iDelay_Release == null?"":m_iDelay_Release).append(OUT_SEPARATOR).
                append(m_iDelay_ReleaseCmp == null?"":m_iDelay_ReleaseCmp).append(OUT_SEPARATOR).
                append(m_iDelay_ClearReq == null?"":m_iDelay_ClearReq).append(OUT_SEPARATOR).
                append(m_iDelay_ClearCmd == null?"":m_iDelay_ClearCmd).append(OUT_SEPARATOR).
                append(m_iDelay_ClearCmp == null?"":m_iDelay_ClearCmp).append(OUT_SEPARATOR).
                append(m_iExCallResult == null?"":m_iExCallResult).append(OUT_SEPARATOR).
                append(m_iCallInterval == null?"":m_iCallInterval).append(OUT_SEPARATOR).
                append(m_iRelDirection == null?"":m_iRelDirection).append(OUT_SEPARATOR).
                append(m_iHODuration == null?"":m_iHODuration).append(OUT_SEPARATOR).
                append(m_iHOTimes == null?"":m_iHOTimes).append(OUT_SEPARATOR).
                append(m_iAuthTimes == null?"":m_iAuthTimes).append(OUT_SEPARATOR).
                append(m_iDelay_AuthReq_1st == null?"":m_iDelay_AuthReq_1st).append(OUT_SEPARATOR).
                append(m_iDelay_AuthReq_2nd == null?"":m_iDelay_AuthReq_2nd).append(OUT_SEPARATOR).
                append(m_iDelay_AuthRes == null?"":m_iDelay_AuthRes).append(OUT_SEPARATOR).
                append(m_iAuthResult_1st == null?"":m_iAuthResult_1st).append(OUT_SEPARATOR).
                append(m_lAuthEroorCode_1st == null?"":m_lAuthEroorCode_1st).append(OUT_SEPARATOR).
                append(m_iAucVecCount == null?"":m_iAucVecCount).append(OUT_SEPARATOR).
                append(m_iDelay_AucVecReq == null?"":m_iDelay_AucVecReq).append(OUT_SEPARATOR).
                append(m_iDelay_AucVecRes == null?"":m_iDelay_AucVecRes).append(OUT_SEPARATOR).
                append(m_iEncryAlgorithm == null?"":m_iEncryAlgorithm).append(OUT_SEPARATOR).
                append(m_iDelay_CIPCmd == null?"":m_iDelay_CIPCmd).append(OUT_SEPARATOR).
                append(m_iDelay_CIPCmp == null?"":m_iDelay_CIPCmp).append(OUT_SEPARATOR).
                append(m_iDelay_TMSICmd == null?"":m_iDelay_TMSICmd).append(OUT_SEPARATOR).
                append(m_iDelay_TMSICmp == null?"":m_iDelay_TMSICmp).append(OUT_SEPARATOR).
                append(m_iIdentityType == null?"":m_iIdentityType).append(OUT_SEPARATOR).
                append(m_iDelay_IDReq == null?"":m_iDelay_IDReq).append(OUT_SEPARATOR).
                append(m_iDelay_IDRes == null?"":m_iDelay_IDRes).append(OUT_SEPARATOR).
                append(m_iHLR == null?"":m_iHLR).append(OUT_SEPARATOR).
                append(m_sHLR_GT).append(OUT_SEPARATOR).
                append(m_iErrorPhaseInd == null?"":m_iErrorPhaseInd).append(OUT_SEPARATOR).
                append(m_iRANType == null?"":m_iRANType).append(OUT_SEPARATOR).
                append(m_iMSC == null?"":m_iMSC).append(OUT_SEPARATOR).
                append(m_sMSC_GT).append(OUT_SEPARATOR).
                append(m_sMSCIP).append(OUT_SEPARATOR).
                append(m_sMGWIP).append(OUT_SEPARATOR).
                append(m_iBSC == null?"":m_iBSC).append(OUT_SEPARATOR).
                append(m_sLAI).append(OUT_SEPARATOR).
                append(m_sCGI).append(OUT_SEPARATOR).
                append(m_sTMSI).append(OUT_SEPARATOR).
                append(m_sIMEI).append(OUT_SEPARATOR).
                append(m_iIMEIRanType == null?"":m_iIMEIRanType).append(OUT_SEPARATOR).
                append(m_iVProvince == null?"":m_iVProvince).append(OUT_SEPARATOR).
                append(m_iVCity == null?"":m_iVCity).append(OUT_SEPARATOR).
                append(m_iVCountry == null?"":m_iVCountry).append(OUT_SEPARATOR).
                append(m_sStartTime).append(OUT_SEPARATOR).
                append(m_sEndTime).append(OUT_SEPARATOR).
                append(m_iEventType == null?"":m_iEventType).append(OUT_SEPARATOR).
                append(m_iSubevtType == null?"":m_iSubevtType).append(OUT_SEPARATOR).
                append(m_iCombFlag == null?"":m_iCombFlag).append(OUT_SEPARATOR).
                append(m_iUserARD == null?"":m_iUserARD).append(OUT_SEPARATOR).
                append(m_iUserType == null?"":m_iUserType).append(OUT_SEPARATOR).
                append(m_sPhonePrefix).append(OUT_SEPARATOR).
                append(m_sPhone).append(OUT_SEPARATOR).
                append(m_sIMSI).append(OUT_SEPARATOR).
                append(m_iHCountry == null?"":m_iHCountry).append(OUT_SEPARATOR).
                append(m_iHProvince == null?"":m_iHProvince).append(OUT_SEPARATOR).
                append(m_iHCity == null?"":m_iHCity).append(OUT_SEPARATOR).
                append(m_iRoamType == null?"":m_iRoamType).append(OUT_SEPARATOR).
                append(m_iHOperator == null?"":m_iHOperator).append(OUT_SEPARATOR).
                append(m_iResult == null?"":m_iResult).append(OUT_SEPARATOR).
                append(m_iErrorCode == null?"":m_iErrorCode).append(OUT_SEPARATOR).
                append(m_iReleaserCode == null?"":m_iReleaserCode).append(OUT_SEPARATOR).
                append(m_iFailureMsg == null?"":m_iFailureMsg).append(OUT_SEPARATOR).
                append(m_iFailurePreMsg == null?"":m_iFailurePreMsg).append(OUT_SEPARATOR).
                append(m_sTDRID).append(OUT_SEPARATOR).
                append(m_sCallID).append(OUT_SEPARATOR).
                append(m_sTest1).append(OUT_SEPARATOR).
                append(m_sTest2).append(OUT_SEPARATOR).
                append(m_iDelay_Setup2 == null?"":m_iDelay_Setup2).append(OUT_SEPARATOR).
                append(m_iCSMO == null?"":m_iCSMO).append(OUT_SEPARATOR).
                append(m_iEndOfCSFB == null?"":m_iEndOfCSFB).append(OUT_SEPARATOR).
                append(m_iFddSupport == null?"":m_iFddSupport).append(OUT_SEPARATOR).
                append(m_iTddSupport == null?"":m_iTddSupport).append(OUT_SEPARATOR).
                append(m_sRAND).append(OUT_SEPARATOR).
                append(m_sAUTN);
        return sb.toString();
    }
}
